package com.example.demo.repository;



import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.model.Cart;

import reactor.core.publisher.Mono;

public interface CartRepository extends ReactiveMongoRepository<Cart,String> {
	
	@Query("{uname:?0}")
	Mono<Cart> findByName(String name);

}
