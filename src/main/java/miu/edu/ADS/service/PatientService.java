package miu.edu.ADS.service;


import miu.edu.ADS.model.Patient;

import java.util.Optional;

public interface PatientService {
	Optional<Patient> savePatient(Patient patient);
	Optional<Patient> findPatientById(Integer patientId);
	Optional<Patient> updateExistingPatient(Integer id, Patient patient);
	void deletePatient(Integer patientId);
}
