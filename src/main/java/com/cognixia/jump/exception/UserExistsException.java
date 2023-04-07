package com.cognixia.jump.exception;

public class UserExistsException extends Exception{

	private static final long serialVersionUID = 1L;
	
    public UserExistsException(String resource, String username) {
		super(resource + " with " + username + " already exists");
	}

}
