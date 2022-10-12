package com.digicert.library;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.digicert.library.exception.AuthorAlreadyExistsException;
import com.digicert.library.exception.AuthorInUseException;
import com.digicert.library.exception.AuthorNotFoundException;
import com.digicert.library.exception.BookAlreadyExistsException;
import com.digicert.library.exception.BookNotFoundException;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
	public static final String DEFAULT_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
		
		if (e instanceof AuthorAlreadyExistsException) {
			return new ResponseEntity<>("Author already exists", HttpStatus.BAD_REQUEST);
		}
		
		if (e instanceof AuthorInUseException) {
			return new ResponseEntity<>("Author in use", HttpStatus.BAD_REQUEST);
		}
		
		if (e instanceof AuthorNotFoundException) {
			return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
		}
		
		if (e instanceof BookAlreadyExistsException) {
			return new ResponseEntity<>("Book already exists", HttpStatus.BAD_REQUEST);
		}
		
		if (e instanceof BookNotFoundException) {
			return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
		}

		throw e;
	}

}