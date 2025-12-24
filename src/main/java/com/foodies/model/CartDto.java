package com.foodies.model;

public class CartDto {
	private int MenuId;
	private int userId;
	private int quantity;
	
	public CartDto() {
	}

	public CartDto(int MenuId, int userId, int quantity) {
		this.MenuId = MenuId;
		this.userId = userId;
		this.quantity = quantity;
	}

	

	public int getMenuId() {
		return MenuId;
	}

	public void setMenuId(int menuId) {
		MenuId = menuId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
