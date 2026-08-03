package com.itp.ITPJan2026Springboot.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.entity.Product;
import com.itp.ITPJan2026Springboot.repository.ProductRepository;

@Service  //Component
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ModelMapper modelMapper;

	public @Nullable ProductDTO saveProduct(ProductDTO productDto) {
		Product product=modelMapper.map(productDto, Product.class);
		Product productSavedInDB= productRepository.save(product);
		return modelMapper.map(productSavedInDB,ProductDTO.class);
	}

	public @Nullable List<ProductDTO> saveProducts(List<ProductDTO> productDtos) {
		
		List<Product> products =productDtos.stream()
		.map(dto->modelMapper.map(dto, Product.class))
		.toList();
		
		List<Product> productsSavedInDB=productRepository.saveAll(products);
		
		return productsSavedInDB.stream()
		.map(product->modelMapper.map(product, ProductDTO.class))
		.toList();
	}
	
	public @Nullable List<ProductDTO> getAllProducts() {
		// TODO Auto-generated method stub
		
		List<Product> productsInDB = productRepository.findAll();
		List<ProductDTO> products =productsInDB.stream()
				.map(product->modelMapper.map(product, ProductDTO.class))
				.toList();
	
		
		return products;
	}

	public @Nullable List<ProductDTO> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		List<Product> productsInDB = productRepository.findByCategoryContaining(category);
		List<ProductDTO> products =productsInDB.stream()
				.map(product->modelMapper.map(product, ProductDTO.class))
				.toList();
		return products;
	}

	public @Nullable ProductDTO getProductsById(int id) {
		// TODO Auto-generated method stub
		
		Optional<Product> productInDB =productRepository.findById(id);
		ProductDTO product =modelMapper.map(productInDB, ProductDTO.class);
		return product;
	}

}
