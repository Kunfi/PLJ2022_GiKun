package ch.noseryoung.sbdemo01.domain.user;

import ch.noseryoung.sbdemo01.domain.role.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(
            name = "id_user", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
            name = "id_role", referencedColumnName = "id"))

    private Collection<Role> roles;

}
