package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.example.entity.Product;
import com.example.webservice.ProductSOAPService;

public class TestSoap {

	@Test
	public void simpletest() {
		JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
		factoryBean.setServiceClass(ProductSOAPService.class);
		factoryBean.setAddress("http://localhost:8080/rest-helloworld/ProductWebService?wsdl");
		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
		factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
		ProductSOAPService service = (ProductSOAPService) factoryBean.create();
		List<Product> products = service.getAllProducts();
		System.out.println(products);
		assertThat(products).isNotEmpty();
	}

}
