package com.bs.spring.common.excepion;

public class AuthenticationException extends RuntimeException{

	public AuthenticationException(String msg) {
		super(msg);
	}
	
}