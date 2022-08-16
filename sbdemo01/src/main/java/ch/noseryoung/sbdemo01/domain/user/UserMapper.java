package ch.noseryoung.sbdemo01.domain.user;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserDTO userToDTO(User user);
    User DTOtoUser(UserDTO userDTO);
    Collection<UserDTO> userToDTO(Collection<User> users);


}
