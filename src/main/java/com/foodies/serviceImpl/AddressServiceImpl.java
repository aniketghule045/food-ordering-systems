package com.foodies.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodies.daos.AddressDao;
import com.foodies.daos.UserDao;
import com.foodies.entity.Address;
import com.foodies.entity.User;



@Service
@Transactional
public class AddressServiceImpl {
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private UserDao userDao;


	public Address addAddress(Address address, int userId) {
		Optional<User> user = userDao.findById(userId);
		if(user.isPresent()) {
			user.get().addAddress(address);
			return address;
		}
		return null;
	}


	public Address editAddress(Address address, int addressId) {
		Optional<Address> address1 = addressDao.findById(addressId);
		Address address2 = address1.orElse(null);
		address2.setAddressLine1(address.getAddressLine1());
		address2.setAddressLine2(address.getAddressLine2());
		System.out.println("------------------------"+address2);
		return address2;
	}


	public String deleteAddress(int addressId) {
		System.out.println("addressId : "+ addressId);
		addressDao.deleteById(addressId);
		return "success";
	}


	public List<Address> getAllAddressesByUserId(int userId) {
		
		return addressDao.getAllAddressesByUserId(userId);
	}


	public Optional<Address> getAddressesByUserId(int userId) {
		
		return addressDao.findByUserId(userId);
	}

}
