package com.foodies.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodies.repository.CartRepository;
import com.foodies.repository.MenuRepository;
import com.foodies.repository.UserRepository;
import com.foodies.entity.Cart;
import com.foodies.entity.Menu;
import com.foodies.entity.User;


@Service
@Transactional
public class CartServiceImpl {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private CartRepository cartRepository;
	


	public String addItemToCart(Integer MenuId, Integer quantity, Integer userId) {
		User customer = userRepository.findById(userId).get();
		Menu menu = menuRepository.findById(MenuId).get();
		cartRepository.save(new Cart(quantity, menu, customer));
		return quantity+" "+menu.getMenuName()+" added to cart";
	}



	public List<Cart> getAllCartContents(Integer userId) {
		return cartRepository.findAllItemsByUser(userId);
	}



	public String updateQuantity(Integer cartId, Integer quantity) {
		Cart cartItem = cartRepository.findById(cartId).get();
		cartItem.setQuantity(quantity);
		return "success";
	}



	public Optional<Cart> findById(Integer cartId) {
		return cartRepository.findById(cartId);
	}



	public void deleteFromCart(Integer cartId) {
		boolean exists= cartRepository.existsById(cartId);
		System.out.println("in remove cart item  " + cartId);
		cartRepository.deleteById(cartId);
	}



	public void deleteAllFromCart(int userId) {
		cartRepository.deleteAll(cartRepository.findAllItemsByUser(userId));
	}

}
