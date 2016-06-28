package com.example.dao;

import java.util.List;
import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.example.entity.Product;
import com.exception.ProductNotFoundException;

@Stateless	
public class ProductRepository implements ProductDao {

	@Inject
	private EntityManager em;

	@Override
	public Optional<Product> getProductByName(String name) {
		TypedQuery<Product> query = em.createNamedQuery(Product.findProductByName, Product.class); 
		query.setParameter("name", "%"+name.toLowerCase()+"%");
		return Optional.ofNullable(query.getSingleResult());

	}
	
	@Override
	public Optional<Product> getProductById(Long id) {
		TypedQuery<Product> query = em.createNamedQuery(Product.findProductById, Product.class); 
		query.setParameter("id", id);
		return Optional.ofNullable(query.getSingleResult());
	}

	@Override
	@Transactional
	public Product createProduct(Product product){
		em.persist(product);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		TypedQuery<Product> query = em.createNamedQuery(Product.findAllProducts, Product.class); 
		return query.getResultList();
	}

	@Override
	@Transactional
	public Long deleteProductById(Long id) {
		Query query = em.createNamedQuery(Product.deleteProductById);
		query.setParameter("id", id);
		return (Long) query.getSingleResult();
	}

	@Override
	@Transactional
	public Product updateProduct(Long id, Product product) {
		Product product1=getProductById(id).orElseThrow(()->new ProductNotFoundException(id));
		product1.setDescription(product.getDescription());
		product1.setImageUrl(product.getImageUrl());
		product1.setName(product.getName());
		product1.setPrice(product.getPrice());
		em.merge(product1);
		return product1;
	}
	
	@Override
	@Transactional
	public void deleteAllProduct() {
		Query query = em.createNamedQuery(Product.deleteAllProduct);
		query.executeUpdate();
	}
}
