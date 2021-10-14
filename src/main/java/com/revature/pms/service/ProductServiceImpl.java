package com.revature.pms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.pms.dao.ProductDAO;
import com.revature.pms.model.Product;
@Service(value = "productoldimpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	public void addProduct(Product product) {
		System.out.println("Adding the product in old way");
		productDAO.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.save(product);

	}

	@Override
	public List<Product> getProducts() {
		return productDAO.findAll();
	}

	@Override
	public void deleteProduct(int productId) {
		productDAO.deleteById(productId);

	}

	@Override
	public Product getProduct(int productId) {
		Optional<Product> prod = productDAO.findById(productId);
		return prod.get();
	}

	@Override
	public boolean isProductExists(int productId) {
		return productDAO.existsById(productId);
	}

	@Override
	public List<Product> getProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductByPriceRange(int lower, int upper) {
		// TODO Auto-generated method stub
		return null;
	}

}
