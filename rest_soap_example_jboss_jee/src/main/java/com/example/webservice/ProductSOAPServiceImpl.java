package com.example.webservice;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.dao.ProductRepository;
import com.example.dao.ProductService;
import com.example.entity.Product;

@WebService(targetNamespace = "http://www.test.pl/", serviceName = "ProductWebService")
public class ProductSOAPServiceImpl implements ProductSOAPService, Serializable {

	private static final long serialVersionUID = 90354625597036448L;
	
	@Inject
	ProductRepository repository;
	
	@Inject
	ProductService service;
	

	@WebMethod
	public List<Product> getAllProducts() {
		return repository.getAllProducts();
	}

	@WebMethod
	public Long createProduct(Product product) {
		product=repository.createProduct(product);
		return product.getId();
	}

}
