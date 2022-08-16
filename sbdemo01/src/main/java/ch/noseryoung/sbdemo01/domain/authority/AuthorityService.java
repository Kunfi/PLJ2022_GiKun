package ch.noseryoung.sbdemo01.domain.authority;

import ch.noseryoung.sbdemo01.domain.exceptions.NotFoundException;
import ch.noseryoung.sbdemo01.domain.exceptions.IdExistsException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class AuthorityService {

    private final AuthorityRepository repository;

    @Autowired
    public AuthorityService(AuthorityRepository repository) {
        this.repository = repository;
    }

    public List<Authority> getAllUsers() {
        return repository.findAll();
    }

    public Authority findById(int authorityId) throws NotFoundException {
        return repository.findById(authorityId).orElseThrow(() -> new NotFoundException("Authority"));
    }

    public Authority createNewAuthority(Authority newAuthority) throws IdExistsException {
        if (!repository.existsById(newAuthority.getAuthorityId())) {
            log.debug("Authority creation successful");
            return repository.save(newAuthority);
        }
        else {
            log.debug("Authority creation NOT successful");
            throw new IdExistsException();
        }
    }

    public String deleteAuthority(int authorityId) throws NotFoundException{
        repository.findById(authorityId).orElseThrow(() -> new NotFoundException("Authority"));
        repository.deleteById(authorityId);
        return "Has been deleted";
    }

    public Authority updateAuthority(Authority authority){
        return repository.save(authority);

    }
}
