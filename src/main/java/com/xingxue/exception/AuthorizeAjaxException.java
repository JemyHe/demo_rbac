package com.xingxue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthorizeAjaxException extends RuntimeException {

	public AuthorizeAjaxException() {
		super("您缺乏相应的权限");
	}
 
}
