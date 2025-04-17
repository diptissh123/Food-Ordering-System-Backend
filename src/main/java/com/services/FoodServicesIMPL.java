package com.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.Repository.FoodRepository;
import com.model.FoodItem;
import com.model.Order;
@Service
public class FoodServicesIMPL implements FoodServices{
	
	@Autowired
	private FoodRepository repo;

	private static final String UPLOAD_DIR = "C:\\Users\\DELL\\OneDrive\\Desktop\\ReactJS\\food-ordering-app\\src\\Component\\images"; // Change this path as needed

	@Override
	public ResponseEntity<List<FoodItem>> GetAllData() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<FoodItem>> (repo.findAll(), HttpStatus.OK);
	}



	@Override
	public ResponseEntity<FoodItem> saveFoodItem(FoodItem foodItem) {
		
		return new ResponseEntity<FoodItem>( repo.save(foodItem),HttpStatus.ACCEPTED);
	}



	

	 public FoodItem addFoodItem(String foodName, double foodPrice, String foodType, String foodDescription, MultipartFile image) {
	        try {
	            byte[] imageData = image.getBytes();  // Convert image to byte array

	            // Create FoodItem object
	            FoodItem foodItem = new FoodItem(foodName, foodPrice, foodType, foodDescription, imageData);

	            // Save to database
	            return repo.save(foodItem);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    }



	@Override
	public List<FoodItem> GetByFoodType(String foodType) {
		// TODO Auto-generated method stub
		return repo.findByfoodType(foodType);
	}



	@Override
	public FoodItem getFoodItemById(Long id) {
		// TODO Auto-generated method stub
		return repo.getById(id);
	}



	@Override
	public List<FoodItem> searchFoodByName(String foodName) {
		// TODO Auto-generated method stub
		return  repo.findByfoodNameContainingIgnoreCase(foodName);
	}

	

	

	

	

	

	
	  
	
}
