package com.foodies.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodies.daos.AddressDao;
import com.foodies.daos.CartDao;
import com.foodies.daos.OrderDao;
import com.foodies.daos.OrderDetailDao;
import com.foodies.daos.PaymentDao;
import com.foodies.daos.UserDao;
import com.foodies.entity.Address;
import com.foodies.entity.Cart;
import com.foodies.entity.Order;
import com.foodies.entity.OrderDetail;
import com.foodies.entity.OrderStatus;
import com.foodies.entity.Payment;
import com.foodies.entity.PaymentStatus;
import com.foodies.entity.Type;
import com.foodies.entity.User;
import com.foodies.model.OrderDto;
import com.foodies.model.OrderResponse;


@Service
@Transactional
public class OrderServiceImpl {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	@Autowired 
	private OrderDetailDao orderDetailDao;

	 
	public String placeOrderForUser(int userId, int addrId,String paymentMode) {
		// get all cart items for given user
		List<Cart> cartItems = cartDao.findAllItemsByUser(userId);
		
		double total = 0.0;
		int deliveryCharges = 25;
		for (Cart item : cartItems) {
			total += item.getQuantity() * item.getSelectedMenu().getPrice();
		}

		Address address = addressDao.findById(addrId).get();
		User customer = userDao.findById(userId).get();
		
		System.out.println("-------------------------------------");
		Order newOrder = new Order(total, LocalDateTime.now(), OrderStatus.PLACED, LocalDateTime.now(), address, customer,null);
		orderDao.save(newOrder);

		
		Payment payment = new Payment(total + deliveryCharges, LocalDateTime.now(), paymentMode.equals("COD") ? PaymentStatus.PENDING : PaymentStatus.COMPLETED, Type.valueOf(paymentMode), newOrder);
		paymentDao.save(payment);
		cartItems.forEach(item -> {
			orderDetailDao.save(new OrderDetail(item.getSelectedMenu().getPrice(), item.getQuantity(), newOrder,
					item.getSelectedMenu()));
		});
		cartDao.deleteAll(cartItems);
		return "Order Placed Successfully!";
	}

	 
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderDao.findAll();
		List<OrderResponse> response = new ArrayList<>();
		
		for (Order order : orders) {
			List<OrderDetail> orderDetails =  orderDetailDao.findAllByOrderId(order.getId());
			Payment payment = paymentDao.findPaymentByOrderId(order.getId());
			response.add(new OrderResponse(order, orderDetails,payment));
		}
		return response;
	}

	 
	public List<OrderResponse> getAllAssignedOrders(int userId) {
		List<Order> orders = orderDao.findAllOrdersByEmployeeId(userId);
		
		List<OrderResponse> response = new ArrayList<>();
		
		for (Order order : orders) {
			List<OrderDetail> orderDetails =  orderDetailDao.findAllByOrderId(order.getId());
			Payment payment = paymentDao.findPaymentByOrderId(order.getId());
			response.add(new OrderResponse(order, orderDetails,payment));
		}
		return response;
	}

	 
	public List<OrderResponse> getAllCustomerOrders(int userId) {
		List<Order> orders = orderDao.findAllOrdersByUserId(userId);
		
		List<OrderResponse> response = new ArrayList<>();
		
		for (Order order : orders) {
			List<OrderDetail> orderDetails =  orderDetailDao.findAllByOrderId(order.getId());
			Payment payment = paymentDao.findPaymentByOrderId(order.getId());
			response.add(new OrderResponse(order, orderDetails,payment));
		}
		return response;
	}
	
	
	 
	public void assignDeliveryBoy(int userId, int orderId) {
		Order order = orderDao.findById(orderId).get();
		User user = userDao.findById(userId).get();
		order.setDeleveryBoy(user);
		order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
		return;
	}

	 
	public void updateOrderStatus(int orderId, String status) {
		Order order = orderDao.findById(orderId).get();
		order.setOrderStatus(OrderStatus.valueOf(status));
		order.setStatusUpdateDate(LocalDateTime.now());
		if(status.equals("DELIVERED")) {
			Payment payment = paymentDao.findPaymentByOrderId(orderId);
			payment.setStatus(PaymentStatus.COMPLETED);
		}
	return;
	
	}

}
