package com.foodies.model;

import javax.persistence.Column;

import org.springframework.beans.BeanUtils;

import com.foodies.entity.Menu;

public class AddMenuDto {

	
	int id;
	int menu_type_id;
	String menuName;
	double price;
	String imageName;
	String description;
	
	public AddMenuDto() {
		
	}
	
	public AddMenuDto(int id, int menu_type_id, String menuName, double price, String imageName, String description) {
		super();
		this.id = id;
		this.menu_type_id = menu_type_id;
		this.menuName = menuName;
		this.price = price;
		this.imageName = imageName;
		this.description = description;
	}


	@Override
	public String toString() {
		return "AddMenuDto [id=" + id + ", menu_type_id=" + menu_type_id + ", menuName=" + menuName + ", price=" + price
				+ ", imageName=" + imageName + ", description=" + description + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMenu_type_id() {
		return menu_type_id;
	}

	public void setMenu_type_id(int menu_type_id) {
		this.menu_type_id = menu_type_id;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static AddMenuDto fromEntity(Menu entity) {
		AddMenuDto dto=new AddMenuDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

}
