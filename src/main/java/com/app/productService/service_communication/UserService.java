package com.app.productService.service_communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.productService.dto.RequestValidateToken;

@FeignClient("customer-management")
public interface UserService {

	@RequestMapping(
			 method= RequestMethod.POST,
			 value="/validate-token")
	ResponseEntity<?> validateToken(@RequestBody RequestValidateToken tokenRequest) throws Exception;
	
}
