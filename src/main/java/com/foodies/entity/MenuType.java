package com.foodies.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "menu_types")
public class MenuType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "menu_type")
	private String menuType;

	@OneToMany(mappedBy = "menutype", fetch = FetchType.LAZY)
	// @JsonIgnoreProperties("Menu")
	private List<Menu> menuList;

	public MenuType(int id, String menuType, List<Menu> menuList) {
		super();
		this.id = id;
		this.menuType = menuType;
		this.menuList = menuList;
	}

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

	@JsonIgnore
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public MenuType() {
		this.menuList = new ArrayList<Menu>();
	}

	@Override
	public String toString() {
		return "MenuType [id=" + id + ", menuType=" + menuType + ", menuList=" + menuList + "]";
	}

	public void addMenu(Menu menu) {
		this.menuList.add(menu);
		menu.setMenutype(this);
	}

}
