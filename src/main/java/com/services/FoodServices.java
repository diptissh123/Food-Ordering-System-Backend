package com.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.model.FoodItem;
import com.model.Order;

public interface FoodServices {


	



	ResponseEntity<List<FoodItem>> GetAllData();

	ResponseEntity<FoodItem> saveFoodItem(FoodItem foodItem);

	FoodItem addFoodItem(String foodName, double foodPrice, String foodType, String foodDescription,
			MultipartFile image);

	List<FoodItem> GetByFoodType(String foodType);

	FoodItem getFoodItemById(Long id);

	List<FoodItem> searchFoodByName(String foodName);



	
	

	

}
