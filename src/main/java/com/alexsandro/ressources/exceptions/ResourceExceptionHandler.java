package com.alexsandro.ressources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.alexsandro.services.exceptions.DatabaseException;
import com.alexsandro.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError();
		err.setError("Database Exception");
		err.setMensagem(e.getMessage());
		err.setPath(request.getRequestURI());
		err.setStatus(status.value());
		err.setTimestamp(Instant.now());

		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setError("Resource not found");
		err.setMensagem(e.getMessage());
		err.setPath(request.getRequestURI());
		err.setStatus(status.value());
		err.setTimestamp(Instant.now());

		return ResponseEntity.status(status).body(err);
	}
	

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,
			HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError();
		err.setError("Resource not found");
		err.setMensagem(e.getMessage());
		err.setPath(request.getRequestURI());
		err.setStatus(status.value());
		err.setTimestamp(Instant.now());

		return ResponseEntity.status(status).body(err);
	}

}
