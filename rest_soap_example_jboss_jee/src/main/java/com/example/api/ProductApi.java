package com.example.api;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.example.dao.ProductRepository;
import com.example.entity.Product;
import com.example.entity.ProductListFactory;

@Path("/product")
@RequestScoped
public class ProductApi {

	@Inject
	private transient Logger log;

	@Inject
	ProductRepository repository;

	private ProductListFactory givenProduct() {
		return new ProductListFactory(repository);
	}

	@GET
	@Path("/generate")
	public void createProducts() {
		log.info("createProducts");
		givenProduct().buildNumberOfProductsAndSave(6);
	}

	@GET
	@Path("/generateProducts/{size}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateProducts(@PathParam("size") Integer size) {
		log.info(Thread.currentThread().getStackTrace()[2].getMethodName()+" "+size);
		return Response.status(Status.OK).entity(givenProduct().buildNumberOfProductsAndSave(size)).build();
	}

	@GET
	@Path("/helloworld")
	public Response getHelloWorld() {
		String value = "Hello World";
		return Response.status(Status.OK).entity(value).build();
	}

	@GET
	@Path("/createProduct/{productName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(@PathParam("productName") String productName) {
		Product product = new Product();
		product.setName(productName);
		product.setImageUrl("image");
		product.setDescription("opis");
		product = repository.createProduct(product);
		return Response.status(Status.OK).entity(product).build();
	}

	@POST
	@Path("/createProduct")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProduct(Product product) {
		product = repository.createProduct(product);
		return Response.status(Status.OK).entity(product).build();
	}

	@GET
	@Path("/allProduct")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProduct() {
		return Response.status(Status.OK).entity(repository.getAllProducts()).build();
	}

	@GET
	@Path("/{productName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductById(@PathParam("productName") String name) {
		return Response.status(Status.OK).entity(repository.getProductByName(name)).build();
	}

	@GET
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAllProduct() {
		repository.deleteAllProduct();
		return Response.status(Status.OK).entity("usunieto").build();
	}
}
