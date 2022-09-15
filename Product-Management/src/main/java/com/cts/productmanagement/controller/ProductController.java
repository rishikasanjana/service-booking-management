package com.cts.productmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.productmanagement.exception.InvalidTokenException;
import com.cts.productmanagement.feign.AuthClient;
import com.cts.productmanagement.model.AppProduct;
import com.cts.productmanagement.service.ProductService;


@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private AuthClient authClient;
		
	  @GetMapping("/awsProduct")
		public String welcome() {
	    	return "Product Deployed to Cloud";
		}
    @PostMapping("/product")
	public AppProduct addProduct(@RequestBody AppProduct appProduct,@RequestHeader(name = "Authorization", required = true) String token)throws InvalidTokenException { 
    	if (!authClient.verifyToken(token).isValid()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}

		return productService.addProduct(appProduct);
	}
    
    @PutMapping("/product")
	public AppProduct updateProduct(@RequestBody AppProduct appProduct,@RequestHeader(name = "authorization", required = true) String token)throws InvalidTokenException {
    	if (!authClient.verifyToken(token).isValid()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
    	return  productService.updateProduct(appProduct);
	}
       
    @GetMapping("/product/{id}")
	public AppProduct getProductById(@PathVariable("id") int id,@RequestHeader(name = "authorization", required = true) String token)throws InvalidTokenException {		
    	if (!authClient.verifyToken(token).isValid()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
    	return productService.getProductById(id);
	}
    
  
    
    @DeleteMapping("/product/{id}")
	public void deleteProduct(@PathVariable int id,@RequestHeader(name = "authorization", required = true) String token)throws InvalidTokenException {
    	if (!authClient.verifyToken(token).isValid()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
    	productService.deleteProduct(id);
	}
	
	@GetMapping("/product")
	public List<AppProduct> getProducts(@RequestHeader(name = "authorization", required = true) String token)throws InvalidTokenException{
		if (!authClient.verifyToken(token).isValid()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		return productService.getProduct();		
	}	
	
}
