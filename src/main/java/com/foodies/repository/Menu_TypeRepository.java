package com.foodies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodies.entity.MenuType;

@Repository
public interface Menu_TypeRepository extends JpaRepository<MenuType, Integer> {
	
}
