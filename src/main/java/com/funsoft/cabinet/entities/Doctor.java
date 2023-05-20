package com.funsoft.cabinet.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

//PJO ---> entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "doctors")

public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Firstname is mandatory")
    @Pattern(regexp = "[a-zA-Z]+", message = "Firstname should be contains only alphabetic")
    @Column(length = 30, nullable = false)
    private String firstname;

    @NotBlank(message = "Lastname is mandatory")
    @Pattern(regexp = "[a-zA-Z]+", message = "Lastname should be contains only alphabetic")
    @Column(length = 35, nullable = false)
    private String lastname;

    @Email(message = "Email invalid")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Address is mandatory")
    @Column(nullable = false)
    private String address;

   // @NotBlank(message = "Speciality is mandatory")
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

  /*  @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Appointment> appointments;*/

}
