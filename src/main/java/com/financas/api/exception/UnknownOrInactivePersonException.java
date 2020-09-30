package com.financas.api.exception;

public class UnknownOrInactivePersonException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnknownOrInactivePersonException(String msg) {
		super(msg);
	}
}
