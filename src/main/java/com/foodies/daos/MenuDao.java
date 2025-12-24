package com.foodies.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodies.entity.Menu;
@Repository
public interface MenuDao extends JpaRepository<Menu, Integer> {
	@Query(value="Select * from menu where menu_type_id=:id",nativeQuery=true)
	public List<Menu> findAllByType(@Param("id") int menuTypeid);
}
