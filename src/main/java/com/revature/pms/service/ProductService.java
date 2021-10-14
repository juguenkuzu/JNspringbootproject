package com.revature.pms.service;

import java.util.List;

import com.revature.pms.model.Product;

public interface ProductService {
	public void addProduct(Product product);
	public void updateProduct(Product product);
	public List<Product> getProducts();
	public void deleteProduct(int productId);
	public Product getProduct(int productId);
	public boolean isProductExists(int productId);
	public List<Product> getProductByName(String productName);
	public List<Product> getProductByPriceRange(int lower,int upper);

}
