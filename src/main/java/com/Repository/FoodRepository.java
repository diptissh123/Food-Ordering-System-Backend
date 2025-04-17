package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Customer;
import com.model.FoodItem;


@Repository
public interface FoodRepository extends JpaRepository<FoodItem, Integer> {

	
	boolean existsById(Long id);

	List<FoodItem> findByfoodType(String foodType);

	FoodItem getById(Long id);

	List<FoodItem> findByfoodNameContainingIgnoreCase(String foodName);

	 
}
