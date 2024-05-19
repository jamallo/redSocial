package com.jamallo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jamallo.models.User;

// Declara que esta interfaz extiende JpaRepository, 
// lo que le proporciona métodos CRUD básicos para gestionar entidades User. 
// El tipo de la entidad es User y la clave primaria es de tipo Integer.
public interface UserRepository extends JpaRepository<User, Integer>{
	
	/**
     * Encuentra un usuario por su email.
     *
     * @param email -> Recibe el email del usuario.
     * @return El usuario encontrado.
     */
	public User findByEmail(String email);
	
	/**
     * Busca usuarios cuyos nombres, apellidos o emails coincidan parcialmente con la consulta proporcionada.
     *
     * @param query -> Recibe la consulta de búsqueda parcial.
     * @return Una lista de usuarios que coinciden con la consulta.
     */
	@Query("select u from User u where u.nombre LIKE%:query% OR u.apellidos LIKE%:query% OR u.email LIKE%:query%")
	public List<User> searchUser(@Param("query") String query);

}
