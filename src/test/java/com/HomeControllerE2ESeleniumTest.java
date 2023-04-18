package com;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerE2ESeleniumTest {

	private WebDriver driver;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setup() {
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
}
	
	@Test
	public void testMessage() {
		
		
		driver.get(String.format("http://127.0.0.1:%s/message",port));
		String result = driver.findElement(By.tagName("body")).getText();
		
		Assert.assertEquals("-- Revature Training App --",result); 
	}
	
}
