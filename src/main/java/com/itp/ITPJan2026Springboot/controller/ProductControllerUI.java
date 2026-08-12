package com.itp.ITPJan2026Springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.service.ProductService;

import jakarta.validation.Valid;

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
	
	@RequestMapping("/addProductForm")
	public String addProductForm(Model model){
		ProductDTO emptyProduct = new ProductDTO();
		model.addAttribute("product",emptyProduct);
		return "add-product-form";
	}
	
	@PostMapping("/addSingleProduct")
	public String addSingleProduct(@ModelAttribute ProductDTO productDto)
	{
		productService.saveProduct(productDto);
//		return "confirm";
		return "redirect:/productui/showProducts";
	}
}
