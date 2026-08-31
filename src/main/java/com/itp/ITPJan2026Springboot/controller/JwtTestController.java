package com.itp.ITPJan2026Springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class JwtTestController {
	
	
	@GetMapping("/deleteTesting")
	public String deleteTesting()
	{
		return "Only for Admin ";
	}
	
	
	@GetMapping("/readTesting")
	public String reatTesting()
	{
		return "for Admin and User ";
	}

}
