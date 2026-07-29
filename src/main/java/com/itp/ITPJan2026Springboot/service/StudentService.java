package com.itp.ITPJan2026Springboot.service;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.itp.ITPJan2026Springboot.dto.StudentDTO;
import com.itp.ITPJan2026Springboot.entity.Student;
import com.itp.ITPJan2026Springboot.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ModelMapper modelMapper;

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

	public void updateStudent(int studid, Student newStudentValues) {
		if(studentRepository.existsById(studid))
		{
		//logic for update
			Student studentFromDb=getStudent(studid);
			studentFromDb.setPer(newStudentValues.getPer());
			studentFromDb.setSname(newStudentValues.getSname());
			studentRepository.save(studentFromDb);
		}
		else
		{
			throw new RuntimeException("Record of Student having RollNumber " + studid + " does not exist");
		}
		
	}

	public Student updateStudent1(int studid, Student newStudentValues) {
		if(studentRepository.existsById(studid))
		{
			Student studentFromDb=getStudent(studid);
			studentFromDb.setPer(newStudentValues.getPer());
			studentFromDb.setSname(newStudentValues.getSname());
			
			studentFromDb.setCourse(newStudentValues.getCourse());
			studentFromDb.setCity(newStudentValues.getCity());
			
			studentFromDb.setEmail(newStudentValues.getEmail());
			studentFromDb.setDob(newStudentValues.getDob());
			
			return studentRepository.save(studentFromDb);
		}
		
		throw new RuntimeException("Record of Student having RollNumber " + studid + " does not exist");
		
		
		
	}

	public @Nullable List<Student> getCityWiseStudents(String studcity) {
		return studentRepository.findByCityContaining(studcity);
	}

	public @Nullable StudentDTO saveStudentUsingDTO(StudentDTO studDTO) {
		Student student=modelMapper.map(studDTO, Student.class);
		Student studentSavedInDB= studentRepository.save(student);
		return modelMapper.map(studentSavedInDB,StudentDTO.class);
	}
	
}
