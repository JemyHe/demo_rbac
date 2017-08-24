package com.xingxue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginException extends RuntimeException {

	public LoginException() {
		super("您需要登录");
	}
	
	public LoginException(String msg) {
		super(msg);
	}

}
