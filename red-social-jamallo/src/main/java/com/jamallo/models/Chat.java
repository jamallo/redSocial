package com.jamallo.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToMany;
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

public class Chat {
	
	@jakarta.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private String nombre_chat;
	
	private String imagen_chat;
	
	@ManyToMany
	private List<User> users = new ArrayList<>();
		
	private LocalDateTime tiempo;
	
	@OneToMany(mappedBy = "chat")
	private List<Mensaje> mensajes = new ArrayList<>();

}
