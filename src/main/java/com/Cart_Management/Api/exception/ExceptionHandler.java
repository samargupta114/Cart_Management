package com.Cart_Management.Api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> anyexceptionhandle(Exception ex, WebRequest webrequest) {
		return new ResponseEntity<Object>(ex.getLocalizedMessage(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = { EmptycartException.class })
	public ResponseEntity<Object> noitemincartexception(EmptycartException ex, WebRequest webrequest) {
		return new ResponseEntity<Object>(ex.message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = { NoItemIncartExceptioin.class })
	public ResponseEntity<Object> noitemincartexception(NoItemIncartExceptioin ex, WebRequest webrequest) {
		return new ResponseEntity<Object>(ex.message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
