package com.app.productService.entity;



import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //Annotation to create the table in database
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name="title",nullable = false,length = 50)
	private String title;
	
	@Enumerated(EnumType.STRING)
	@Column(name="category",nullable = false,length = 50)
	private Category category;
	
	@Column(name="vendor",nullable = false,length = 50)
	private String vendor;
	
	@Column(name="description",nullable = false,length = 100)
	private String description;
	
	@Column(name="price",nullable = false)
	private double price;

	@Column(name="image",nullable = false)
	@Lob
	private byte[] image;

	public Product(int id,String title, Category category, String vendor, String description, double price,byte[] image) {
		super();
		this.id=id;
		this.title = title;
		this.category = category;
		this.vendor = vendor;
		this.description = description;
		this.price = price;
		this.image=image;
		
	}
	public Product(String title, Category category, String vendor, String description, double price,byte[] image) {
		super();
		
		this.title = title;
		this.category = category;
		this.vendor = vendor;
		this.description = description;
		this.price = price;
		this.image=image;
	}
	
	
	
	
	
}
