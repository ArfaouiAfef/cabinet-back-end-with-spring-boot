package com.funsoft.cabinet.service;

import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Speciality;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Doctor Save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor getDoctor(Long id) throws ResourceNotFound {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("doctor not found for id: "+id)
        );
        return doctor;
    }

    @Override
    public List<Doctor> getDoctors() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    public Map<String, Boolean> deleteDoctor(Long id) {
       doctorRepository.deleteById(id);

        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted",Boolean.TRUE);
        return res;
    }

    @Override
    public List<Doctor> searchByFirstName(String firstName) {
        return doctorRepository.findByFirstname(firstName);
    }

    @Override
    public List<Doctor> searchByFirstNameOrLastName(String name) {
        return doctorRepository.findByFirstnameOrLastname(name,name);
    }

    @Override
    public List<Doctor> searchBySpeciality(Speciality speciality) {
        return doctorRepository.findBySpeciality(speciality);
    }

    @Override
    public List<Doctor> advancedSearch(Speciality speciality, String pseudo) {
        return doctorRepository.search(speciality,pseudo);
    }

    @Override
    public Doctor updateDoctor(long id, Doctor doctor) throws ResourceNotFound {
        Doctor old = getDoctor(id);
        old.setFirstname(doctor.getFirstname());
        old.setLastname(doctor.getLastname());
        old.setEmail(doctor.getEmail());
        old.setAddress(doctor.getAddress());
        old.setSpeciality(doctor.getSpeciality());
        return doctorRepository.save(old);
    }
}
