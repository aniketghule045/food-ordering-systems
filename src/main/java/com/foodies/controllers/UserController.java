package com.foodies.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.foodies.entity.User;
import com.foodies.model.LoginRequest;
import com.foodies.model.ResponseDto;
import com.foodies.serviceImpl.UserServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginDetails) {
		System.out.println(loginDetails);
		System.out.println("In Authenticate User " + loginDetails);
		System.out.println(loginDetails.getEmail());
		User user = userService.authenticate(loginDetails.getEmail(), loginDetails.getPassword());
		System.out.println(user);
		return new ResponseEntity<>(new ResponseDto<User>("success", user), HttpStatus.CREATED);
	}

	

	@PostMapping(value = { "/register" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		user.setRole("customer");
		System.out.println("in create new User" + user);

		return new ResponseEntity<>(new ResponseDto<User>("success", userService.ceateNewUser(user)),
				HttpStatus.CREATED);
	}

	

	@PostMapping(value = { "/add_deliveryBoy" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerDeliveryBoy(@RequestBody User user) {
		user.setRole("deliveryBoy");
		System.out.println("in create new User" + user);

		return new ResponseEntity<>(new ResponseDto<User>("success", userService.ceateNewUser(user)),
				HttpStatus.CREATED);
	}

	@PostMapping("/edit/{userId}")
	public ResponseEntity<?> updateProfile(@RequestBody User user1, @PathVariable int userId) {
		user1.setId(userId);
		user1.setRole("customer");
		User user = userService.update(user1);
		return ResponseEntity.ok(new ResponseDto<User>("Success", user));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable int userId) {
		User user = userService.findUser(userId);
		return ResponseEntity.ok(new ResponseDto<User>("Success", user));
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable int userId) {
		String message = userService.deleteById(userId);
		return new ResponseEntity<>(new ResponseDto<>("success", message), HttpStatus.OK);
	}

	@PostMapping("/change_password")
	public ResponseEntity<?> changePassword(@RequestBody HashMap<String, String> map) {
		String email = map.get("Email");
		String password = map.get("Password");

		return new ResponseEntity<>(new ResponseDto<>("success", userService.updatePassword(email, password)),
				HttpStatus.CREATED);
	}
	
}
