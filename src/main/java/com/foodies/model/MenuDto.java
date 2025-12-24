package com.foodies.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class MenuDto{
	private double price;
	private String description;
	
	public MenuDto() {
	}

	public MenuDto(double price, String description) {
		this.price = price;
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "MenuDto [price=" + price + ", description=" + description + "]";
	}
	
	
	
}
