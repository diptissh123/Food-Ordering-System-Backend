package com.model;

import java.util.Base64;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonBackReference 
    private Order order;

    private Long foodId;
    private String name;
    private int quantity;
    private double price;
    private double total;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imageBytes;

    @Transient
    private String image;

    public void setImage(byte[] imageBytes2) {
        if (imageBytes2 != null && imageBytes2.length > 0) { // ✅ Fixed condition
            this.imageBytes = imageBytes2; // ✅ Directly assign bytes (NO decoding needed)
        }
    }

    public String getImage() {
        return imageBytes != null ? "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes) : null;
    }
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Long getFoodId() {
		return foodId;
	}
	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	

    // Getters and Setters
    
}
