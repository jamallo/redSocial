package com.jamallo.models;

import java.time.LocalDateTime;
import java.util.List;


import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String titulo;
	
	private String imagen;
	
	private String video;
	
	@ManyToOne
	private User user;
	
	@OneToMany
	private List<User> liked = new ArrayList<>();
	
	private LocalDateTime createdAt;
	
	//book
}
