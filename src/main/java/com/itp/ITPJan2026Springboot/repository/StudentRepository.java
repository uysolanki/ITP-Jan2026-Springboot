package com.itp.ITPJan2026Springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.ITPJan2026Springboot.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
