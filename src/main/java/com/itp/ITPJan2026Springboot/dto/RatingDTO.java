package com.itp.ITPJan2026Springboot.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
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
public class RatingDTO {
	
	@DecimalMin(value = "0.0", message = "Rate must be at least 0")
    @DecimalMax(value = "5.0", message = "Rate must not exceed 5")
	private double rate;
	
	@Min(value = 1, message = "Count must be at least 1")
	private int count;
}
