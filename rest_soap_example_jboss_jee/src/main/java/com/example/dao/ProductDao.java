package com.example.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Local;

import com.example.entity.Product;

@Local
public interface ProductDao {
	Optional<Product> getProductById(Long id);
	
	Optional<Product> getProductByName(String name);

	Product createProduct(Product product);
    
    List<Product> getAllProducts();
    
    Long deleteProductById(Long id);
    
    Product updateProduct(Long id,Product product);
    
    public void deleteAllProduct();

}
