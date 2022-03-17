package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




@RequestMapping("/usercart")
@RestController
public class CartController {
	
	@Autowired
    private CartService cartService;
	
	@PostMapping("/post")
	public Mono<Cart> add(@RequestBody Cart cart)
	{
		return cartService.saveUser(cart);
	}
	@GetMapping("/getall")
	public Flux<Cart> getAllCartinfo()
	{
		return cartService.getAllDetails();
	}
	@GetMapping("/getbyname/{name}")
	public Mono<Cart> getDetails(@PathVariable String name)
	{
		return cartService.getDetailsByName(name);
	}
	@PutMapping("/update/{name}/{prodname}/{qty}")
	public Mono<Cart> updateQty(@PathVariable String name,@PathVariable String prodname,@PathVariable int qty)
	{
		return cartService.updateProd(name, prodname, qty);
	}
	
	@PutMapping("/update/{name}")
	public Mono<Cart> addProduct(@PathVariable String name,@RequestBody Product prod)
	{
		return cartService.addProd(name,prod);
	}
	@DeleteMapping("/delete/{name}/{prodname}")
	public Mono<Cart> deleteProd(@PathVariable String name,@PathVariable String prodname)
	{
		return cartService.deleteByName(name, prodname);
		
	}

}
