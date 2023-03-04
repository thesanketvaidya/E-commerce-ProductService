package com.app.productService.dto;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ApiResponse {

	private String message;

	public ApiResponse(String message) {
		super();
		this.message = message;
	}
	
	
}
