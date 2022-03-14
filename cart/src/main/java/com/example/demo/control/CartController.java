package com.example.demo.control;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modell.Cart;
import com.example.demo.repos.CartRepository;
import com.example.demo.servicee.CartService;


@RequestMapping("/usercart")
@RestController
public class CartController {
	
	@Autowired
	public CartRepository repo;
	
	@Autowired
    private CartService cartService;
	
	@PostMapping("/post")
	public void add(@RequestBody Cart cart)
	{
		cartService.saveUser(cart);
	}
	
	@GetMapping("/getall")
	public List<Cart> sendDetails()
	{
		return cartService.getdata();
	}
	
	@GetMapping("/getbyname/{name}")
	public Optional<Cart> getDetailsByName(@PathVariable String name )
	{
		try
		{
		return cartService.getDetailsByName(name);
		}
		catch(Exception e)
		{
			
		    
		    return Optional.empty();
		}
		
	}
	
	
	

}
