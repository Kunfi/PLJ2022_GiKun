package ch.noseryoung.sbdemo01.domain.user;

import ch.noseryoung.sbdemo01.domain.authority.AuthorityRepository;
import ch.noseryoung.sbdemo01.domain.exceptions.NotFoundException;
import ch.noseryoung.sbdemo01.domain.exceptions.IdExistsException;
import ch.noseryoung.sbdemo01.domain.role.RoleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Log4j2
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository repository, AuthorityRepository authorityRepository, RoleRepository roleRepository) {
        this.userRepository = repository;
        this.authorityRepository = authorityRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(int userId) throws NotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User"));
    }

    public User createNewUser(User newUser) throws IdExistsException {
        if (!userRepository.existsById(newUser.getUserId())) {
            log.debug("User creation successful");
            return userRepository.save(newUser);
        }
        else {
            log.debug("User creation NOT successful");
            throw new IdExistsException();
        }
    }

    public String deleteUser(int userId) throws NotFoundException{
        userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User"));
        userRepository.deleteById(userId);
        return "Has been deleted";
    }

    public User updateUser(User user){
        return userRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null){throw new UsernameNotFoundException("User not found");}
        else {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> role.getAuthorities().forEach(authority ->
                authorities.add(new SimpleGrantedAuthority(authority.getDescription()))));
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
        }
    }

}
