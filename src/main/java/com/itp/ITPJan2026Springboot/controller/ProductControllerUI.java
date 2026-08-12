package com.itp.ITPJan2026Springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.service.ProductService;

@Controller
@RequestMapping("/productui")
public class ProductControllerUI {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/showProducts")
	public String showProducts(Model model){
		List<ProductDTO> products = productService.getAllProducts();
		model.addAttribute("products",products);
		return "show-products";
	}
}
