package com.jamallo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamallo.models.Mensaje;

public interface MensajeRepository extends JpaRepository<Mensaje, Integer>{
	
	public List<Mensaje> findByChatId(Integer chatId);

}
