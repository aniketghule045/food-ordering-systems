 package com.foodies.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodies.entity.Menu;
import com.foodies.entity.MenuType;
import com.foodies.model.AddMenuDto;
import com.foodies.model.AddMenuInDto;
import com.foodies.model.MenuDto;
import com.foodies.model.ResponseDto;
import com.foodies.serviceImpl.MenuServiceImpl;
import com.foodies.serviceImpl.MenuTypeServiceImpl;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/menu")
@Tag(name = "Menu's APIs")
public class MenuController {
	@Autowired
	private MenuServiceImpl menuService;
	@Autowired
	private MenuTypeServiceImpl menuTypeService;

	

	@PostMapping("/addMenu/{menuTypeid}")
	public ResponseEntity<?> save(AddMenuInDto addMenuInDto) {
		System.out.println(addMenuInDto);
		MenuType menuType = menuTypeService.findMenuType(addMenuInDto.getId());

		Menu menu = AddMenuInDto.toEntity(addMenuInDto);
		menu = menuService.saveMenu(menu, addMenuInDto.getImageName());
		return ResponseDto.success(menu);
	}

	

	@PostMapping("/add/{menuTypeid}")
	public ResponseEntity<?> addMenu(AddMenuInDto menuInDto, @PathVariable int menuTypeid) {
		System.out.println(menuTypeid);
		Menu menu = AddMenuInDto.toEntity(menuInDto);
		menu = menuService.addMenu(menu, menuInDto.getImageName(), menuTypeid);
		return new ResponseEntity<>(new ResponseDto<Menu>("Success", menu), HttpStatus.CREATED);
	}

	


	@GetMapping("/all")
	public ResponseEntity<?> findAllMenu() {
		List<Menu> list = menuService.findAll();
		System.out.println(list);
	
		List<AddMenuDto> result = new ArrayList<AddMenuDto>();
		for (Menu menu : list)
			result.add(AddMenuDto.fromEntity(menu));
		System.out.println(result);
		return ResponseDto.success(result);
	}
	@GetMapping("/by/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {

		System.out.println(id + "  menuTypeid");
		List<Menu> list = menuService.findAllByType(id);
		
		return ResponseDto.success(list);
	}

	@GetMapping("/all/{menuTypeid}")
	public ResponseEntity<?> findAllMenuByType(@PathVariable int menuTypeid) {
		System.out.println(menuTypeid + "  menuTypeid");
		System.out.println(menuTypeid + "  menuTypeid");
		List<Menu> list = menuService.findAllByType(menuTypeid);
		
		return ResponseDto.success(list);
	}

	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable int id) {
		String message = menuService.deleteMenu(id);
		return new ResponseEntity<>(new ResponseDto<>("success", message), HttpStatus.CREATED);
	}


	
	@PostMapping("/edit")
	public ResponseEntity<ResponseDto<Menu>> editMenu(@RequestBody Menu menuDto) {
		
	    Menu menu = menuService.editMenu(menuDto, menuDto.getId());
	    return new ResponseEntity<>(new ResponseDto<>("Success", menu), HttpStatus.OK);
	}


}
