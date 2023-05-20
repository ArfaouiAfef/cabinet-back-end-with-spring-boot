package com.funsoft.cabinet.repository;

import com.funsoft.cabinet.entities.Appointment;
import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    //select p from Appointment p where p.patient= :patient
    //select * from Appointment p where p.patient_id = patient.id
    public List<Appointment> findByPatient(Patient patient);
    public List<Appointment> findByDoctor(Doctor doctor);
}
