package com.itp.ITPJan2026Springboot.exception;

import java.time.LocalDateTime;

import com.itp.ITPJan2026Springboot.entity.Rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class APIError {
	
	String field;
	Object rejectedValue;
	String defaultMessage;
}
