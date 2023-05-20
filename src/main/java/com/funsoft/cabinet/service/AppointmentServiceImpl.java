package com.funsoft.cabinet.service;

import com.funsoft.cabinet.dto.AppointmentDto;
import com.funsoft.cabinet.entities.Appointment;
import com.funsoft.cabinet.entities.Doctor;
import com.funsoft.cabinet.entities.Patient;
import com.funsoft.cabinet.exception.ResourceNotFound;
import com.funsoft.cabinet.mapper.MapperAppointment;
import com.funsoft.cabinet.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    MapperAppointment mapperAppointment;

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @Override
    public Appointment createAppointment(AppointmentDto appointmentDto) throws ResourceNotFound {
        Appointment appointment = mapperAppointment.dtoToEntity(appointmentDto);
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentDto> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        List<AppointmentDto> appointmentDtos = mapperAppointment.entitiesToListDto(appointments);
        return appointmentDtos;
    }

    @Override
    public List<AppointmentDto> getAppointmentToByPatient(Long idp) {
        Patient patient = patientService.getPatient(idp);
        List<Appointment> appointments = appointmentRepository.findByPatient(patient);
        List<AppointmentDto> appointmentDtos = mapperAppointment.entitiesToListDto(appointments);
        return appointmentDtos;
    }

    @Override
    public List<AppointmentDto> getAppointmentToByDoctor(Long idd) throws ResourceNotFound {
        Doctor doctor = doctorService.getDoctor(idd);
        List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);
        List<AppointmentDto> appointmentDtos = mapperAppointment.entitiesToListDto(appointments);
        return appointmentDtos;
    }

    @Override
    public AppointmentDto getAppointment(Long id) throws ResourceNotFound {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
        ()-> new ResourceNotFound("Appointment not found for id: "+id));
        return mapperAppointment.entityToDto(appointment);
    }

    @Override
    public Map<String,Boolean> deleteAppointment(Long id) throws ResourceNotFound {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Appointment not found for id: "+id));
        appointmentRepository.delete(appointment);
        Map<String,Boolean> res = new HashMap<>();
        res.put("delete",Boolean.TRUE);
        return res;
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentDto appointmentDto) throws ResourceNotFound {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFound("Appointment not found for id: "+id));
        appointment.setDoctor(doctorService.getDoctor(appointmentDto.getIdd()));
        appointment.setPatient(patientService.getPatient(appointmentDto.getIdp()));
        appointment.setRdvTime(appointmentDto.getRdvTime());

        return appointmentRepository.save(appointment);
    }
}
