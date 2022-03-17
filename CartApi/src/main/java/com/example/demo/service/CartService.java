package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import com.mongodb.client.result.UpdateResult;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


//new reactive service class
@Service
public class CartService {
	   @Autowired
	   public CartRepository cartRepo;
	  
	   
	   //function to compute total amount
	   public double  computeTotalAmount(Cart c)
		{
			List<Product>products=c.getProducts();
			List<Double>amt= new ArrayList<>();
	        products.forEach(p -> {
				double price=p.getPrice();
				int quantity=p.getQuantity();
				amt.add(price*quantity);
			});
	        
			 double tot_amount = 0;
		        for (Double i : amt)
		        	tot_amount += i;
		        
		    
		     return tot_amount;
		}
	   
	   // to create a new user
	   public Mono<Cart> saveUser(Cart cart) {
			
			cart.setTot_amt(computeTotalAmount(cart));
	        return cartRepo.save(cart);
	      
	    }
	   public Flux<Cart> getAllDetails()
	   {
		   return cartRepo.findAll();
	   }
	   //to find cart details by user name
	   
	   public Mono<Cart> getDetailsByName(String name)
	   {
		   Mono<Cart> savedUser=cartRepo.findByName(name);
		   return savedUser;
	   }
	   
	   //update Product quantity of given user
	   public Mono<Cart> updateProd(String name, String prodname, int qty)
	   {
		   

		   
		   Mono<Cart>cart=cartRepo.findByName(name);
		   Cart c=cart.block();
		   
		   List<Product>products=c.getProducts();
	        for(Product p:products)
	        {
	            if (p.getProdname().equals(prodname)) {
	                p.setQuantity(qty);
	                break;
	                
	             }
	       
	       
	        }
	       c.setProducts(products); 
		   c.setTot_amt(computeTotalAmount(c));
		   
		   return cartRepo.save(c);
	   }
	   
	   //delete Product of given user
	   
	   public Mono<Cart> deleteByName(String name,String  prodname)
	   {
		   Mono<Cart> cart=cartRepo.findByName(name);
		   Cart c= cart.block();
			List<Product>products=c.getProducts();
			for(Product p:products)
			{
				if (p.getProdname().equals(prodname)) {
	                products.remove(p);
	                break;
	                
	             }
			}
			
			c.setProducts(products);
			
			//set new tot_amt    
		    c.setTot_amt(computeTotalAmount( c));
			
		    
			return cartRepo.save(c);
	   }

	public Mono<Cart> addProd(String name, Product prod) {
		Mono<Cart> cart=cartRepo.findByName(name);
		Cart c=cart.block();
		List<Product>products=c.getProducts();
		products.add(prod);
		c.setProducts(products);
		
		
	     c.setTot_amt(computeTotalAmount(c));
	     return cartRepo.save(c);
		
	}
	   
}
