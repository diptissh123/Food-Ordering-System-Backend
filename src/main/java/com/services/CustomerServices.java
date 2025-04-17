package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Repository.CustomerRepository;
import com.Repository.FoodRepository;
import com.model.Admin;
import com.model.Customer;




@Service
public class CustomerServices {
	@Autowired
	private CustomerRepository repo;

	public Customer saveCustomer(Customer customer) {
		
		return repo.save(customer);
	}

	public Customer ValidateCustomer(Customer customer) {
		
		String email=customer.getEmail();
		String pass=customer.getPassword();
		
		Optional<Customer> opt=repo.findByEmailAndPassword(email, pass);
		if(opt.isPresent()) 
		{
		
		return opt.get();
		}
		else
		{
			return new Customer();
			
		}
	}

	public ResponseEntity<List<Customer>> getAllCustomer() {
	    return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<Customer> GetCustomerById(Long custId) {
		Optional<Customer> optional=repo.findById(custId);
		if(optional.isPresent()) {
		return new ResponseEntity<Customer>(optional.get(),HttpStatus.ACCEPTED);}
		else {
			return new ResponseEntity<Customer>(optional.get(),HttpStatus.BAD_REQUEST);
		}
		
	}

	

	

	
	
}
