package com.foodies.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodies.repository.Menu_TypeRepository;
import com.foodies.entity.Menu;
import com.foodies.entity.MenuType;


@Transactional
@Service
public class MenuTypeServiceImpl {
	 
	
	@Autowired
	private Menu_TypeRepository menuTypeRepository;
	
	
	@PostConstruct
	public void setDefaultData() {
		MenuType menuType1=new MenuType();
		menuType1.setId(1);
		menuType1.setMenuType("Nonveg");
		MenuType menuType2=new MenuType();
		menuType2.setId(2);
		menuType2.setMenuType("Veg");
		MenuType menuType3=new MenuType();
		menuType3.setId(3);
		menuType3.setMenuType("Chinees");
		MenuType menuType4=new MenuType();
		menuType4.setId(4);
		menuType4.setMenuType("SouthIndian");
		MenuType menuType5=new MenuType();
		menuType5.setId(5);
		menuType5.setMenuType("Sweets");
		MenuType menuType6=new MenuType();
		menuType6.setId(6);
		menuType6.setMenuType("Thali");
		MenuType menuType7=new MenuType();
		menuType7.setId(7);
		menuType7.setMenuType("Rice");
		MenuType menuType8=new MenuType();
		menuType8.setId(8);
		menuType8.setMenuType("Roti");
		List<MenuType> list=new ArrayList<>();
		
		list.add(menuType1);
		list.add(menuType2);
		list.add(menuType3);
		list.add(menuType4);
		list.add(menuType5);
		list.add(menuType6);
		list.add(menuType7);
		list.add(menuType8);
		menuTypeRepository.saveAll(list);
		
	}
	 
	public List<MenuType> getAllMenuTypes() {
		List<MenuType> list= menuTypeRepository.findAll();
		return list;			
	}
	
	 
	public MenuType addOrEditCategory(MenuType menuType1) {
		
		return menuTypeRepository.save(menuType1);
	}
	 
	public List<MenuType> findAll() {
		return menuTypeRepository.findAll();
	}

	 
	public Menu addMenu(Menu menu, int id) {
		Optional<MenuType> menuType = menuTypeRepository.findById(id);
		if(menuType.isPresent()) {
			menuType.get().addMenu(menu);
			return menu;
		}
		return null;
	}

	 
	public MenuType findMenuType(int id) {
		Optional<MenuType> menutype = menuTypeRepository.findById(id);
		return menutype.orElse(null);
	}

	 
	public List<Menu> findByMenuType(int id) {
		Optional<MenuType> menuType1 = menuTypeRepository.findById(id);
		MenuType menutype = menuType1.orElse(null);
		List<Menu> menulist = menutype.getMenuList();
		return menulist;
	}
	
	
}
