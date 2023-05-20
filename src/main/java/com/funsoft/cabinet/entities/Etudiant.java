package com.funsoft.cabinet.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Etudiant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    @ManyToMany(mappedBy = "participant", fetch = FetchType.LAZY)
    private List<Evenement> evenementList;

}
