package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.example.entity.Product;
import com.example.entity.ProductBuilder;


public class TestRest {
	
    private List<Product> BuildNumberOfProductsAndSave(int i) {
    	client.target("http://localhost:8080/rest-helloworld/api/product/delete").request().get();
    	client = ClientBuilder.newClient();
    	List<Product> response = client.target("http://localhost:8080/rest-helloworld/api/product/generateProducts/"+i).request(MediaType.APPLICATION_JSON)
				.get(productType);
		return response;		
	}
	
	
	private Client client = ClientBuilder.newClient();
	private GenericType<List<Product>> productType = new GenericType<List<Product>>() {};
	
	
    @Test
    public void should_create_a_product() throws InterruptedException {
        //given
    	Product product=new ProductBuilder("nowyProduct").build();
    	
        //when
        thenCreateProductByApi(product);

        //then
        assertThat(thenGetOneProductFromApi(product.getName())).isNotNull();
        
    }


	/*@Test
    public void should_update_existing_product() {
        //given
    	List<Product> givenProducts=BuildNumberOfProductsAndSave(1);

        //when
        thenUpdateProductByApi();

        //then
        assertThat(realProductRepository.findOneByName("product_0"))
                .isNotNull()
                .hasFieldOrPropertyWithValue("price", BigDecimal.ONE.setScale(2));
    }

    @Test
    public void should_delete_existing_product() {
        //given
        BuildNumberOfProductsAndSave(1);
        //when
        thenDeleteOneProductFromApi();

        //then
        assertThat(realProductRepository.findOneByName("product_0")).isNull();
    }*/
	
	@Test
    public void should_get_empty_list_of_products() {
        BuildNumberOfProductsAndSave(0);

        List<Product> products = thenGetProductsFromApi();

        assertThat(products).isEmpty();
    }

	@Test
    public void should_get_3_products() {
        BuildNumberOfProductsAndSave(3);

        List<Product> products = thenGetProductsFromApi();

        assertThat(products).hasSize(3);
    }

    @Test
    public void should_get_one_product() throws InterruptedException {
        List<Product> givenProducts=BuildNumberOfProductsAndSave(1);   
        
        Product product = thenGetOneProductFromApi(givenProducts.get(0).getName());

        assertThat(givenProducts.get(0)).isEqualToComparingFieldByField(product);
    }
    
	
	@Test
	public void testGetAllProducts(){
		System.out.println("test Resta - pobieranie wszystkich Produktow");
		List<Product> response = client.target("http://localhost:8080/rest-helloworld/api/product/allProduct").request(MediaType.APPLICATION_JSON)
				.get(productType);
		System.out.println(response.size());
		assertTrue( true );
	}
	
	private List<Product> thenGetProductsFromApi() {
        return client.target("http://localhost:8080/rest-helloworld/api/product/allProduct").request(MediaType.APPLICATION_JSON)
				.get(productType);
    }

    private Product thenGetOneProductFromApi(String name) {
        return client.target("http://localhost:8080/rest-helloworld/api/product/"+name).request(MediaType.APPLICATION_JSON)
				.get(Product.class);
    }
    
    private void thenCreateProductByApi(Product product) {
		client.target("http://localhost:8080/rest-helloworld/api/product/createProduct").request(MediaType.APPLICATION_JSON)
		.post(Entity.entity(product, MediaType.APPLICATION_JSON),
				Product.class);
	}

}
