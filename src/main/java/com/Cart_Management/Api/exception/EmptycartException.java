package com.Cart_Management.Api.exception;

public class EmptycartException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;	

	public EmptycartException(String message) {
		super();
		this.message = message;
	}

	public EmptycartException() {
		super();
	}
}
