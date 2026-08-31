//package com.itp.ITPJan2026Springboot.controller;
//
//import java.security.Principal;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.itp.ITPJan2026Springboot.dto.ProductDTO;
//import com.itp.ITPJan2026Springboot.service.ProductService;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@RestController
//@RequestMapping("/productui")
//public class ProductControllerReactUI {
//
//	@Autowired
//	ProductService productService;
//	
//	@Autowired
//	AuthenticationManager authenticationManager;
//	
////	@RequestMapping("/showProducts")
////	public String showProducts(Model model) {
////		List<ProductDTO> products = productService.getAllProducts();
////		model.addAttribute("products",products);
////		return "show-products";
////	}
//	
//	@GetMapping("/showProducts")
//	public List<ProductDTO> showProducts(Model model) {
//		List<ProductDTO> products = productService.getAllProducts();
//		return products;
//	}
//	
//
//	@PostMapping("/login")
//	public String login(
//	        @RequestParam String username,
//	        @RequestParam String password,
//	        HttpServletRequest request) {
//
//	    UsernamePasswordAuthenticationToken token =
//	            new UsernamePasswordAuthenticationToken(username, password);
//
//	    Authentication authentication =
//	            authenticationManager.authenticate(token);
//
//	    SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	    request.getSession().setAttribute(
//	            "SPRING_SECURITY_CONTEXT",
//	            SecurityContextHolder.getContext()
//	    );
//
//	    return "Login successful";
//	}
//	
//	@GetMapping
//	public List<ProductDTO> getAllProducts() {
//        return productService.getAllProducts();
//    }
//	
//	
//	@RequestMapping("/addProductForm")
//	public String addProductForm(Model model) {
//		ProductDTO product = new ProductDTO();
//		model.addAttribute("product",product);
//		return "add-product-form";
//	}
//	
////	@PostMapping("/addSingleProduct")
////	public void addSingleProduct(@ModelAttribute ProductDTO productDTO) {
////		//System.out.println("On Controller");
////		productService.saveProduct(productDTO);
////	}
//	
//	@PostMapping("/addSingleProduct")
//	public void addSingleProduct(@RequestBody ProductDTO productDTO) {
//
//	    System.out.println("Product received: " + productDTO);
//
//	    productService.saveProduct(productDTO);
//	}
//	
//	@PostMapping("/rateProduct/{productId}")
//	public String rateProduct(@PathVariable int productId,
//	                          @RequestParam int rating) {
//
//	    productService.rateProduct(productId, rating);
//	    return "redirect:/productui/showProducts";
//	}
//	
////	@RequestMapping("/updateProductForm/{id}")
////	public String updateProductForm(@PathVariable int id,Model model){
////		
////		ProductDTO product=productService.getProductsById(id);
////		model.addAttribute("product",product);
////		return "update-product-form";
////	}
//	
//	@GetMapping("/showProduct/{id}")
//	public ProductDTO getProductById(@PathVariable int id) {
//
//	    return productService.getProductsById(id);
//	}
//	
////	@PostMapping("/updateProductUI/{prodId}")
////	public String updateProduct(@PathVariable int prodId, @ModelAttribute ProductDTO productDTO){
////		try {
////			productService.updateProduct(prodId, productDTO);
////			return "redirect:/productui/showProducts";
////		} catch (Exception e) {
////			return e.getMessage();
////		}
////		
////	}
//	
//	@PutMapping("/updateProductUI/{prodId}")
//	public String updateProduct(
//	        @PathVariable int prodId,
//	        @RequestBody ProductDTO productDTO) {
//
//	    try {
//	        productService.updateProduct(prodId, productDTO);
//	        return "Update successful";
//	    } catch (Exception e) {
//	        return e.getMessage();
//	    }
//	}
//	
////	@RequestMapping("/deleteProductUI/{id}")
////	public String deleteProductUI(@PathVariable int id) throws Exception{
////		
////		productService.deleteProduct(id);
////		return "redirect:/productui/showProducts";
////	}
//	
//	@DeleteMapping("/deleteProductUI/{id}")
//	public String deleteProductUI(@PathVariable int id) throws Exception{
//	
//	productService.deleteProductsById(id);
//	return "";
//}
//	
//	@RequestMapping(value = "/403")
//	public ModelAndView accesssDenied(Principal user) {
//
//		ModelAndView model = new ModelAndView();
//
//		if (user != null) {
//			model.addObject("msg", "Hi " + user.getName() 
//			+ ", you do not have permission to access this page!");
//		} else {
//			model.addObject("msg", 
//			    "you do not have permission to access this page!");
//		}
//
//		model.setViewName("403");
//		return model;
//
//	}
//}
