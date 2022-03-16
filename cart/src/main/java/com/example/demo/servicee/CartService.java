package com.example.demo.servicee;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.example.demo.modell.Cart;
import com.example.demo.modell.Product;
import com.example.demo.repos.CartRepository;



@Service
public class CartService {
	
	   @Autowired
	   public CartRepository cartRepo;
	   
	   public MongoTemplate mt;
	   
	
	public void saveUser(Cart cart) {
		List<Double>amt= new ArrayList<>();
		List<Product>products=cart.getProducts();
		products.forEach(p -> {
			double price=p.getPrice();
			int quantity=p.getQuantity();
			amt.add(price*quantity);
			
			
		});
		 double tot_amount = 0;
	        for (Double i : amt)
	        	tot_amount += i;
	        
	        cart.setTot_amt(tot_amount);
		
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
	

	 public Optional<Cart> updateProd(String name, String prodname, int qty) {
	        
	        Optional<Cart> cart=cartRepo.findByName(name);
	        Cart c=cart.get();
	        List<Product>products=c.getProducts();
	        for(Product p:products)
	        {
	            if (p.getProdname().equals(prodname)) {
	                p.setQuantity(qty);
	                break;
	                
	             }
	       
	       
	        }
	        
	        c.setProducts(products);
	        
		        
	        c.setTot_amt(computeTotalAmount( c)); // computeTotalAmount( c) this function definition is at end
	        
	        cart=Optional.of(c);
	        cartRepo.save(cart.get());
	        
			return cart;
	        
	 }
	 
	//function to delete all cart info of a user
		public void deleteByName(String name,String  prodname)
		{	
			
			Optional<Cart> cart=cartRepo.findByName(name);
			Cart c=cart.get();
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
			 
		    cart=Optional.of(c);
			cartRepo.save(cart.get());
				
			 
			
			
		}

		public void addProductByName(String name, Product prod) {
			Optional<Cart> cart=cartRepo.findByName(name);
			Cart c=cart.get();
			List<Product>products=c.getProducts();
			products.add(prod);
			c.setProducts(products);
			
			
		     c.setTot_amt(computeTotalAmount(c));
			 cart=Optional.of(c);
			cartRepo.save(cart.get());
		}
		
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

}




