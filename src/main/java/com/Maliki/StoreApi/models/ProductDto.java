package com.Maliki.StoreApi.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductDto {
	@NotEmpty(message = "Must be not Empty")
	private String name;
	@NotEmpty(message = "Must be not Empty")
	private String brand;
	@NotEmpty(message = "Must be not Empty")
	private String category;
	//@NotEmpty(message = "Must be not Empty")
	private String price;
	@NotEmpty(message = "Must be not Empty")
	@Size(min = 10, message = "The Description should be atleast 10 characters")
	@Size(max = 2000, message = "The Description cannot exceed 2000 characters")
	@Column(columnDefinition = "TEXT")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
