package com.example.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.example.entity.Product;

@ApplicationScoped
public class ProductRepository implements ProductDao {

	@Inject
	private EntityManager em;

	@Override
	public Product getProductByName(String name) {
		Query query = em.createNamedQuery(Product.findProductByName);
		query.setParameter("name", "%"+name.toLowerCase()+"%");
		return (Product) query.getSingleResult();
	}
	
	@Override
	public Product getProductById(Long id) {
		return em.find(Product.class, id);
	}

	@Override
	@Transactional
	public Product createProduct(Product product){
		em.persist(product);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		return em.createNamedQuery(Product.findAllProducts).getResultList();
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
		Product product1=getProductById(id);
		product1.setDescription(product.getDescription());
		product1.setImageUrl(product.getImageUrl());
		product1.setName(product.getName());
		product1.setPrice(product.getPrice());
		em.persist(product1);
		return product1;
	}
	
	@Override
	@Transactional
	public void deleteAllProduct() {
		Query query = em.createNamedQuery(Product.deleteAllProduct);
		query.executeUpdate();
	}
}
