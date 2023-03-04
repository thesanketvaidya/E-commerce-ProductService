package com.app.productService.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.productService.dao.IProductDao;
import com.app.productService.entity.Category;
import com.app.productService.entity.Product;

@Service
@Transactional
public class ProductServiceImplementation implements IProductService {

	@Autowired
	private IProductDao productDao;
	@Override
	public List<Product> findAll() {
	
		return productDao.findAll();
	}

	@Override
	public Optional<Product> findById(int id) {
		
		return productDao.findById(id);
	}

	@Override
	public Product add(Product p) {
		return productDao.save(p);
	}

	@Override
	public String delete(int id) {
		productDao.deleteById(id);
		return "Product with id "+id+" is Deleted";
	}

	@Override
	public int update(Product p) {
		Optional<Product> product = productDao.findById(p.getId());
		//System.out.print("This is ++++++++++++"+p.getId());
		if(product.isPresent()) {
			Product prod = product.get();
			prod.setCategory(p.getCategory());
			prod.setDescription(p.getDescription());
			prod.setPrice(p.getPrice());
			prod.setTitle(p.getTitle());
			prod.setVendor(p.getVendor());
			if(p.getImage()!=null)
				prod.setImage(p.getImage());
			
			return 1;
		}
		else {
			return 0;
		}
		 
	}

	@Override
	public List<Product> findByCategory(Category category) {
		
		return productDao.findByCategory(category);
	}
	
	
	
//	public int updateImage(Product p) {
//		Optional<Product> product = productDao.findById(p.getId());
//		//System.out.print("This is ++++++++++++"+p.getId());
//		if(product.isPresent()) {
//			Product prod = product.get();
//			prod.setImage(p.getImage());
//			return 1;
//		}
//		else {
//			return 0;
//		}
//		 
//	}

}
