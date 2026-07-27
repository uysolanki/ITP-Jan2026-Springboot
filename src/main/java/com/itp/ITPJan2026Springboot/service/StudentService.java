package com.itp.ITPJan2026Springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	public List<Student> saveMultipleStudent(List<Student> students) {
		return studentRepository.saveAll(students);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student getStudent(int studid) {
		return studentRepository.findById(studid).get();
	}

	public void deleteStudent(int studid) throws RuntimeException
	{
		
		if(studentRepository.existsById(studid))
		{
		studentRepository.deleteById(studid);	
		}
		else
		{
			throw new RuntimeException("Record of Student having RollNumber " + studid + " does not exist");
		}
	}
	
}
