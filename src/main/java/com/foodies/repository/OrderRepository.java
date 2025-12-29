package com.foodies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodies.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	@Query("Select o from Order o where o.customer.id=:id order by o.statusUpdateDate desc")
	List<Order> findAllOrdersByUserId(@Param("id") Integer userId);

	@Query("Select o from Order o where o.deleveryBoy.id=:id")
	List<Order> findAllOrdersByEmployeeId(@Param("id") Integer userId);

}
