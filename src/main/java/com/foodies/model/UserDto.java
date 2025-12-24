package com.foodies.model;

import org.springframework.beans.BeanUtils;

import com.foodies.entity.User;

public class UserDto {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNo;
	
	public UserDto() {

	}
	
	public UserDto(int id, String firstName, String lastName, String email, String phoneNo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNo=" + phoneNo + "]";
	}
	
	public static UserDto fromEntity(User entity) {
		UserDto dto=new UserDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
		
	}
}
