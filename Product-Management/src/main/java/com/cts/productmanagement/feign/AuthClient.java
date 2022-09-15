package com.cts.productmanagement.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.productmanagement.model.JwtResponse;

@FeignClient(name="authorization-service",url="${Authorization.URL}")
	public interface AuthClient {

		@GetMapping("/api/auth/validate")
		public JwtResponse verifyToken(@RequestHeader(name="authorization",required = true)String token) ;
					

	
}
