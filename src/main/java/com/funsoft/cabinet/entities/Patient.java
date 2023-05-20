package com.funsoft.cabinet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.funsoft.cabinet.entities.Appointment;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstname;
    private String lastname;
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Appointment> appointments;
}
