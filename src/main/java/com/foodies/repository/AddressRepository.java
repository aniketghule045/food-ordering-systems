package com.foodies.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.foodies.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	List<Address> getAllAddressesByUserId(@Param("id") Integer userId);

	@Query(value = "Select * from addresses where user_id=:id", nativeQuery = true)
	Optional<Address> findByUserId(@Param("id") Integer userId);

}
