package com.xingxue.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class LoginAjaxException extends RuntimeException {

	public LoginAjaxException() {
		super("您需要登录");
	}
	
	public LoginAjaxException(String msg) {
		super(msg);
	}

}
