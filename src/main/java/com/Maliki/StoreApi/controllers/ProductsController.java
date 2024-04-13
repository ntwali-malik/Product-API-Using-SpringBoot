package com.Maliki.StoreApi.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Maliki.StoreApi.models.Product;
import com.Maliki.StoreApi.models.ProductDto;
import com.Maliki.StoreApi.repositories.ProductsRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

	@Autowired
	private ProductsRepository repo;
	
	//Listing All Products
	@GetMapping
	public List<Product> getProducts(){
		return repo.findAll();
	}
	
	//Diplay product based on ID
	@GetMapping("{id}")
	public 	ResponseEntity<Product> getProduct(@PathVariable int id){
		Product product = repo.findById(id).orElse(null);
		
		if(product == null) {
			return ResponseEntity.notFound().build();
		}
		 	
		return ResponseEntity.ok(product);
	}
	
	//CREATE
	@PostMapping
	public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto productDto,BindingResult result){
		
		double price = 0;
		try {
			price = Double.parseDouble(productDto.getPrice());
		}
		catch(Exception ex) {
			result.addError(new FieldError("ProductDto", "price", "The Price should be a Number"));
		}
		
		
		if(result.hasErrors()) {
			var errorsList = result.getAllErrors();
			var errorsMap = new HashMap<String,String>();
			for(int i=0;i<errorsList.size();i++) {
				var error = (FieldError) errorsList.get(i);
				errorsMap.put(error.getField(), error.getDefaultMessage());
			}
			return ResponseEntity.badRequest().body(errorsMap);
		}
		
		Product prod = new Product();
		prod.setName(productDto.getName());
		prod.setBrand(productDto.getBrand());
		prod.setCategory(productDto.getCategory());
		prod.setPrice(price);
		prod.setDescription(productDto.getDescription());
		prod.setCreatedAt(new Date());
		
		repo.save(prod);
		
		return ResponseEntity.ok(prod);
	}
	
	//UPDATEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
	@PutMapping("{id}")
	public 	ResponseEntity<Product> updateProduct(@PathVariable int id ,@Valid @RequestBody ProductDto productDto,BindingResult result){
		
		Product product = repo.findById(id).orElse(null);
		
		if(product == null) {
			return ResponseEntity.notFound().build();
		}
		
		double price = 0;
		try {
			price = Double.parseDouble(productDto.getPrice());
		}
		catch(Exception ex) {
			result.addError(new FieldError("ProductDto", "price", "The Price should be a Number"));
		}
		if(result.hasErrors()) {
			var errorsList = result.getAllErrors();
			var errorsMap = new HashMap<String,String>();
			for(int i=0;i<errorsList.size();i++) {
				var error = (FieldError) errorsList.get(i);
				errorsMap.put(error.getField(), error.getDefaultMessage());
			}
			//return ResponseEntity.badRequest().body(errorsMap);
		}
		
		
		Product prod = new Product();
		prod.setName(productDto.getName());
		prod.setBrand(productDto.getBrand());
		prod.setCategory(productDto.getCategory());
		prod.setPrice(price);
		prod.setDescription(productDto.getDescription());
		repo.save(prod);
		
		return ResponseEntity.ok(prod);
	}
	
	//DELETEEEEEEEEEEEEEEE
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable int id){
		Product prod = repo.findById(id).orElse(null);
		if(prod == null) {
			return ResponseEntity.notFound().build();
		}
		repo.delete(prod);
		return ResponseEntity.status(HttpStatus.OK).body("Product with id " + id + " deleted successfully");
	}
}
