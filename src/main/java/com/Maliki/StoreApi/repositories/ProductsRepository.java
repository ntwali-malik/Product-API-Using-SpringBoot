package com.Maliki.StoreApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Maliki.StoreApi.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer>{

}
