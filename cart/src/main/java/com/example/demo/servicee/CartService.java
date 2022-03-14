package com.example.demo.servicee;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.modell.Cart;
import com.example.demo.repos.CartRepository;

@Service
public class CartService {
	
	@Autowired
	   public CartRepository cartRepo;
	
	
	public void saveUser(Cart cart) {
        
       cartRepo.save(cart);        
    }
     
	public List<Cart> getdata()
	{
		return cartRepo.findAll();
		
	}

	public Optional<Cart> getDetailsByName(String name) {
		
		
		//Optional<Cart> savedCartData= Optional.ofNullable(cartRepo.findByName(name).orElseThrow(()-> new RuntimeException(String.format("Not found %s",name))));
		
		Optional<Cart> savedCartData=Optional.of(cartRepo.findByName(name).orElseThrow(()->new RuntimeException(String.format("Not found %s",name))));
    	return savedCartData;
    	
		
		 
	}

}




