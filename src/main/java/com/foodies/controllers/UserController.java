package com.foodies.controllers;

import java.util.HashMap;
import java.util.Map;

import com.foodies.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private JwtUtil jwtUtil;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userService.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginDetails) {

        User user = userService.authenticate(
                loginDetails.getEmail(),
                loginDetails.getPassword());


        String token = jwtUtil.generateToken(user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("token", token);
        response.put("user", user);

        return ResponseEntity.ok(response);

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
	public ResponseEntity<?> changePassword(@RequestBody LoginRequest loginDetails) {
		String email = loginDetails.getEmail();
		String password = loginDetails.getPassword();

		return new ResponseEntity<>(new ResponseDto<>("success", userService.updatePassword(email, password)),
				HttpStatus.CREATED);
	}
	
}
