package ch.noseryoung.sbdemo01.domain.user;

import ch.noseryoung.sbdemo01.domain.exceptions.NotFoundException;
import ch.noseryoung.sbdemo01.domain.exceptions.IdExistsException;
import ch.noseryoung.sbdemo01.domain.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Log4j2
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(int userId) throws NotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User"));
    }

    public User createNewUser(User newUser) throws IdExistsException {
        if (!userRepository.existsById(newUser.getUserId())) {
            newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            userRepository.save(newUser);
            log.debug("User creation successful");
            return newUser;
        }
        else {
            log.debug("User creation NOT successful");
            throw new IdExistsException();
        }
    }

    public String deleteUser(int userId) throws NotFoundException{
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return "Has been deleted";
        }
        else {
            throw new NotFoundException("User");
        }
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public Object addRoleToUser(Integer userId, Integer roleId) throws NotFoundException {
        log.debug("Role existing");
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User"));
        user.getRoles().add(roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null){throw new UsernameNotFoundException("User not found");}
        else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(
                role -> role.getAuthorities().forEach(
                    authority -> authorities.add(new SimpleGrantedAuthority(authority.getDescription()))));
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
        }
    }
}
