package com.xingxue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizeException extends RuntimeException {

	public AuthorizeException() {
		super("您缺乏相应的权限");
	}
 
}
