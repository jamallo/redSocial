package com.jamallo.controller;

//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

	@GetMapping
	public String homeControllerHandler() {
		return "this is home controller";
	}
	
	@GetMapping("/home")
	public String homeControllerHandler2() {
		return "this is home controller 2";
	}
	
	@GetMapping("/codewithjamallo")
	public String homeControllerHandler3() {
		return "hola codewithjamallo";
	}
	
	
//	@PutMapping
//	@PostMapping
//	@DeleteMapping
}
