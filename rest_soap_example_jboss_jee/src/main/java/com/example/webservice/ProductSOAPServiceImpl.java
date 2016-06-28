package com.example.webservice;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

import com.example.dao.ProductDao;
import com.example.entity.Product;

@WebService(targetNamespace = "http://www.test.pl/", serviceName = "ProductWebService")
public class ProductSOAPServiceImpl implements ProductSOAPService, Serializable {

	private static final long serialVersionUID = 90354625597036448L;
	
	@EJB	
	ProductDao repository;
	
	@Inject
	private transient Logger log;
	

	@WebMethod
	public List<Product> getAllProducts() {
		log.info("getAllProducts");
		return repository.getAllProducts();
	}

	@WebMethod
	public Long createProduct(Product product) {
		log.info("createProduct o nazwie = "+product.getName());
		product=repository.createProduct(product);
		return product.getId();
	}

}
