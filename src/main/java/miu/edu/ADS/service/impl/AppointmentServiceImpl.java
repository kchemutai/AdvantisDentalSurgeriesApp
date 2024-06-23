package miu.edu.ADS.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import miu.edu.ADS.exception.appointment.AppointmentNotFoundException;
import miu.edu.ADS.model.Appointment;
import miu.edu.ADS.repository.AppointmentRepository;
import miu.edu.ADS.service.AppointmentService;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
	
	private final AppointmentRepository appointmentRepository;


    @Override
	public Optional<Appointment> createNewAppointment(Appointment appointment) {
		Appointment newAppointment = appointmentRepository.save(appointment);
		return Optional.of(newAppointment);
	}

	@Override
	public Optional<List<Appointment>> findDentistAppointmentsById(Integer dentistId) {
		List<Appointment> appointments = appointmentRepository.findAll();
		if (appointments.isEmpty()) {
			return Optional.empty();
		}
		List<Appointment> dentistAppointments = appointments.stream()
				.filter(appointment -> Objects.equals(appointment.getDentist().getId(), dentistId))
				.toList();
		return Optional.of(dentistAppointments);
	}

	@Override
	public Optional<List<Appointment>> findPatientAppointmentsById(Integer patientId) {
		List<Appointment> appointments = appointmentRepository.findAll();
		if (appointments.isEmpty()) {
			return Optional.empty();
		}
		List<Appointment> patientAppointments = appointments.stream()
				.filter(appointment -> Objects.equals(appointment.getDentist().getId(), patientId))
				.toList();
		return Optional.of(patientAppointments);
	}

	@Override
	public Optional<Appointment> findAppointmentById(Integer id) {
		Optional<Appointment> appointment = appointmentRepository.findById(id);
		if (appointment.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(appointment.get());
	}

	@Override
	public Optional<List<Appointment>> findAllAppointments() {
		List<Appointment> appointments = appointmentRepository.findAll();
		if (appointments.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(appointments);
	}

	@Override
	public Optional<Appointment> updateAppointment(Integer id, Appointment appointment) {
		Optional<Appointment> savedAppointment = appointmentRepository.findById(id);
		if (savedAppointment.isEmpty()) {
			Appointment newAppointment = appointmentRepository.save(appointment);
			newAppointment.setDate(appointment.getDate());
			newAppointment.setTime(appointment.getTime());
			newAppointment.setStatus(appointment.getStatus());
			newAppointment.setSurgery(appointment.getSurgery());
			newAppointment.setPatient(appointment.getPatient());
			newAppointment.setDentist(appointment.getDentist());
			return Optional.of(newAppointment);
		}
		return Optional.empty();
	}

	@Override
	public void deleteAppointment(Integer id) {
		if (appointmentRepository.findById(id).isPresent()) {
			appointmentRepository.deleteById(id);
		}
		else throw new AppointmentNotFoundException("Delete failed, appointment with Id "+id+" not found");
	}
}
