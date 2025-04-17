package com.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Admin;


@Repository
public interface adminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByEmailAndPassword(String email, String pass);

	

	
 
}
