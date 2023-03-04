package com.app.productService.dto;


import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestProductDto {

	
	private Integer id;
	@NotBlank
	private String title;
	@NotBlank
	private String category;
	@NotBlank
	private String vendor;
	@NotBlank
	private String description;
	private double price;
	private MultipartFile image;
	
	private String username;
	private String token;
}
