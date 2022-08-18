package ch.noseryoung.sbdemo01.domain.role;

import ch.noseryoung.sbdemo01.domain.authority.Authority;
import ch.noseryoung.sbdemo01.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int roleId;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    @ManyToMany
    @JoinTable(
        name = "roles_authorities",
        joinColumns = @JoinColumn(
            name = "id_role", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(
            name = "id_authority", referencedColumnName = "id"))
    private Set<Authority> authorities;

}