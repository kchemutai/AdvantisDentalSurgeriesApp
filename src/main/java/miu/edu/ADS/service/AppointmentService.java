package miu.edu.ADS.service;

import miu.edu.ADS.dto.appointment.AppointmentRequest;
import miu.edu.ADS.model.Appointment;

import java.util.List;
import java.util.Optional;


public interface AppointmentService {

	Optional<Appointment> createNewAppointment(AppointmentRequest appointmentRequest);
	Optional<List<Appointment>> findDentistAppointmentsById(Integer userId);
	Optional<List<Appointment>> findPatientAppointmentsById(Integer patientId);
	Optional<Appointment> findAppointmentById(Integer id);
	Optional<List<Appointment>> findAllAppointments();
	Optional<Appointment> updateAppointment(Integer id, Appointment appointment);
	void deleteAppointment(Integer id);
}
