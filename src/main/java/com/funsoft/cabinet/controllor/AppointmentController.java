package com.funsoft.cabinet.controllor;

import com.funsoft.cabinet.dto.AppointmentDto;
import com.funsoft.cabinet.entities.Appointment;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rdvs")
@CrossOrigin(allowedHeaders = "*" ,origins = "*")
public class AppointmentController extends BasicController{
    @Autowired
    AppointmentService appointmentService;

    @PostMapping
    public Appointment createAppointment(@RequestBody AppointmentDto appointmentDto) throws ResourceNotFound {
        return  appointmentService.createAppointment(appointmentDto);
    }
   @GetMapping
   public List<AppointmentDto> getAppointments(){
        return appointmentService.getAppointments();
   }
   @GetMapping("/patient/find")
    public List<AppointmentDto> findByPatient(@RequestParam("Patient") long idp){
        return appointmentService.getAppointmentToByPatient(idp);
   }
    @GetMapping("/doctor/find")
    public List<AppointmentDto> findByDoctor(@RequestParam("Doctor") long idd) throws ResourceNotFound {
        return appointmentService.getAppointmentToByDoctor(idd);
    }
    @GetMapping("/{id}")
    public AppointmentDto getAppointment(@PathVariable("id") long id) throws ResourceNotFound {
        return appointmentService.getAppointment(id);
    }
    @DeleteMapping("/{id}")
    public Map<String,Boolean> deleteAppointment(@PathVariable("id") long id) throws ResourceNotFound {
        return appointmentService.deleteAppointment(id);
    }
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable("id") long id, @RequestBody AppointmentDto appointmentDto) throws ResourceNotFound {
        return appointmentService.updateAppointment(id, appointmentDto);
    }
}
