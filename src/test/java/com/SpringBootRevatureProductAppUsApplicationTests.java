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

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SpringBootRevatureProductAppUsApplicationTests {

	@LocalServerPort
	private String port;

	@Autowired
	private RestTemplate restTemplate;

	private String baseURL = "http://localhost";

	URL url,messageURL;
	
	@BeforeEach
	public void setUp() throws MalformedURLException {
		System.out.println("Before each ");
		url = new URL(baseURL + ":" + port);
		messageURL = new URL(baseURL + ":" + port+"/message");
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
