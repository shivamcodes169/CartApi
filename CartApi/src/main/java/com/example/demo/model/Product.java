package com.example.demo.model;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Product {
	
	public String prodname;
	public int quantity;
	public double price;

}
