package com.itp.ITPJan2026Springboot.dto;

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

		
	    private String title;
	    private double price;
	    private String description;
	    private String category;
	    private String image;
	   
	    private RatingDTO rating;

	    
}
