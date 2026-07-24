package com.itp.ITPJan2026Springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itp.ITPJan2026Springboot.entity.Student;
import com.itp.ITPJan2026Springboot.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@PostMapping("/saveStudent") //Http Method
	public String saveStudent()
	{
		Student s1=new Student();
		s1.setPer(78.5);
		s1.setSname("Alice");
		
		studentService.saveStudent(s1);
		return "Record Inserted";
	}
	
	@PostMapping("/saveStudent1") //Http Method
	public Student saveStudent1()
	{
		Student s1=new Student();
		s1.setPer(78.5);
		s1.setSname("Alice");
		
		return studentService.saveStudent(s1);
	}
	
	@PostMapping("/saveStudentByRequestParam") 
	public Student saveStudentByRequestParam(@RequestParam("a") double percentage,@RequestParam("b") String studentName)
	{
		Student s1=new Student();
		s1.setPer(percentage);
		s1.setSname(studentName);
		
		return studentService.saveStudent(s1);
	}
	
	@PostMapping("/saveStudentByRequestParam1") 
	public Student saveStudentByRequestParam1(@RequestParam double percentage,@RequestParam String studentName)
	{
		Student s1=new Student();
		s1.setPer(percentage);
		s1.setSname(studentName);
		
		return studentService.saveStudent(s1);
	}
	
	@PostMapping("/saveStudentByPathVariable/{a}/{b}") 
	public Student saveStudentByPathVariable(@PathVariable("a") double percentage,@PathVariable("b") String studentName)
	{
		Student s1=new Student();
		s1.setPer(percentage);
		s1.setSname(studentName);
		
		return studentService.saveStudent(s1);
	}
	
	@PostMapping("/saveStudentByPathVariable1/{percentage}/{studentName}") 
	public Student saveStudentByPathVariable1(@PathVariable double percentage,@PathVariable String studentName)
	{
		Student s1=new Student();
		s1.setPer(percentage);
		s1.setSname(studentName);
		
		return studentService.saveStudent(s1);
	}
	
	@PostMapping("/saveStudentByRequestBody") 
	public Student saveStudentByRequestBody(@RequestBody Student s1)
	{
		return studentService.saveStudent(s1);
	}
	
	@PostMapping("/saveMultipleStudentByRequestBody") 
	public List<Student> saveMultipleStudentByRequestBody(@RequestBody List<Student> students)
	{
		return studentService.saveMultipleStudent(students);
	}
	
	
	@GetMapping("/getAllStudents") 
	public List<Student> getAllStudents()
	{
		return studentService.getAllStudents();
	}
	
	@GetMapping("/getStudent/{studid}") 
	public Student getStudent(@PathVariable int studid)
	{
		return studentService.getStudent(studid);
	}
}

/*
{
    "rno": 6,
    "sname": "Alice",
    "per": 58.5
}
*/
