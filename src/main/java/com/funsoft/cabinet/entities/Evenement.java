package com.funsoft.cabinet.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Evenement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @ManyToMany
    List<Etudiant> participant;
}
