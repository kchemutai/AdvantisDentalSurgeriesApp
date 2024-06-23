package miu.edu.ADS.service.impl;

import java.util.List;
import java.util.Optional;

import miu.edu.ADS.exception.surgery.SurgeryNotFoundException;
import miu.edu.ADS.model.Surgery;
import miu.edu.ADS.repository.SurgeryRepository;
import miu.edu.ADS.service.SurgeryService;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SurgeryServiceImpl implements SurgeryService {
	
	private final SurgeryRepository surgeryRepository;


	@Override
	public Optional<Surgery> createNewSurgery(Surgery surgery) {
		return Optional.of(surgeryRepository.save(surgery));
	}

	@Override
	public Optional<Surgery> getSurgeryById(Integer id) {
		return surgeryRepository.findById(id);
	}

	@Override
	public Optional<Surgery> updateSurgery(Integer id, Surgery surgery) {
		Optional<Surgery> surgeryOptional = surgeryRepository.findById(id);
		if (surgeryOptional.isPresent()) {
			Surgery surgeryUpdated = surgeryOptional.get();
			surgeryUpdated.setSurgeryName(surgery.getSurgeryName());
			surgeryUpdated.setPhoneNumber(surgery.getPhoneNumber());
			surgeryUpdated.setLocation(surgery.getLocation());
			surgeryUpdated.setAppointment(surgery.getAppointment());
			return Optional.of(surgeryRepository.save(surgeryUpdated));
		}
		throw new SurgeryNotFoundException("Update failed!, Surgery with Id "+id+" not found");
	}

	@Override
	public void deleteSurgery(Integer id) {
		surgeryRepository.findById(id).orElseThrow(()->new SurgeryNotFoundException("Delete failed!, Surgery with Id "+id+" not found"));
	}

	@Override
	public Optional<List<Surgery>> findAllSurgeries() {
		List<Surgery> surgeryList = surgeryRepository.findAll();
		if (surgeryList.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(surgeryList);
	}
}
