package com.example.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.example.dao.ProductRepository;

public class ProductListFactory {
	private final ProductRepository repository;

	public ProductListFactory(ProductRepository repository) {
		this.repository = repository;
	}

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
