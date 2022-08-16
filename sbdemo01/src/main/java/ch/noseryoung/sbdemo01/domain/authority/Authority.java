package ch.noseryoung.sbdemo01.domain.authority;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Authority {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int authorityId;

    @Column(name = "description")
    private String description;

}
