package com.alexsandro.ressources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alexsandro.services.exceptions.DatabaseException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request ){
	HttpStatus status = HttpStatus.BAD_REQUEST;
	StandardError err = new StandardError();
		return null;	
	}
	
}
