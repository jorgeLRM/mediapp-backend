package com.mitocode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3075440614033374569L;

	public ModelNotFoundException(String message) {
		super(message);
	}
}
