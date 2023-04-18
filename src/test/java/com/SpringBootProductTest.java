package com;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import com.revature.pms.dao.*;
import com.revature.pms.model.Product;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(value=MethodOrderer.OrderAnnotation.class)
class SpringBootProductTest extends AbstractTest {

	@LocalServerPort
	private String port;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProductDAO ProductDAO;
	
int productId=0;
	private String baseURL = "http://localhost";

	URL url,restURL;
	
	@BeforeEach
	public void setUp() throws MalformedURLException {
		System.out.println("Before each ");
		super.setUpJson();
		productId=100;
		url = new URL(baseURL + ":" + port);
		restURL = new URL(baseURL + ":" + port+"/product");
	}
	
	/*
	 * @Test
	 * 
	 * @DisplayName("Testing Product DAO Save") public void saveproduct() {
	 * 
	 * // count the number of row on the product table after inserting Product
	 * product=new Product(7,"malta",5,13); List<Product>
	 * products=(List<Product>)ProductDAO.findAll(); int
	 * originalsize=products.size(); ProductDAO.save(product);
	 * assertEquals(originalsize+1, 7);
	 * 
	 * }
	 */
	
	@Test
	@Order(3)
	@DisplayName("Testing get product by productId")
	public void TestgetProductbyid() throws Exception {
		
		// To DO
		
	}
	

	@Test
	@Order(2)
	@DisplayName("Testing Update product")
	public void TestUpdateProducts() throws Exception {
		
		Product product = new Product();
		product.setProductId(productId);
		product.setProductName("Banana");
		product.setPrice(8);
		product.setQuantityOnHand(12);
		
		String inputJson=super.mapToJson(product);
		

		MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders.put(restURL.toString())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	int status=	mvcResult.getResponse().getStatus();
	assertEquals(200,status);
	String successmessage="Product with product id :" +  product.getProductId() + " updated successfully";
	String content=mvcResult.getResponse().getContentAsString();
	
	assertEquals(content,successmessage);

	}

	@Test
	@Order(1)
	@DisplayName("Testing Save product")
	public void TestSaveProduct() throws Exception {
		
		Product product = new Product();
		//product.setProductId(7);
		product.setProductId(productId);
		product.setProductName("Juce");
		product.setPrice(9);
		product.setQuantityOnHand(23);
		
		String inputJson=super.mapToJson(product);
		

		MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders.post(restURL.toString())
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	int status=	mvcResult.getResponse().getStatus();
	assertEquals(201,status);
	String successmessage="Product saved successfully :" + product.getProductId();
	String content=mvcResult.getResponse().getContentAsString();
	
	assertEquals(content,successmessage);

	}

	
	
	@Test
	@DisplayName("Testing Delete product")
	@Order(4)
	public void TestDeleteProducts() throws Exception {
		
		String deleteURL=restURL.toString()+"/"+productId;	 
			
		MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders.delete(deleteURL)).andReturn();
				
	int status=	mvcResult.getResponse().getStatus();
	assertEquals(200,status);
	String successmessage="Product deleted successfully";
	String content=mvcResult.getResponse().getContentAsString();
	
	assertEquals(content,successmessage);

	}

	@Test
	@Order(5)
	@DisplayName("Testing Get all product")
	public void TestGetProducts() throws Exception {

		MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders.get(restURL.toString()).accept(MediaType.APPLICATION_JSON)).andReturn();
	int status=	mvcResult.getResponse().getStatus();
	assertEquals(200,status);
	
	String content=mvcResult.getResponse().getContentAsString();
	Product[] productList =super.mapFromJson(content, Product[].class);
	assertEquals(productList.length,11);

	}
	
}
