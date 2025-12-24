package com.foodies.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodies.daos.CartDao;
import com.foodies.daos.MenuDao;
import com.foodies.daos.UserDao;
import com.foodies.entity.Cart;
import com.foodies.entity.Menu;
import com.foodies.entity.User;


@Service
@Transactional
public class CartServiceImpl {
	@Autowired
	private UserDao userDao;
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private CartDao cartDao;
	


	public String addItemToCart(Integer MenuId, Integer quantity, Integer userId) {
		User customer = userDao.findById(userId).get();
		Menu menu = menuDao.findById(MenuId).get();
		cartDao.save(new Cart(quantity, menu, customer));
		return quantity+" "+menu.getMenuName()+" added to cart";
	}



	public List<Cart> getAllCartContents(Integer userId) {
		return cartDao.findAllItemsByUser(userId);
	}



	public String updateQuantity(Integer cartId, Integer quantity) {
		Cart cartItem = cartDao.findById(cartId).get();
		cartItem.setQuantity(quantity);
		return "success";
	}



	public Optional<Cart> findById(Integer cartId) {
		return cartDao.findById(cartId);
	}



	public void deleteFromCart(Integer cartId) {
		boolean exists=cartDao.existsById(cartId);
		System.out.println("in remove cart item  " + cartId);
		cartDao.deleteById(cartId);
	}



	public void deleteAllFromCart(int userId) {
		cartDao.deleteAll(cartDao.findAllItemsByUser(userId));
	}

}
