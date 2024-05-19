package com.jamallo.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Clase que maneja excepciones globales para toda la aplicación.
 * Proporciona métodos para manejar excepciones específicas y excepciones generales.
 */
@ControllerAdvice
public class ExcepcionesGlobales {
	
	/**
     * Maneja la excepción ExcepcionesUsuario.
     * @param ue -> Recibe la excepción ExcepcionesUsuario.
     * @param req -> Recibe la solicitud web.
     * @return Una respuesta con detalles de error y estado HTTP BAD_REQUEST.
     */
	@ExceptionHandler(ExcepcionesUsuario.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler (
			ExcepcionesUsuario ue,
			WebRequest req) {
		
		ErrorDetails error = new ErrorDetails(
				ue.getMessage(), 
				req.getDescription(false), 
				LocalDateTime.now()
				);
		
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}
	
	/**
     * Maneja excepciones generales.
     * @param ue -> Recibe la excepción general.
     * @param req -> Recibe la solicitud web.
     * @return Una respuesta con detalles de error y estado HTTP BAD_REQUEST.
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> otherExceptionHandler (
			Exception ue,
			WebRequest req) {
		
		ErrorDetails error = new ErrorDetails(
				ue.getMessage(), 
				req.getDescription(false), 
				LocalDateTime.now()
				);
		
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.BAD_REQUEST);
	}

}
