package miu.edu.ADS.service.impl;

import miu.edu.ADS.exception.dentist.DentistNotFoundException;
import miu.edu.ADS.model.Dentist;
import miu.edu.ADS.repository.DentistRepository;
import miu.edu.ADS.service.DentistService;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DentistServiceImpl implements DentistService {

	private final DentistRepository dentistRepository;


	@Override
	public Optional<Dentist> saveDentist(Dentist dentist) {
		return Optional.of(dentistRepository.save(dentist));
	}

	@Override
	public Optional<Dentist> findDentistById(Integer dentistId) {
		Optional<Dentist> savedDentist = dentistRepository.findById(dentistId);
		if (savedDentist.isPresent()) {
			return savedDentist;
		}
		return Optional.empty();
	}

	@Override
	public Optional<Dentist> updateExistingDentist(Integer id, Dentist dentist) {
		Optional<Dentist> savedDentist = dentistRepository.findById(id);
		if (savedDentist.isPresent()) {
			Dentist updatedDentist = savedDentist.get();
			updatedDentist.setSpecialization(dentist.getSpecialization());
			updatedDentist.setUser(dentist.getUser());
			updatedDentist.setAppointments(dentist.getAppointments());
			return Optional.of(updatedDentist);
		}
		return Optional.empty();
	}

	@Override
	public void deleteDentist(Integer dentistId) {
		dentistRepository.findById(dentistId).orElseThrow(()->new DentistNotFoundException("Delete failed!, Dentist with Id "+dentistId+" not found"));
		dentistRepository.deleteById(dentistId);
	}
}
