package com.foodies.controllers;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foodies.entity.Menu;
import com.foodies.entity.MenuType;
import com.foodies.model.AddMenuTypeDto;
import com.foodies.model.ResponseDto;
import com.foodies.serviceImpl.MenuTypeServiceImpl;

@CrossOrigin
@Controller
@RequestMapping("/menutype")
@Tag(name = "Menu Types APIs")
public class MenuTypeController {

	@Autowired
	private MenuTypeServiceImpl menuTypeService;

	@PostMapping(value = { "/add" }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addNewMenuType(@RequestBody MenuType menuType) {
		MenuType menuType1 = menuTypeService.addOrEditCategory(menuType);
		return new ResponseEntity<>(new ResponseDto<MenuType>("success", menuType1), HttpStatus.CREATED);
	}

	

	@GetMapping("/all")
	public ResponseEntity<?> findAllMenuType() {
		List<MenuType> list = menuTypeService.getAllMenuTypes();
	
		List<AddMenuTypeDto> result = new ArrayList<AddMenuTypeDto>();
		for (MenuType menu : list)
			result.add(AddMenuTypeDto.fromEntity(menu));
		
		return ResponseDto.success(result);
	}

	@GetMapping("/allMenuByType/{id}")
	public ResponseEntity<?> menuByType(@PathVariable int id) {
		List<Menu> menu = menuTypeService.findByMenuType(id);
		return new ResponseEntity<>(new ResponseDto<List<Menu>>("success", menu), HttpStatus.CREATED);
	}

}
