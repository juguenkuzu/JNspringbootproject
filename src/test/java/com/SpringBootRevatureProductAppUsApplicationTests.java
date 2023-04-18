package com;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.revature.pms.dao.*;
import com.revature.pms.model.Product;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringBootRevatureProductAppUsApplicationTests {

	@LocalServerPort
	private String port;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ProductDAO ProductDAO;
	

	private String baseURL = "http://localhost";

	URL url,messageURL;
	
	@BeforeEach
	public void setUp() throws MalformedURLException {
		System.out.println("Before each ");
		url = new URL(baseURL + ":" + port);
		messageURL = new URL(baseURL + ":" + port+"/message");
	}
	
	@Test
	@DisplayName("Testing Product DAO Save")
	public void saveproduct()  {
		
		Product product=new Product(6,"cheese",3,10);
		List<Product> products=(List<Product>)ProductDAO.findAll();
		int originalsize=products.size();
		ProductDAO.save(product);
		assertEquals(originalsize+1, 11);
		
		
		
	}
	@Test
	@DisplayName("Testing home")
	public void contextLoads() throws MalformedURLException {

		ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
		assertEquals("Welcome To Revature", response.getBody());

	}

	@Test
	@DisplayName("Testing message")
	public void testMessageAPI() throws MalformedURLException {
		String expected = "-- Revature Training App --";
		ResponseEntity<String> response = restTemplate.getForEntity(messageURL.toString(), String.class);

		assertEquals(expected, response.getBody());
	}

}
