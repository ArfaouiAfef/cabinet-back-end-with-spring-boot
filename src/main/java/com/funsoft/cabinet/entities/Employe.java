package com.funsoft.cabinet.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    //obligatoire
    @ManyToOne
    @JoinColumn(name = "projet_id", referencedColumnName = "id")
    private Projet projet;
}
