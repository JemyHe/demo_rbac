package com.xingxue.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingxue.exception.AuthorizeAjaxException;
import com.xingxue.exception.AuthorizeException;
import com.xingxue.exception.LoginAjaxException;
import com.xingxue.exception.LoginException;

@ControllerAdvice
public class ExceptionAdvice {

	@ExceptionHandler({ LoginException.class, AuthorizeException.class })
	public String handler1(Exception e, Model model) {
		model.addAttribute("error", e.getMessage());
		return "/user/login";
	}

	@ExceptionHandler({ LoginAjaxException.class, AuthorizeAjaxException.class })
	@ResponseBody
	public Map<String, Object> handler2(Exception e) {
		Map<String, Object> map = new HashMap<>();
		map.put("success", false);
		map.put("message", e.getMessage());
		return map;
	}

}
