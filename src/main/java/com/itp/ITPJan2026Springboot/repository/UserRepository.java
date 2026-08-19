package com.itp.ITPJan2026Springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.ITPJan2026Springboot.entity.MyUser;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

	public MyUser findByUsername(String s);
}
