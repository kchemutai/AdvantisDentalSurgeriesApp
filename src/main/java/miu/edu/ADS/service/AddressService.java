package miu.edu.ADS.service;

import miu.edu.ADS.model.Address;

import java.util.List;
import java.util.Optional;


public interface AddressService {

	Optional<Address> createNewAddress(Address address);
	Optional<List<Address>> findAddresses();
	Optional<Address> findAddressById(Integer id);
	Optional<Address> updateAddress(Integer id, Address address);
	void deleteAddressById(Integer id);
}
