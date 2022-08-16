package ch.noseryoung.sbdemo01.domain.role;

import ch.noseryoung.sbdemo01.domain.exceptions.IdExistsException;
import ch.noseryoung.sbdemo01.domain.exceptions.NotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> getAllRoles() {
        return repository.findAll();
    }

    public Role findById(int roleId) throws NotFoundException {
        return repository.findById(roleId).orElseThrow(() -> new NotFoundException("Role"));
    }

    public Role createNewRole(Role newRole) throws IdExistsException {
        if (!repository.existsById(newRole.getRoleId())) {
            log.debug("Role creation successful");
            return repository.save(newRole);
        }
        else {
            log.debug("Role creation NOT successful");
            throw new IdExistsException();
        }
    }

    public String deleteRole(int roleId) throws NotFoundException{
        repository.findById(roleId).orElseThrow(() -> new NotFoundException("Role"));
        repository.deleteById(roleId);
        return "Has been deleted";
    }

    public Role updateRole(Role role){
        return repository.save(role);

    }
}
