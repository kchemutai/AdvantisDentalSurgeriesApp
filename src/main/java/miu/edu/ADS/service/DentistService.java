package miu.edu.ADS.service;


import miu.edu.ADS.model.Dentist;

import java.util.Optional;

public interface DentistService {

	Optional<Dentist> saveDentist(Dentist dentist);
	Optional<Dentist> findDentistById(Integer dentistId);
	Optional<Dentist> updateExistingDentist(Integer id, Dentist dentist);
	void deleteDentist(Integer dentistId);
}
