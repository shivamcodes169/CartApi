package com.example.demo.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.modell.Cart;

@Repository

public interface CartRepository extends MongoRepository<Cart,String>{
	
	

}
