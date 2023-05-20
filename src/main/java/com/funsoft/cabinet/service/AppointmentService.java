package com.funsoft.cabinet.service;

import com.funsoft.cabinet.dto.AppointmentDto;
import com.funsoft.cabinet.entities.Appointment;
import com.funsoft.cabinet.exception.ResourceNotFound;

import java.util.List;
import java.util.Map;

public interface AppointmentService {

    public Appointment createAppointment(AppointmentDto appointmentDto) throws ResourceNotFound;
    public List<AppointmentDto> getAppointments();

    public List<AppointmentDto> getAppointmentToByPatient(Long idp);

    public List<AppointmentDto> getAppointmentToByDoctor(Long idd) throws ResourceNotFound;

    public AppointmentDto getAppointment(Long id) throws ResourceNotFound;

    public Map<String,Boolean> deleteAppointment(Long id) throws ResourceNotFound;

    public Appointment updateAppointment(Long id, AppointmentDto appointmentDto) throws ResourceNotFound;


}
