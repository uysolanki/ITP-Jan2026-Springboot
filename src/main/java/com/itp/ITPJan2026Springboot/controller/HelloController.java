package com.itp.ITPJan2026Springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itp.ITPJan2026Springboot.entity.Student;

@RestController
public class HelloController {

	@RequestMapping("/test")
	public String test()
	{
		return "Welcome to Springboot";
	}
	
	@RequestMapping("/test1")
	public Student test1()
	{
		Student s1=new Student();
		s1.setPer(78.5);
		s1.setRno(1);
		s1.setSname("Alice");
		
		return s1;
	}
}
