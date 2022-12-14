package com.example.calucalteage.exception;

public class NotFoundUserException extends RuntimeException {

	public NotFoundUserException() {
		super("User with entered ID do not exist!");
	}

}
