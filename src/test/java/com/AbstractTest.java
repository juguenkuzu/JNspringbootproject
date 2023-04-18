package com;

import java.io.IOException;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootRevatureProductAppUsApplication.class)

// class qui permet de convertir la reponse en json format et vice versa
public abstract class AbstractTest {

	protected MockMvc mvc;
	
	@Autowired
	WebApplicationContext WebApplicationContext;
	
	protected void setUpJson() {
		mvc= MockMvcBuilders.webAppContextSetup(WebApplicationContext).build();
		
	}
	
	protected String mapToJson(Object obj)throws JsonProcessingException {
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(obj);	
		
	}
	
	protected <T> T  mapFromJson(String json,Class<T> clazz) 
			throws JsonParseException,JsonMappingException,IOException  {
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.readValue(json, clazz);
		
		
	}
	
}
