package com.example.dao;

import java.util.List;

import javax.ejb.Local;

import com.example.entity.Product;

@Local
public interface ProductDao {
	public Product getProductById(Long id);
	
	Product getProductByName(String name);

	Product createProduct(Product product);
    
    List<Product> getAllProducts();
    
    Long deleteProductById(Long id);
    
    Product updateProduct(Long id,Product product);
    
    public void deleteAllProduct();

}
