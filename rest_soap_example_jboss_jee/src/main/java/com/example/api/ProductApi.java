package com.example.api;

import java.util.logging.Logger;

import javax.ejb.EJB;
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

import com.example.dao.ProductDao;
import com.example.entity.Product;
import com.example.entity.ProductListFactory;

@Path("/product")
@RequestScoped
public class ProductApi {

	@Inject
	private transient Logger log;

	@EJB
	ProductDao repository;
	
	@EJB
	private ProductListFactory givenProduct;

	@GET
	@Path("/generate")
	public void createProducts() {
		log.info("createProducts");
		givenProduct.buildNumberOfProductsAndSave(6);
	}

	@GET
	@Path("/generateProducts/{size}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response generateProducts(@PathParam("size") Integer size) {
		log.info("generate "+size+" Products ");
		return Response.status(Status.OK).entity(givenProduct.buildNumberOfProductsAndSave(size)).build();
	}
	
	@GET
	@Path("/products/{size}/{page}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductsBySizeAndPage(@PathParam("size") Integer size,@PathParam("page") Integer page) {
		log.info("getAllProduct");
		return Response.status(Status.OK).entity(repository.getProduct(size, page)).build();
	}

	@GET
	@Path("/helloworld")
	public Response getHelloWorld() {
		log.info("getHelloWorld");
		String value = "Hello World";
		return Response.status(Status.OK).entity(value).build();
	}

	@GET
	@Path("/createProduct/{productName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(@PathParam("productName") String productName) {
		log.info("createProduct GET o nazwie = "+productName);
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
		log.info("createProduct POST o nazwie = "+product.getName());
		product = repository.createProduct(product);
		return Response.status(Status.OK).entity(product).build();
	}

	@GET
	@Path("/allProduct")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProduct() {
		log.info("getAllProduct");
		return Response.status(Status.OK).entity(repository.getAllProducts()).build();
	}

	@GET
	@Path("/{productName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductByName(@PathParam("productName") String name) {
		log.info("getProductByName o nazwie = "+name);
		return Response.status(Status.OK).entity(repository.getProductByName(name)).build();
	}

	@GET
	@Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAllProduct() {
		log.info("deleteAllProduct GET");
		repository.deleteAllProduct();
		return Response.status(Status.OK).entity("usunieto").build();
	}
}
