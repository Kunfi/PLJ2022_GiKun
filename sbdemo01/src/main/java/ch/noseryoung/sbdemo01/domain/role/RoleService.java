package ch.noseryoung.sbdemo01.domain.role;

import ch.noseryoung.sbdemo01.domain.authority.Authority;
import ch.noseryoung.sbdemo01.domain.authority.AuthorityRepository;
import ch.noseryoung.sbdemo01.domain.exceptions.IdExistsException;
import ch.noseryoung.sbdemo01.domain.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, AuthorityRepository authorityRepository) {
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role findById(int roleId) throws NotFoundException {
        return roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role"));
    }

    public Role createNewRole(Role newRole) throws IdExistsException {
        if (!roleRepository.existsById(newRole.getRoleId())) {
            log.debug("Role creation successful");
            return roleRepository.save(newRole);
       }
        else {
            log.debug("Role creation NOT successful");
            throw new IdExistsException();
        }
    }

    public Role addAuthorityToRole(Integer roleId, Integer authorityId) {
        if (!authorityRepository.existsById(authorityId)) {
            log.debug("Authority existing");
            Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role"));
            role.getAuthorities().add(authorityRepository.findById(authorityId).orElseThrow(() -> new NotFoundException("Authority")));
            return roleRepository.save(role);
        }
        else {
            log.debug("Role creation NOT successful");
            throw new IdExistsException();
        }
    }

    public String deleteRole(int roleId) throws NotFoundException{
        roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role"));
        roleRepository.deleteById(roleId);
        return "Has been deleted";
    }

    public Role updateRole(Role role){
        return roleRepository.save(role);

    }
}
