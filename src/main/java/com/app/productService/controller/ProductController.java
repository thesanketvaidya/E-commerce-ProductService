package com.app.productService.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.RestController;
import com.app.productService.dto.ApiResponse;
import com.app.productService.dto.RequestProductDto;
import com.app.productService.entity.Category;
import com.app.productService.entity.Product;
import com.app.productService.service.IProductService;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
//@CrossOrigin(value = {"*"}, exposedHeaders = {"Content-Disposition"})
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@GetMapping("/products")
	public List<Product> findAll(@RequestParam String category){
		if(category.equals("All Products"))
			return productService.findAll();
		return productService.findByCategory(Category.valueOf(category));
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		    if(productService.findById(id).isPresent()) {
		    	return ResponseEntity.ok(productService.findById(id));
		
		    }else {
		    	return new ResponseEntity<>(new ApiResponse("Invalid Id"), HttpStatus.NOT_FOUND);
		    }
	}
	
	@PostMapping("/products")
	//here as we have multipart file we can't use @RequestBody instead use @ModelAttribute
	public ResponseEntity<?> add(@Valid RequestProductDto productDto) throws IOException {
			
			Product p = new Product(productDto.getTitle(), Category.valueOf(productDto.getCategory().toUpperCase()), productDto.getVendor(), productDto.getDescription(), productDto.getPrice(),productDto.getImage().getBytes());
			return ResponseEntity.ok(productService.add(p));
		
	}
	
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		
		 if(productService.findById(id).isPresent()) {
			return ResponseEntity.ok(productService.delete(id));
		}else {
			return new ResponseEntity<>(new ApiResponse("Invalid Id"), HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@PutMapping(value="/products")
	public ResponseEntity<?> update(@Valid RequestProductDto productDto,BindingResult br) throws IOException {
		 	//System.out.println(productDto);
		  //System.out.println(br.getFieldErrors());
			Product p = new Product(productDto.getId(),productDto.getTitle(), Category.valueOf(productDto.getCategory().toUpperCase()), productDto.getVendor(), productDto.getDescription(), productDto.getPrice(),null);
			if(productDto.getImage()!=null)
				p.setImage(productDto.getImage().getBytes());
			
			return ResponseEntity.ok(productService.update(p));
		
	}
	

}
