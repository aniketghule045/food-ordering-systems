package com.foodies.entity;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name = "cart")
public class Cart extends BaseEntity {

	private int quantity;
	

	@ManyToOne
	@JoinColumn(name = "menu_id")
	private Menu selectedMenu;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User currentUser;

	public Cart() {

	}


	public Cart(int quantity, Menu selectedMenu, User currentUser) {
		super();
		this.quantity = quantity;
		this.selectedMenu = selectedMenu;
		this.currentUser = currentUser;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Menu getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(Menu selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	@Override
	public String toString() {
		return "Cart [quantity=" + quantity + ", selectedMenu=" + selectedMenu + ", currentUser=" + currentUser + "]";
	}

	

}
