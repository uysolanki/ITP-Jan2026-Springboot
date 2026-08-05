package com.itp.ITPJan2026Springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.exception.APIError;
import com.itp.ITPJan2026Springboot.exception.ProductNotFoundException;
import com.itp.ITPJan2026Springboot.service.ProductService;

import jakarta.validation.Valid;

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
	
	@PostMapping("/saveProductWithValidation")
	ResponseEntity<ProductDTO> saveProductWithValidation(@Valid @RequestBody ProductDTO productDto)
	{
		return  new ResponseEntity<ProductDTO>(productService.saveProduct(productDto),HttpStatus.CREATED);
	}
	

	@PostMapping("/saveProductWithValidationAndExceptionHandling")
	ResponseEntity<?> saveProductWithValidationAndExceptionHandling(@Valid @RequestBody ProductDTO productDto, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors()) 
	 	{
		List<APIError> errors = new ArrayList<>();
		for (FieldError error : bindingResult.getFieldErrors()) 
			{
			APIError apiError = new APIError(error.getField(), error.getRejectedValue(),error.getDefaultMessage());
			errors.add(apiError);
			 }
				 
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	return new ResponseEntity<>(productService.saveProduct(productDto),HttpStatus.CREATED);
	}
	
	
	@PostMapping("/saveProductWithValidationAndExceptionHandlingUsingGHA")
	ResponseEntity<ProductDTO> saveProductWithValidationAndExceptionHandlingUsingGHA(@Valid @RequestBody ProductDTO productDto)
	{
	return new ResponseEntity<ProductDTO>(productService.saveProduct(productDto),HttpStatus.CREATED);
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
	
	@DeleteMapping("/deleteProductsById/{id}")
	ResponseEntity<String> deleteProductsById(@PathVariable int id){
		try
		{
		productService.deleteProductsById(id);
		}
		catch(ProductNotFoundException ex)
		{
		return  new ResponseEntity<String>(ex.getMessage(),HttpStatus.OK);
		}
		return  new ResponseEntity<String>("Product Deleted with ID " +id,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProductsByIdUsingGHA/{id}")
	ResponseEntity<String> deleteProductsByIdUsingGHA(@PathVariable int id){
		
		productService.deleteProductsById(id);
		return  new ResponseEntity<String>("Product Deleted with ID " +id,HttpStatus.OK);
	}
}
