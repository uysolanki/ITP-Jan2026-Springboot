package com.itp.ITPJan2026Springboot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Product {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String title;
	    private double price;
	    private String description;
	    private String category;
	    private String image;
	    
	    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	    @JoinColumn(name ="ratingid", referencedColumnName = "ratingid" )
	    private Rating rating;

	    private LocalDateTime createdAt;
	    private LocalDateTime modifiedAt;
		
		@PrePersist
		protected void atCreation()
		{
			LocalDateTime now=LocalDateTime.now();
			this.createdAt=now;
			this.modifiedAt=now;
		}
		
		@PreUpdate
		protected void atUpdation()
		{
			this.modifiedAt=LocalDateTime.now();
		}
}
