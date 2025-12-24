package com.foodies.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodies.entity.OrderDetail;

@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {

	@Query("Select od from OrderDetail od where od.currentOrder.id=:id")
	List<OrderDetail> findAllByOrderId(@Param("id") Integer id);

}
