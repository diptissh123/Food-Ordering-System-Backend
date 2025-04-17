package com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "food_items")
public class FoodItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;
    private double foodPrice;
    private String foodType;
    private String foodDescription;
    

    @Lob  // This tells Hibernate to store large binary data (BLOB)
    @Column(columnDefinition = "LONGBLOB")  // Use "LONGBLOB" for large images
    private byte[] image; 

    // Constructors
    public FoodItem() {}

    public FoodItem(String foodName, double foodPrice, String foodType, String foodDescription, byte[] image) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodType = foodType;
        this.foodDescription = foodDescription;
        this.image = image;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFoodName() { return foodName; }
    public void setFoodName(String foodName) { this.foodName = foodName; }

    public double getFoodPrice() { return foodPrice; }
    public void setFoodPrice(double foodPrice) { this.foodPrice = foodPrice; }

    public String getFoodType() { return foodType; }
    public void setFoodType(String foodType) { this.foodType = foodType; }

    public String getFoodDescription() { return foodDescription; }
    public void setFoodDescription(String foodDescription) { this.foodDescription = foodDescription; }

    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }

	
    
}
