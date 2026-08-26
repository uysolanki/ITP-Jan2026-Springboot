package com.itp.ITPJan2026Springboot.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.itp.ITPJan2026Springboot.entity.Product;

@DataJpaTest
@ActiveProfiles("test")
@Transactional
class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@org.junit.jupiter.api.BeforeEach
	void setUp() {
		productRepository.deleteAll();
	}

	@Test
	void save_shouldPersistProductInTestDatabase() {
		Product product = new Product();
		product.setTitle("Sample Product Title");
		product.setPrice(99.99);
		product.setDescription("Sample description");
		product.setCategory("Electronics");
		product.setImage("product.jpg");

		Product savedProduct = productRepository.save(product);

		assertNotNull(savedProduct.getId());
		assertEquals(1, productRepository.count());
		assertEquals("Sample Product Title", productRepository.findById(savedProduct.getId()).orElseThrow().getTitle());
	}
}
