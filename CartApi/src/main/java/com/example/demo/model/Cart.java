package com.example.demo.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Document("ShopCart")
public class Cart {
	
	@Id
	public String id;
	
	@Indexed(unique=true)
	public String uname;
	
	public List<Product>products;
	public double tot_amt;

}
