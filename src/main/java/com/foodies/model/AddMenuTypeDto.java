package com.foodies.model;



import org.springframework.beans.BeanUtils;

import com.foodies.entity.MenuType;

public class AddMenuTypeDto {

	private int id;
	
	private String menuType;

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getMenuType() {
		return menuType;
	}


	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}


	AddMenuTypeDto(){
		
	}
	
	
	public AddMenuTypeDto(int id, String menuType) {
		super();
		this.id = id;
		this.menuType = menuType;
	}


	@Override
	public String toString() {
		return "AddMenuTypeDto [id=" + id + ", menuType=" + menuType + "]";
	}
	
	public static AddMenuTypeDto fromEntity(MenuType entity) {
		AddMenuTypeDto dto=new AddMenuTypeDto();
		System.out.println(entity.getMenuList());
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
}
