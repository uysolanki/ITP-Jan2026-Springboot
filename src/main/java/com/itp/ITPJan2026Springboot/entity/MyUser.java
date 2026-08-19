package com.itp.ITPJan2026Springboot.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class MyUser {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long userid;

	   private String username;
	  
	   private String password;
	   
	   @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	   @JoinTable(
	           name = "users_roles",
	           joinColumns = @JoinColumn(name = "fkuserid"),
	           inverseJoinColumns = @JoinColumn(name = "fkroleid")
	           )
	   private List<Role> roles;

}
