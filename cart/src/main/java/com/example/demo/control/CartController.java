package com.example.demo.control;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modell.Cart;
import com.example.demo.modell.Product;
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
	//function to update product quantity and cart amount
	@PutMapping("/{name}/{prodname}/{qty}")
	public Optional<Cart> updateProduct(@PathVariable String name,@PathVariable String prodname,@PathVariable int qty)
	{
		try
		{
			Optional<Cart>c=cartService.updateProd(name,prodname,qty);
			return c;
		}
		catch(Exception e)
		{	
			System.out.print(e);
			return null;
		}
	}
	
	//to add a new product for given customer
		@PutMapping("/{name}")
		public String addProduct(@PathVariable String name,@RequestBody Product p)
		{
			cartService.addProductByName( name,p);
			return "checking";
		}
	
	@DeleteMapping("/{name}/{prodname}")
	public String deleteProd(@PathVariable String name,@PathVariable String prodname)
	{
		cartService.deleteByName(name, prodname);
		return prodname+" deleleted successfully";
	}
	
	

}
