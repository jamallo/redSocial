package com.jamallo.controller;

//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

// Controlador REST para manejar las solicitudes relacionadas 
// con la página principal o endpoints de prueba.
@RestController
public class HomeController {

	/**
     * Endpoint para manejar las solicitudes GET a la raíz del controlador.
     * 
     * @return Un mensaje indicando que este es el controlador principal.
     */
	@GetMapping
	public String homeControllerHandler() {
		return "Este es el controlador principal";
	}
	
	/**
     * Endpoint para manejar las solicitudes GET a "/home".
     * 
     * @return Un mensaje indicando que este es el controlador principal 2.
     */
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "Este es el segundo controlador principal";
	}
	
	/**
     * Endpoint para manejar las solicitudes GET a "/codigojamallo".
     * 
     * @return Un saludo para codigojamallo.
     */
	@GetMapping("/codigojamallo")
	public String homeControllerHandler3() {
		return "hola código de Jamallo";
	}
	
	
//	@PutMapping
//	@PostMapping
//	@DeleteMapping
}
