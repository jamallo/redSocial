package com.jamallo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Usuarios")
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String nombre;
	
	private String apellidos;
	
	private String email;
	
	private String password;
	
	private String genero;
	
	private List<Integer> followers = new ArrayList<>();
	
	private List<Integer> followings = new ArrayList<>();
	
	@JsonIgnore
	@ManyToMany
	private List<Post> savedPost = new ArrayList<>();
	
	
	
	
}
