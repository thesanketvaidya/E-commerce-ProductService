package com.app.productService.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.productService.entity.Category;
import com.app.productService.entity.Product;

public interface IProductDao extends JpaRepository<Product, Integer> {

	List<Product> findByCategory(Category category);
}
