
package com.foodies.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodies.entity.Address;
import com.foodies.model.ResponseDto;
import com.foodies.serviceImpl.AddressServiceImpl;


@RestController
@RequestMapping("/address")
@CrossOrigin
public class AddressController {
	
	@Autowired
	private AddressServiceImpl addressService;
	
	
	@GetMapping("/find/{userId}")
	public ResponseEntity<?> getUserContents(@PathVariable int userId) {
		Optional<Address> address1 = addressService.getAddressesByUserId(userId);
		return new ResponseEntity<>(new ResponseDto<>("success", address1), HttpStatus.OK);
	}
	
	@PostMapping("/add/{id}")
	public ResponseEntity<?> addAddress(@RequestBody Address address , @PathVariable int id){
		System.out.println(address);
		Address address1 = addressService.addAddress(address , id );
		System.out.println("Address Added :  "+ address1);
		return new ResponseEntity<>(new ResponseDto<Address>("success", address),HttpStatus.CREATED);
	}
	@GetMapping("/all/{userId}")
	public ResponseEntity<?> getCartContents(@PathVariable int userId) {
		List<Address> address1 = addressService.getAllAddressesByUserId(userId);
		return new ResponseEntity<>(new ResponseDto<>("success", address1), HttpStatus.OK);
	}
	
	@PutMapping("/edit/{addressId}")
	public ResponseEntity<?> editAddress(@RequestBody Address address , @PathVariable int addressId){
		Address address1 = addressService.editAddress(address , addressId );
		System.out.println("Address edited :  "+ address1);
		return new ResponseEntity<>(new ResponseDto<Address>("success", address1),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{addressId}")
	public ResponseEntity<?> deleteAddress(@PathVariable int addressId){
		System.out.println(addressId);
		
		String message = addressService.deleteAddress(addressId);
		return new ResponseEntity<>(new ResponseDto<>("success", message),HttpStatus.CREATED);
	}
}

