package com.example.demo.modell;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Document("cart")
public class Cart {
	
	@Id
	public String id;
	
	@Indexed(unique=true)
	public String uname;
	
	public List<Product>products;
	public double tot_amt;

}
