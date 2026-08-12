package com.itp.ITPJan2026Springboot.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException ex) 
	{
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<APIError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) 
	{
		List<APIError> errors = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) 
		{
		APIError apiError = new APIError(error.getField(), error.getRejectedValue(),error.getDefaultMessage());
		errors.add(apiError);
		}
		return new ResponseEntity<List<APIError>>(errors, HttpStatus.BAD_REQUEST);
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<List<APIError>> handleMethodArgumentNotValidException1(MethodArgumentNotValidException ex) 
//	{
//		List<APIError> errors = new ArrayList<>();
//
//	    ex.getBindingResult().getFieldErrors().forEach(error -> {
//	        APIError apiError = new APIError(
//	        		 error.getField(),
//	        		 error.getRejectedValue(),
//	                error.getDefaultMessage()     
//	        );
//	        errors.add(apiError);
//	    });
//
//	    return new ResponseEntity<List<APIError>>(errors, HttpStatus.BAD_REQUEST);
//
//	}
}
