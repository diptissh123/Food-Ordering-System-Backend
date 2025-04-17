package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Repository.CustomerRepository;
import com.Repository.OrderRepository;
import com.model.Order;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderRepository orderRepo, CustomerRepository customerRepository) {
        this.orderRepo = orderRepo;
        this.customerRepository = customerRepository;
    }

	public List<Order> getOrdersByCustomerId(Long custId) {
		// TODO Auto-generated method stub
		return orderRepo.findByCustomer_CustId(custId);
	}

	public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepo.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    public Order updateOrderStatus(Long id, String newStatus) {
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(newStatus);
            return orderRepo.save(order);
        }
        return null;
    }

    public void deleteOrder(Long id) {
        orderRepo.deleteById(id);
    }
    
}

