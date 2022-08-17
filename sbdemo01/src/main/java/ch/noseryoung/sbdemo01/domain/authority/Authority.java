package ch.noseryoung.sbdemo01.domain.authority;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int authorityId;

    @Column(name = "description")
    private String description;

}
