package com.foodies.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodies.entity.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {

	@Query("Select c from Cart c join fetch c.selectedMenu where c.currentUser.id=:id")
	List<Cart> findAllItemsByUser(@Param("id") Integer userId);

}
