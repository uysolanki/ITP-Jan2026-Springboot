package com.itp.ITPJan2026Springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDto)
	{
		return  new ResponseEntity<ProductDTO>(productService.saveProduct(productDto),HttpStatus.CREATED);
	}

}
