package ch.noseryoung.sbdemo01.domain.user;

import ch.noseryoung.sbdemo01.domain.role.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserDTO {

    private String userName;
    private Role role;

}
