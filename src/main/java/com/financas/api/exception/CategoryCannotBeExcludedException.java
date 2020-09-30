package com.financas.api.exception;

public class CategoryCannotBeExcludedException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public CategoryCannotBeExcludedException(String msg) {
		super(msg);
	}

}
