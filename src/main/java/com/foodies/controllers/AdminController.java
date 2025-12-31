package com.foodies.controllers;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodies.entity.User;
import com.foodies.model.ResponseDto;
import com.foodies.model.UserDto;
import com.foodies.serviceImpl.UserServiceImpl;

@CrossOrigin
@Controller
@RequestMapping("/admin")
@Tag(name = "Admin APIs")
public class AdminController {
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/userList")
	public ResponseEntity<?> findAllUsers() {
		List<User> userlist = userService.getAllUser("customer");
		List<UserDto> result = new ArrayList<UserDto>();
		for (User user : userlist)
			result.add(UserDto.fromEntity(user));
		return ResponseDto.success(result);

	}

	@GetMapping("/deliveryBoyList")
	public ResponseEntity<?> findAllDeliveryBoy() {
		List<User> userlist = userService.getAllDeliveryBoy("deliveryBoy");
		List<UserDto> result = new ArrayList<UserDto>();
		for (User user : userlist)
			result.add(UserDto.fromEntity(user));
		return ResponseDto.success(result);

	}
}
