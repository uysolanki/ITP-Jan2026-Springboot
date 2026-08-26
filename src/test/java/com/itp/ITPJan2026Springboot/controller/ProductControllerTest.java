package com.itp.ITPJan2026Springboot.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.service.ProductService;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockitoBean
	private ProductService productService;

	@Test
	void getProductsById_endpointShouldReturnJson() throws Exception {
		ProductDTO product = ProductDTO.builder().id(1).title("Sample Product Title").price(99.99)
				.description("Sample description").category("Electronics").image("product.jpg").build();

		when(productService.getProductsById(1)).thenReturn(product);

		mockMvc.perform(get("/product/getProductsById/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.title").value("Sample Product Title"));

	}
	
	@Test
	void getAllProducts_endpointShouldReturnJsonArray() throws Exception {
	    // Step 1: create sample list (fake DB response)
	    ProductDTO product1 = ProductDTO.builder()
	            .id(1)
	            .title("Sample Product Title")
	            .price(99.99)
	            .description("Sample description")
	            .category("Electronics")
	            .image("product.jpg")
	            .build();
	    ProductDTO product2 = ProductDTO.builder()
	            .id(2)
	            .title("Another Product Title")
	            .price(49.99)
	            .description("Another description")
	            .category("Books")
	            .image("book.jpg")
	            .build();
	    List<ProductDTO> products = Arrays.asList(product1, product2);
	    // Step 2: mock service call
	    when(productService.getAllProducts()).thenReturn(products);
	    // Step 3: call endpoint + validate response
	    mockMvc.perform(get("/product/getAllProducts"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[0].id").value(1))
	            .andExpect(jsonPath("$[0].title").value("Sample Product Title"))
	            .andExpect(jsonPath("$[1].id").value(2))
	            .andExpect(jsonPath("$[1].title").value("Another Product Title"));
	}
	
	
	@Test
	void saveProductWithValidationAndExceptionHandlingUsingGHA_shouldReturnCreated() throws Exception {
	    String requestJson = """
	            {
	              "id": 1,
	              "title": "Sample Product Title",
	              "price": 99.99,
	              "description": "Sample description",
	              "category": "Electronics",
	              "image": "product.jpg"
	            }
	            """;

	    // Step 2: expected saved response from service
	    ProductDTO savedProduct = ProductDTO.builder()
	            .id(1)
	            .title("Sample Product Title")
	            .price(99.99)
	            .description("Sample description")
	            .category("Electronics")
	            .image("product.jpg")
	            .build();

	    // Step 3: mock service
	    when(productService.saveProduct(any(ProductDTO.class))).thenReturn(savedProduct);

	    // Step 4: POST request + validate response
	    mockMvc.perform(post("/product/saveProductWithValidationAndExceptionHandlingUsingGHA")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(requestJson))
	            .andExpect(status().isCreated())
	            .andExpect(jsonPath("$.id").value(1))
	            .andExpect(jsonPath("$.title").value("Sample Product Title"));
	}

}
