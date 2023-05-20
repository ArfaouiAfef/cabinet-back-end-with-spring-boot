package com.funsoft.cabinet.service;

import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Speciality;
import com.funsoft.cabinet.exception.ResourceNotFound;

import java.util.List;
import java.util.Map;

public interface DoctorService {
    public Doctor Save(Doctor doctor);
    public  Doctor getDoctor(Long id) throws ResourceNotFound;
    public List<Doctor> getDoctors();
    public Map<String,Boolean> deleteDoctor(Long id);
    public List<Doctor> searchByFirstName(String firstName);
    public List<Doctor> searchByFirstNameOrLastName(String name);
    public List<Doctor> searchBySpeciality(Speciality speciality);
    public List<Doctor> advancedSearch(Speciality speciality,String pseudo);
    public Doctor updateDoctor(long id, Doctor doctor) throws ResourceNotFound;

}
