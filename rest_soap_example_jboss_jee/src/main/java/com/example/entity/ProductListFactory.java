package com.example.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.example.dao.ProductDao;

@Stateless
public class ProductListFactory {
	
	@EJB
	ProductDao repository;

	public List<Product> buildNumberOfProductsAndSave(int numberOfProducts) {
		List<Product> productList = new ArrayList<>();
		IntStream.range(0, numberOfProducts).forEachOrdered(number -> {
			Product product = new ProductBuilder(String.format("product_%s", number)).build();
			repository.createProduct(product);
			productList.add(product);
		});
		return productList;
	}

}
