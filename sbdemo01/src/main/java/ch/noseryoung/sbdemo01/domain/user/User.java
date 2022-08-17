package ch.noseryoung.sbdemo01.domain.user;

import ch.noseryoung.sbdemo01.domain.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @NotNull
    @Column(name = "name")
    private String userName;

    @NotNull
    @Column(name = "password")
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role", referencedColumnName = "id")
    private Role role;

}
