package com.itp.ITPJan2026Springboot.service;

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

}
