package com.Cart_Management.Api.exception;

public class NoItemIncartExceptioin extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	public NoItemIncartExceptioin(String message) {
		super();
		this.message = message;
	}
	public NoItemIncartExceptioin() {
		super();
	}
	
}
