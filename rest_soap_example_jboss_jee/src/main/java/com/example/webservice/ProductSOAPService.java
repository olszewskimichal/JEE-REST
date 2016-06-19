package com.example.webservice;

import java.util.List;

import javax.jws.WebService;

import com.example.entity.Product;

@WebService
public interface ProductSOAPService {
	public List<Product> getAllProducts();
	
	public Long createProduct(Product product);
}
