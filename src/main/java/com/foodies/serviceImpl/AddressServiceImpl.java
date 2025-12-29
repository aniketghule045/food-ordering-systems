package com.foodies.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodies.repository.AddressRepository;
import com.foodies.repository.UserRepository;
import com.foodies.entity.Address;
import com.foodies.entity.User;



@Service
@Transactional
public class AddressServiceImpl {
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserRepository userRepository;


	public Address addAddress(Address address, int userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			user.get().addAddress(address);
			return address;
		}
		return null;
	}


	public Address editAddress(Address address, int addressId) {
		Optional<Address> address1 = addressRepository.findById(addressId);
		Address address2 = address1.orElse(null);
		address2.setAddressLine1(address.getAddressLine1());
		address2.setAddressLine2(address.getAddressLine2());
		System.out.println("------------------------"+address2);
		return address2;
	}


	public String deleteAddress(int addressId) {
		System.out.println("addressId : "+ addressId);
		addressRepository.deleteById(addressId);
		return "success";
	}


	public List<Address> getAllAddressesByUserId(int userId) {
		
		return addressRepository.getAllAddressesByUserId(userId);
	}


	public Optional<Address> getAddressesByUserId(int userId) {
		
		return addressRepository.findByUserId(userId);
	}

}
