package com.funsoft.cabinet.controllor;

import com.funsoft.cabinet.entities.Patient;
import com.funsoft.cabinet.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
public class PatientController {
    @Autowired
    PatientService patientService;


    @PostMapping
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.save(patient);
    }

    @GetMapping
    public List<Patient> getPatients(){
        return patientService.getPatient();
    }

    @GetMapping(value = "/{id}")
    public Patient getPatient(@RequestParam("id") Long id){
        return patientService.getPatient(id);
    }

    @DeleteMapping
    public Map<String,Boolean> deletePatient(@RequestParam("id") Long id){
        return patientService.delete(id);
    }

    @PutMapping
    public Patient updatePatient(@RequestParam("id") Long id,@RequestBody Patient patient){
        return patientService.update(id,patient);
    }

    @GetMapping(value = "/find")
    public Patient findByPhone(@RequestParam("phone") String phone){
        return patientService.findByPhone(phone);
    }

}
