package com.itp.ITPJan2026Springboot.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
	    private Integer id;
		
		@Column(nullable = false, length = 100)  //title varchar(100) not null
	    private String title;
	    
		@Column(nullable = false)
		private double price;
		
		@Column(nullable = false, length = 1000)
	    private String description;
		
		@Column(nullable = false, length = 50)
	    private String category;
		
		@Column(nullable = false)
	    private String image;
	    
	    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	    @JoinColumn(name ="ratingid", referencedColumnName = "ratingid",nullable = true )
	    private Rating rating;

	    @Column(nullable = false, updatable = false)  //include only insert and not in update query
	    private LocalDateTime createdAt;
	    
	    @Column(nullable = false)
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
