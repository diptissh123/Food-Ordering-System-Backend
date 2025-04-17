package com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Repository.adminRepository;

import com.model.Admin;



@Service
public class AdminService {
	@Autowired
    private  adminRepository adminRepository;

	

	public Admin validateAdmin(Admin admin) {
		String email=admin.getEmail();
		String pass=admin.getPassword();
		
		Optional<Admin> opt=adminRepository.findByEmailAndPassword(email, pass);
		if(opt.isPresent()) 
		{
		return opt.get();
		}
		else
		{
			return new Admin();
			
		}
	}

	
	
	

	

	



	

   
	
	

}
