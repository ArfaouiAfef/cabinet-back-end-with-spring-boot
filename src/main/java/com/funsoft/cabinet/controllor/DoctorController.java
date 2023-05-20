package com.funsoft.cabinet.controllor;

import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Speciality;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
public class DoctorController extends BasicController{
    @Autowired
    DoctorService doctorService;

    @GetMapping(value = "/welcome")
    public String welcome() {
        return "welcome, I'm a robot. I will help you to manage doctors.";
    }

    @PostMapping
    public Doctor createDoctor(@Valid @RequestBody Doctor doctor){
        return doctorService.Save(doctor);
        }

    @GetMapping
    public List<Doctor> geDoctors(){
        return doctorService.getDoctors();
    }

    @GetMapping(value = "/{id}")
    public Doctor geDoctor(@PathVariable("id") long id) throws ResourceNotFound {
        return doctorService.getDoctor(id);
    }

    @GetMapping(value = "/get")
    public Doctor getDoctorByParam(@RequestParam("doctorId") long id) throws ResourceNotFound {
        return doctorService.getDoctor(id);
    }

    @DeleteMapping
    public Map<String, Boolean> deleteDoctor(@RequestParam("id") long id){
        return doctorService.deleteDoctor(id);
    }

    @PutMapping
    public Doctor updateDoctor(@RequestParam("id") long id, @Valid@ RequestBody Doctor doctor) throws ResourceNotFound {
        return doctorService.updateDoctor(id, doctor);
    }

    @GetMapping(value = "/find")
    public List<Doctor> getBySpeciality(@RequestParam("speciality")Speciality speciality){
        return doctorService.searchBySpeciality(speciality);
    }

    @GetMapping(value = "/search")
    public List<Doctor> search(@RequestParam("speciality")Speciality speciality, @RequestParam("pseudo")String pseudo){
       return doctorService.advancedSearch(speciality, pseudo+'%');
    }
}
