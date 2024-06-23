package miu.edu.ADS.service.impl;

import java.util.List;
import java.util.Optional;

import miu.edu.ADS.exception.address.AddressNotFoundException;
import miu.edu.ADS.model.Address;
import miu.edu.ADS.repository.AddressRepository;
import miu.edu.ADS.service.AddressService;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
	
	private final AddressRepository addressRepository;
	
	@Override
	public Optional<Address> createNewAddress(Address address) {
        log.info("A new Address with id={} will be created!", address.getId());

		Address savedAddress = addressRepository.save(address);
        log.info("The Address with id={} is created!", savedAddress.getId());

		return Optional.of(savedAddress);
	}

	@Override
	public Optional<List<Address>> findAddresses() {
		List<Address> addresses = addressRepository.findAll();
		if (addresses.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(addresses);
	}

	public Optional<Address> findAddressById(Integer id) {
		Optional<Address> address = addressRepository.findById(id);
		if (address.isPresent()) {
			return address;
		}
		return Optional.empty();
	}

	@Override
	public Optional<Address> updateAddress(Integer id, Address address) {
		Optional<Address> savedAddress = addressRepository.findById(id);
		if (savedAddress.isPresent()) {
			Address address1 = savedAddress.get();
			address1.setStreet(address.getStreet());
			address1.setCity(address.getCity());
			address1.setState(address.getState());
			address1.setZip(address.getZip());
			Address updatedAddress = addressRepository.save(address1);
			return Optional.of(updatedAddress);
		}
		return Optional.empty();
	}

	@Override
	public void deleteAddressById(Integer id) {
		addressRepository.findById(id).orElseThrow(()-> new AddressNotFoundException("Delete Failed, address with Id "+id+" not found"));
		addressRepository.deleteById(id);
	}
}
