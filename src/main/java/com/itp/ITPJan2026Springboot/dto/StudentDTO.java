package com.itp.ITPJan2026Springboot.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
public class StudentDTO {

	private String sname;
	private double per;
	private String course;
	private String city;
	private String email;
	private LocalDate dob;
}
