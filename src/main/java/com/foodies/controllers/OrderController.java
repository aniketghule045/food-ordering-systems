package com.foodies.controllers;

import java.util.HashMap;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodies.model.OrderPlaceDto;
import com.foodies.model.ResponseDto;
import com.foodies.serviceImpl.OrderServiceImpl;

@RestController
@RequestMapping("/order")
@CrossOrigin
@Tag(name = "Orders APIs")
public class OrderController {
	@Autowired
	private OrderServiceImpl orderService;

	@PostMapping("/place")
	public ResponseEntity<?> placeOrderFromCart(@RequestBody OrderPlaceDto orderInput) {
		int userId = orderInput.getUserId();
		int addrId = orderInput.getAddressId();
		String paymentMode = orderInput.getPaymentMode();
		return new ResponseEntity<>(
				new ResponseDto<>("success", orderService.placeOrderForUser(userId, addrId, paymentMode)),
				HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllOrders() {
		return new ResponseEntity<>(new ResponseDto<>("success", orderService.getAllOrders()), HttpStatus.OK);
	}

	@GetMapping("/assigned/{userId}")
	public ResponseEntity<?> getAssignedOrders(@PathVariable int userId) {
		return new ResponseEntity<>(new ResponseDto<>("success", orderService.getAllAssignedOrders(userId)),
				HttpStatus.OK);
	}

	@GetMapping("/customer/all/{userId}")
	public ResponseEntity<?> getAllCustomerOrders(@PathVariable int userId) {
		return new ResponseEntity<>(new ResponseDto<>("success", orderService.getAllCustomerOrders(userId)),
				HttpStatus.OK);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<?> assignDeliveryBoy(@RequestBody int orderId, @PathVariable int userId) {
		orderService.assignDeliveryBoy(userId, orderId);
		return new ResponseEntity<>(new ResponseDto<>("success", "Order Assigned Successfully!!"), HttpStatus.OK);
	}

	@PutMapping("/update_status")
	public ResponseEntity<?> updateOrderStatus(@RequestBody HashMap<String, String> orderInput) {
		orderService.updateOrderStatus(Integer.parseInt(orderInput.get("orderId")), orderInput.get("status"));
		return new ResponseEntity<>(new ResponseDto<>("success", "Order Status Changed Successfully!!"), HttpStatus.OK);
	}

}
