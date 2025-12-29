package com.foodies.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodies.repository.AddressRepository;
import com.foodies.repository.CartRepository;
import com.foodies.repository.OrderRepository;
import com.foodies.repository.OrderDetailRepository;
import com.foodies.repository.PaymentRepository;
import com.foodies.repository.UserRepository;
import com.foodies.entity.Address;
import com.foodies.entity.Cart;
import com.foodies.entity.Order;
import com.foodies.entity.OrderDetail;
import com.foodies.entity.OrderStatus;
import com.foodies.entity.Payment;
import com.foodies.entity.PaymentStatus;
import com.foodies.entity.Type;
import com.foodies.entity.User;
import com.foodies.model.OrderResponse;


@Service
@Transactional
public class OrderServiceImpl {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired 
	private OrderDetailRepository orderDetailRepository;

	 
	public String placeOrderForUser(int userId, int addrId,String paymentMode) {
		// get all cart items for given user
		List<Cart> cartItems = cartRepository.findAllItemsByUser(userId);
		
		double total = 0.0;
		int deliveryCharges = 25;
		for (Cart item : cartItems) {
			total += item.getQuantity() * item.getSelectedMenu().getPrice();
		}

		Address address = addressRepository.findById(addrId).get();
		User customer = userRepository.findById(userId).get();
		
		System.out.println("-------------------------------------");
		Order newOrder = new Order(total, LocalDateTime.now(), OrderStatus.PLACED, LocalDateTime.now(), address, customer,null);
		orderRepository.save(newOrder);

		
		Payment payment = new Payment(total + deliveryCharges, LocalDateTime.now(), paymentMode.equals("COD") ? PaymentStatus.PENDING : PaymentStatus.COMPLETED, Type.valueOf(paymentMode), newOrder);
		paymentRepository.save(payment);
		cartItems.forEach(item -> {
			orderDetailRepository.save(new OrderDetail(item.getSelectedMenu().getPrice(), item.getQuantity(), newOrder,
					item.getSelectedMenu()));
		});
		cartRepository.deleteAll(cartItems);
		return "Order Placed Successfully!";
	}

	 
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderResponse> response = new ArrayList<>();
		
		for (Order order : orders) {
			List<OrderDetail> orderDetails =  orderDetailRepository.findAllByOrderId(order.getId());
			Payment payment = paymentRepository.findPaymentByOrderId(order.getId());
			response.add(new OrderResponse(order, orderDetails,payment));
		}
		return response;
	}

	 
	public List<OrderResponse> getAllAssignedOrders(int userId) {
		List<Order> orders = orderRepository.findAllOrdersByEmployeeId(userId);
		
		List<OrderResponse> response = new ArrayList<>();
		
		for (Order order : orders) {
			List<OrderDetail> orderDetails =  orderDetailRepository.findAllByOrderId(order.getId());
			Payment payment = paymentRepository.findPaymentByOrderId(order.getId());
			response.add(new OrderResponse(order, orderDetails,payment));
		}
		return response;
	}

	 
	public List<OrderResponse> getAllCustomerOrders(int userId) {
		List<Order> orders = orderRepository.findAllOrdersByUserId(userId);
		
		List<OrderResponse> response = new ArrayList<>();
		
		for (Order order : orders) {
			List<OrderDetail> orderDetails =  orderDetailRepository.findAllByOrderId(order.getId());
			Payment payment = paymentRepository.findPaymentByOrderId(order.getId());
			response.add(new OrderResponse(order, orderDetails,payment));
		}
		return response;
	}
	
	
	 
	public void assignDeliveryBoy(int userId, int orderId) {
		Order order = orderRepository.findById(orderId).get();
		User user = userRepository.findById(userId).get();
		order.setDeleveryBoy(user);
		order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
		return;
	}

	 
	public void updateOrderStatus(int orderId, String status) {
		Order order = orderRepository.findById(orderId).get();
		order.setOrderStatus(OrderStatus.valueOf(status));
		order.setStatusUpdateDate(LocalDateTime.now());
		if(status.equals("DELIVERED")) {
			Payment payment = paymentRepository.findPaymentByOrderId(orderId);
			payment.setStatus(PaymentStatus.COMPLETED);
		}
	return;
	
	}

}
