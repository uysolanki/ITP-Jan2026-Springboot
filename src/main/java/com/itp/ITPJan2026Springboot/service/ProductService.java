package com.itp.ITPJan2026Springboot.service;

import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.entity.Product;
import com.itp.ITPJan2026Springboot.exception.ProductNotFoundException;
import com.itp.ITPJan2026Springboot.repository.ProductRepository;

import jakarta.validation.Valid;

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

	public void deleteProductsById(int id) throws ProductNotFoundException
	{
		if(!productRepository.existsById(id))
		{
			throw new ProductNotFoundException("Product with ID "+id+ " does NOT exist.");
		}
		
		productRepository.deleteById(id);
	}

	public @Nullable ProductDTO updateProduct(int id, ProductDTO productDTO) throws Exception {
		// TODO Auto-generated method stub
		Product productInDB = productRepository.findById(id)
		        .orElseThrow(() -> new Exception("Product not found"));

		productInDB.setTitle(productDTO.getTitle());
		productInDB.setCategory(productDTO.getCategory());
		productInDB.setDescription(productDTO.getDescription());
		productInDB.setImage(productDTO.getImage());
		productInDB.setPrice(productDTO.getPrice());
		//productInDB.setRating(productDTO.getRating());

		Product savedProduct = productRepository.save(productInDB);

		return modelMapper.map(savedProduct, ProductDTO.class);
		
	}

//	public ProductDTO saveProductWithValidationAndExceptionHandling(@Valid ProductDTO productDto) throws MethodArgumentNotValidException
//	{
//		Product product=modelMapper.map(productDto, Product.class);
//		Product productSavedInDB= productRepository.save(product);
//		return modelMapper.map(productSavedInDB,ProductDTO.class);
//	}

}
