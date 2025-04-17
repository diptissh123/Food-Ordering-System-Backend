package com.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

@Entity
@Table(name = "orders")  // Avoid using reserved keywords like `order`
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
   
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false) // This links Order to Customer
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference // Forward reference
    private List<OrderItem> items;

    private double totalAmount;
    private String paymentMethod;
    private String status;
    
    
    
	public Order() {
		super();
	}
	public Order(Long orderId, Customer customer, List<OrderItem> items, double totalAmount, String paymentMethod,
			String status) {
		super();
		this.orderId = orderId;
		this.customer = customer;
		this.items = items;
		this.totalAmount = totalAmount;
		this.paymentMethod = paymentMethod;
		this.status = status;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    // Getters and Setters
 
}
