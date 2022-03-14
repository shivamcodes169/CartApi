package com.example.demo.modell;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
@Document("cart")
public class Cart {
	
	@Id
	public String Id;
	
	@Indexed(unique=true)
	public String uname;
	public double tot_amt;
	public List<Product>products;

}