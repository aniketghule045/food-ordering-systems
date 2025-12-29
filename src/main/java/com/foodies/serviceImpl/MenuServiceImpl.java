package com.foodies.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.foodies.repository.MenuRepository;
import com.foodies.repository.Menu_TypeRepository;
import com.foodies.entity.Menu;
import com.foodies.entity.MenuType;
import com.foodies.util.StorageService;

@Transactional
@Service
public class MenuServiceImpl {
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private Menu_TypeRepository menu_typeRepository;
	@Autowired
	private StorageService storageService;

	 
	public List<Menu> findAll() {
			List<Menu> menuList = menuRepository.findAll();
			return menuList;
		}
	
	public Optional<Menu> findById(int id) {
		Optional<Menu> menu = menuRepository.findById(id);
		System.out.println(menu);
		System.out.println("here" + id);
		System.out.println(menu);
		return menu;
	}
	 
	public String deleteMenu(int id) {
		menuRepository.deleteById(id);
		return "Menu Deleted successfully";
	}
	 
	public Menu editMenu(Menu menuDto , int id) {
		Menu menu = menuRepository.getById(id);

		menu.setMenuName(menuDto.getMenuName());
		menu.setDescription(menuDto.getDescription());
		menu.setPrice(menuDto.getPrice());
		return menuRepository.save(menu);

	}


	 
	public Menu saveMenu(Menu menu, MultipartFile imageName) {
		String fileName = storageService.store(imageName);
		menu.setImageName(fileName);
		return menuRepository.save(menu);
	}
	 
	public Menu addMenu(Menu menu, MultipartFile imageName , int menuTypeid) {
		String image = storageService.store(imageName);
		menu.setImageName(image);
		Optional<MenuType> menuType = menu_typeRepository.findById(menuTypeid);
		if(menuType.isPresent()) {
			menuType.get().addMenu(menu);
			return menuRepository.save(menu);
		}
		System.out.println("Saving Entity");
		return menuRepository.save(menu);
	}

	public List<Menu> findAllByType(int menuTypeid) {
		List<Menu> list= menuRepository.findAllByType(menuTypeid);
		return list;
	}

}
