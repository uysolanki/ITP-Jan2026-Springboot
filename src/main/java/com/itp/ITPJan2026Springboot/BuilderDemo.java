package com.itp.ITPJan2026Springboot;

import com.itp.ITPJan2026Springboot.dto.ProductDTO;
import com.itp.ITPJan2026Springboot.dto.RatingDTO;

public class BuilderDemo {

	public static void main(String[] args) {
		ProductDTO p1=new ProductDTO("laptop",200.0,"Dell Inspiron","Electronics","abc.jpg",new RatingDTO());

		ProductDTO p2=new ProductDTO();
		p2.setTitle("Lenevo");
		p2.setDescription("Laptop for preffesionals");
		
		ProductDTO p3=ProductDTO.builder()
				.title("Apple McBook")
				.description("Latest Model")
				.build();
	}

}

