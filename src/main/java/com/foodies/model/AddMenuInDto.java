package com.foodies.model;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.foodies.entity.Menu;

public class AddMenuInDto {

		int id;
		String menuName;
		double price;
		int menu_type_id;
		MultipartFile imageName;
		String description;
		
		public AddMenuInDto() {
			
		}

		public AddMenuInDto(int id, String menuName, double price, MultipartFile imageName, String description) {
			super();
			this.id = id;
			this.menuName = menuName;
			this.price = price;
			this.imageName = imageName;
			this.description = description;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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
		public void setMenu_type_id(int menu_type_id) {
			this.menu_type_id = menu_type_id;
		}
		
		public int getMenu_type_id() {
			return menu_type_id;
		}
		
		public MultipartFile getImageName() {
			return imageName;
		}

		public void setImageName(MultipartFile imageName) {
			this.imageName = imageName;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "AddMenuDto [id=" + id + ", menuName=" + menuName + ", price=" + price + ", imageName=" + imageName
					+ ", description=" + description + "]";
		}
		
		public static Menu toEntity(AddMenuInDto dto) {
			Menu entity=new Menu();
			BeanUtils.copyProperties(dto,entity,"imageName");
			return entity;
		}
		
		

	}
