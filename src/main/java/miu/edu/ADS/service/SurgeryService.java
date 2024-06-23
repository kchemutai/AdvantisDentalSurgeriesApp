package miu.edu.ADS.service;

import miu.edu.ADS.model.Surgery;

import java.util.List;
import java.util.Optional;


public interface SurgeryService {

	Optional<Surgery> createNewSurgery(Surgery surgery);
	Optional<Surgery> getSurgeryById(Integer id);
	Optional<Surgery> updateSurgery(Integer id, Surgery surgery);
	void deleteSurgery(Integer id);
	Optional<List<Surgery>> findAllSurgeries();
}
