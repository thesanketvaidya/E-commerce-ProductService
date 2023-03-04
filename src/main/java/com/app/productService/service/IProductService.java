package com.app.productService.service;

import java.util.List;
import java.util.Optional;

import com.app.productService.entity.Category;
import com.app.productService.entity.Product;

public interface IProductService {

	List<Product> findAll();
	Optional<Product> findById(int id);
	Product add(Product p);
	String delete(int id);
	int update(Product p);
	List<Product> findByCategory(Category category);
	
}
