package com.itp.ITPJan2026Springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itp.ITPJan2026Springboot.entity.Student;
import com.itp.ITPJan2026Springboot.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public Student saveStudent(Student s1) {
		return studentRepository.save(s1);
		
	}
	
}
