package com.example.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@NamedQueries({ @NamedQuery(name = "findProductById", query = "select p from Product p where p.id = :id"),
		@NamedQuery(name = "findProductByName", query = "select p from Product p where lower(p.name) like :name"),
		@NamedQuery(name = "findAllProducts", query = "select p from Product p"),
		@NamedQuery(name = "deleteAllProduct", query = "delete from Product p"),
		@NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id = :id") })
@Entity
@XmlRootElement(name="Product")
@XmlType(name="Product")
public class Product {
	public static final String findProductById = "findProductById";
	public static final String findProductByName = "findProductByName";
	public static final String findAllProducts = "findAllProducts";
	public static final String deleteProductById = "deleteProductById";
	public static final String deleteAllProduct = "deleteAllProduct";
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	private String imageUrl;

	private BigDecimal price;

	public Product() {
	}

	public Product(String name, String description, String imageUrl, BigDecimal price) {
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl + "]";
	}

}
