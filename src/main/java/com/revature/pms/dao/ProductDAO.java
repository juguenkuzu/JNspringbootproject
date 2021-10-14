package com.revature.pms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.pms.model.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
		
	public List<Product> findByProductName(String productName);
	public List<Product> findByPriceBetween(int low, int high);

	//public List<Product> getProductByPriceRange(int lower, int upper);
	//5000/7000
	//?
	
	
}
