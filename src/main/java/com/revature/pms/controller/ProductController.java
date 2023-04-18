package com.revature.pms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.pms.model.Product;
import com.revature.pms.service.ProductService;
import com.revature.pms.service.ProductServiceImpl;

//CRUD - operation completed successfully
@RestController
@RequestMapping("product")
/*
 * @CrossOrigin(origins = "http://localhost:9090")
 */
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	//@Qualifier("productnewimpl")
	@Qualifier("productoldimpl")
	ProductService productService;

	// localhost:9090/product/
	
	/*
	 * // get all product old implementation
	 * 
	 * @GetMapping() List<Product> getProducts() {
	 * 
	 * return productService.getProducts(); }
	 */
	
	
	@GetMapping("/price") 
	  public List<Product> getProductByPriceRange(@RequestParam int lower, int upper) {
	  
		List<Product> products = new ArrayList<Product>();
		
		products = productService.getProductByPriceRange(lower, upper);
		
		return products;
	}
	
	
	  // get  product by name  new implementation
	  
	  @GetMapping() 
	  public ResponseEntity<List<Product>> getProducts(@RequestParam (required =	  false)String productName) {
	  
	  List<Product> products = new ArrayList<Product>();
	  products=productService.getProducts();
	  int sizeprod=productService.getProducts().size();
	  if (sizeprod==0 ) {
		
		  return new ResponseEntity<List<Product>>(products,HttpStatus.NO_CONTENT);
		  
	  }
	  
	  else {
	  
	  if(productName == null) { 
		  //the client wants all the products 
		  return new ResponseEntity<List<Product>>(products,HttpStatus.OK); } 
	  else 
	  { //the client wants filtered products
	  products = productService.getProductByName(productName); }
	  
	  return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	  }
	  }
	 

	// localhost:9090/product/iPhone
	@GetMapping("/searchProductName/{productName}")
	public List<Product> getProductByName(@PathVariable("productName") String productName) {
		return productService.getProductByName(productName);
	}

	// get a single record
	// localhost:9090/product/798
	@GetMapping("{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId) {

		ResponseEntity<Product> responseEntity = new ResponseEntity<Product>(HttpStatus.OK);
		Product product = new Product();
		// two scenario
		if (productService.isProductExists(productId)) {
			product = productService.getProduct(productId);
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK);
			// product exists
		} else {
			// product does not exist
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT);
		}

		return responseEntity;
	}
	
	
	/*
	 * // Add product old implementation
	 * 
	 * @PostMapping() public String saveProduct(@RequestBody Product product) {
	 * productService.addProduct(product); return "Product saved successfully";
	 * 
	 * }
	 */

	
	
	  // Add  product new implementation
	  
	  @PostMapping() public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		  ResponseEntity<String> responseEntity = new 	  ResponseEntity<String>(HttpStatus.OK);
		  // two scenario 
		//if  product 	  exists 
	  if(productService.isProductExists(product.getProductId())) {
		  responseEntity = new ResponseEntity<String>("Product with product id :" +  product.getProductId() + " already exists", HttpStatus.CONFLICT); 
	  
	  } 
	  else 
	  {
		  productService.addProduct(product); 
	  responseEntity = new  ResponseEntity<String>("Product saved successfully :" + product.getProductId(), HttpStatus.CREATED); } 
	  
	  return responseEntity; }
	 

	/*
	 * // update product old implementation
	 * 
	 * @PutMapping() public String updateProduct(@RequestBody Product product) {
	 * productService.updateProduct(product); return "Product updated successfully";
	 * 
	 * }
	 */
	
	
	  // update product new implementation
	  
	  @PutMapping() public ResponseEntity<String> updateProduct(@RequestBody Product product) {
		  ResponseEntity<String> responseEntity = new 	  ResponseEntity<String>(HttpStatus.OK);
		  // two scenario 
		  // product 	  exists
	  if	  (productService.isProductExists(product.getProductId())) {
	  productService.updateProduct(product);
	  
	  responseEntity = new ResponseEntity<String>( "Product with product id :" +  product.getProductId() + " updated successfully", HttpStatus.OK); 
	  
	  }
	  else 
	  { responseEntity = new ResponseEntity<String>("Product not updated successfully :" +  product.getProductId(), HttpStatus.NOT_MODIFIED); 
	  
	  }
	  
	  return responseEntity; }
	 

	// delete product new implementation
	// http://localhost:9090/product/2009
	@DeleteMapping("{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId") int productId) {

		ResponseEntity<String> responseEntity = new ResponseEntity<String>(HttpStatus.OK);
		// two scenario
		if (productService.isProductExists(productId)) {
			productService.deleteProduct(productId);
			responseEntity = new ResponseEntity<String>("Product deleted successfully", HttpStatus.OK);
			// product exists
		} else {
			responseEntity = new ResponseEntity<String>("Product not deleted successfully", HttpStatus.BAD_REQUEST);
		}

		return responseEntity;
	}

}
