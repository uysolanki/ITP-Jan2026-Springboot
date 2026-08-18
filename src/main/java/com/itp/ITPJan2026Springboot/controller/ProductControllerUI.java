package com.itp.ITPJan2026Springboot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/productui")
public class ProductControllerUI {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping("/showProducts")
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
	public String addSingleProduct(@Valid @ModelAttribute ProductDTO productDto)
	{
		productService.saveProduct(productDto);
		return "redirect:/productui/showProducts";
	}
	
	@RequestMapping("/deleteProductUI/{id}")
	public String deleteProductUI(@PathVariable int id){
		
		productService.deleteProductsById(id);
		return "redirect:/productui/showProducts";
	}
	
	@RequestMapping("/updateProductForm/{id}")
	public String updateProductForm(@PathVariable int id,Model model){
		
		ProductDTO product=productService.getProductsById(id);
		model.addAttribute("product",product);
		return "update-product-form";
	}
	
	@PostMapping("/updateProductUI/{prodId}")
	public String updateProduct(@PathVariable int prodId, @ModelAttribute ProductDTO productDTO){
		try {
			productService.updateProduct(prodId, productDTO);
			return "redirect:/productui/showProducts";
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() 
			+ ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", 
			    "you do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}
