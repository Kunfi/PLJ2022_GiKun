package ch.noseryoung.sbdemo01.domain.role;

import ch.noseryoung.sbdemo01.domain.authority.Authority;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int roleId;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
        name = "roleauthority",
        joinColumns = @JoinColumn (name = "id_role", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn (name = "id_authority", referencedColumnName = "id")
    )
    private Set<Authority> authorities;

}