package com.itp.ITPJan2026Springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/saveProduct")
	ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDto)
	{
		return  new ResponseEntity<ProductDTO>(productService.saveProduct(productDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/saveProducts")
	ResponseEntity<List<ProductDTO>> saveProducts(@RequestBody List<ProductDTO> productDtos)
	{
		return  new ResponseEntity<List<ProductDTO>>(productService.saveProducts(productDtos),HttpStatus.CREATED);
	}
	

	@GetMapping("/getAllProducts")
	ResponseEntity<List<ProductDTO>> getAllProducts(){
		return  new ResponseEntity<List<ProductDTO>>(productService.getAllProducts(),HttpStatus.OK);
	}
	
	@GetMapping("/getProductsByCategory/{category}")
	ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable String category){
		return  new ResponseEntity<List<ProductDTO>>(productService.getProductsByCategory(category),HttpStatus.OK);
	}
	
	
	@GetMapping("/getProductsById/{id}")
	ResponseEntity<ProductDTO> getProductsById(@PathVariable int id){
		return  new ResponseEntity<ProductDTO>(productService.getProductsById(id),HttpStatus.OK);
	}
}
