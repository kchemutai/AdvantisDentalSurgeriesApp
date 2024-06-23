package miu.edu.ADS.service.impl;

import miu.edu.ADS.exception.patient.PatientNotFoundException;
import miu.edu.ADS.model.Patient;
import miu.edu.ADS.repository.PatientRepository;
import miu.edu.ADS.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientServiceImpl implements PatientService {

	private final PatientRepository patientRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Optional<Patient> savePatient(Patient patient) {
		patient.getUser().setPassword(passwordEncoder.encode(
				patient.getUser().getPassword()
		));
		return Optional.of(patientRepository.save(patient));
	}

	@Override
	public Optional<Patient> findPatientById(Integer patientId) {
		Optional<Patient> patient = patientRepository.findById(patientId);
		if (patient.isPresent()) {
			return patient;
		}
		return Optional.empty();
	}

	@Override
	public Optional<Patient> updateExistingPatient(Integer id, Patient patient) {
		Optional<Patient> existingPatient = patientRepository.findById(id);
		if (existingPatient.isPresent()) {
			Patient updatedPatient = existingPatient.get();
			updatedPatient.setUser(patient.getUser());
			updatedPatient.setOutstandingBill(patient.getOutstandingBill());
			updatedPatient.setAddress(patient.getAddress());
			updatedPatient.setAppointments(patient.getAppointments());
			return Optional.of(patientRepository.save(updatedPatient));
		}
		return Optional.empty();
	}

	@Override
	public void deletePatient(Integer patientId) {
		patientRepository.findById(patientId).orElseThrow(()->new PatientNotFoundException("Delete failed!, Patient with Id "+patientId+" not found"));
		patientRepository.deleteById(patientId);
	}

	@Override
	public Optional<List<Patient>> findAllPatients() {
		List<Patient> patients = patientRepository.findAll();
		if (patients.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(patients);
	}
}
