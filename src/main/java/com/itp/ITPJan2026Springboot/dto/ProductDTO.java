package com.itp.ITPJan2026Springboot.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class ProductDTO {

	    @Size(min = 6, max = 100, message = "Product Title must be between 6 and 100 characters") 
	    private String title;
	    
	    @DecimalMin(value = "10.00", message = "Product Price must be greater than or equal to 10.00")
	    private double price;
	    
	    @NotBlank(message = "Product Description is required")
	    private String description;
	    
	    @NotBlank(message = "Product Category is required")
	    private String category;
	    
	    @NotBlank(message = "Product Image is required")
	    @Pattern(
	        regexp = "(?i).*\\.(png|jpg|pdf)$",
	        message = "Image must end with .png, .jpg, or .pdf"
	    )
	    private String image;
	   
	    @NotNull(message = "Product Rating is mandatory")
	    @Valid
	    private RatingDTO rating;

	    
}
