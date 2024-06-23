package miu.edu.ADS.repository;

import miu.edu.ADS.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Integer> {
}
