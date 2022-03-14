package com.example.demo.repos;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.modell.Cart;

@Repository

public interface CartRepository extends MongoRepository<Cart,String>{
	
	@Query("{uname:?0}")
	Optional<Cart> findByName(String name);
	

	
	

}
