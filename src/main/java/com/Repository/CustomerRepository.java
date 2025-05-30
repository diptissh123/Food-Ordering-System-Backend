package com.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByEmailAndPassword(String email, String pass);

	boolean existsById(Long custId);

	

	

	

	
	

}
