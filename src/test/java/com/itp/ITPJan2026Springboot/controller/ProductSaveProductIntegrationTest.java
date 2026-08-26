package com.itp.ITPJan2026Springboot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.itp.ITPJan2026Springboot.config.AppConfig;
import com.itp.ITPJan2026Springboot.entity.Product;
import com.itp.ITPJan2026Springboot.repository.ProductRepository;
import com.itp.ITPJan2026Springboot.service.ProductService;

@DataJpaTest
@Import({ ProductController.class, ProductService.class, AppConfig.class })
@ActiveProfiles("test")
@Transactional
class ProductSaveProductIntegrationTest {

	@Autowired
	private ProductController productController;

	@Autowired
	private ProductRepository productRepository;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		productRepository.deleteAll();
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	void saveProduct_shouldReturnCreatedAndPersistInDatabase() throws Exception {
		mockMvc.perform(post("/product/saveProduct")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validProductJson()))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andExpect(jsonPath("$.title").value("Sample Product Title"))
				.andExpect(jsonPath("$.price").value(99.99))
				.andExpect(jsonPath("$.category").value("Electronics"));

		assertEquals(1, productRepository.count());

		Product savedProduct = productRepository.findAll().get(0);
		assertNotNull(savedProduct.getId());
		assertEquals("Sample Product Title", savedProduct.getTitle());
		assertEquals(99.99, savedProduct.getPrice());
		assertEquals("Sample description", savedProduct.getDescription());
		assertEquals("Electronics", savedProduct.getCategory());
		assertEquals("product.jpg", savedProduct.getImage());
		assertNotNull(savedProduct.getCreatedAt());
		assertNotNull(savedProduct.getModifiedAt());
	}

	@Test
	void saveProduct_shouldPersistMultipleProductsInDatabase() throws Exception {
		mockMvc.perform(post("/product/saveProduct")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validProductJson()))
				.andExpect(status().isCreated());

		mockMvc.perform(post("/product/saveProduct")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "title": "Another Product Title",
						  "price": 49.99,
						  "description": "Another description",
						  "category": "Books",
						  "image": "book.jpg"
						}
						"""))
				.andExpect(status().isCreated());

		assertEquals(2, productRepository.count());
	}

	@Test
	void saveProduct_shouldGenerateUniqueIdsInDatabase() throws Exception {
		MvcResult firstResponse = mockMvc.perform(post("/product/saveProduct")
				.contentType(MediaType.APPLICATION_JSON)
				.content(validProductJson()))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andReturn();

		MvcResult secondResponse = mockMvc.perform(post("/product/saveProduct")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
						{
						  "title": "Second Product Title",
						  "price": 59.99,
						  "description": "Second description",
						  "category": "Sports",
						  "image": "sports.jpg"
						}
						"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").isNotEmpty())
				.andReturn();

		int firstId = com.jayway.jsonpath.JsonPath.read(firstResponse.getResponse().getContentAsString(), "$.id");
		int secondId = com.jayway.jsonpath.JsonPath.read(secondResponse.getResponse().getContentAsString(), "$.id");

		assertNotEquals(firstId, secondId);
		assertEquals(2, productRepository.count());
	}

	private String validProductJson() {
		return """
				{
				  "title": "Sample Product Title",
				  "price": 99.99,
				  "description": "Sample description",
				  "category": "Electronics",
				  "image": "product.jpg"
				}
				""";
	}
}
