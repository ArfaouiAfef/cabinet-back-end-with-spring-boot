package com.funsoft.cabinet.mapper;

import com.funsoft.cabinet.dto.AppointmentDto;
import com.funsoft.cabinet.entities.Appointment;
import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Patient;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.service.DoctorService;
import com.funsoft.cabinet.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperAppointment implements Serializable {
    @Autowired
    DoctorService doctorService;
    @Autowired
    PatientService patientService;

    public Appointment dtoToEntity(AppointmentDto appointmentDto) throws ResourceNotFound {
        Appointment appointment = new Appointment();
        Doctor doctor = doctorService.getDoctor(appointmentDto.getIdd());
        appointment.setDoctor(doctor);
        Patient patient = patientService.getPatient(appointmentDto.getIdp());
        appointment.setPatient(patient);
        appointment.setRdvTime(appointmentDto.getRdvTime());
        return appointment;
    }

    public AppointmentDto entityToDto(Appointment appointment){
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointmentDto.getId());
        appointmentDto.setIdd(appointment.getDoctor().getId());
        appointmentDto.setIdp(appointment.getPatient().getId());
        appointmentDto.setRdvTime(appointment.getRdvTime());
        return appointmentDto;
    }
    public List<AppointmentDto> entitiesToListDto(List<Appointment> entities){
        return entities.stream().map(item -> entityToDto(item)).collect(Collectors.toList());
    }
}

