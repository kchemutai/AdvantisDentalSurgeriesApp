package miu.edu.ADS.repository;

import miu.edu.ADS.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
