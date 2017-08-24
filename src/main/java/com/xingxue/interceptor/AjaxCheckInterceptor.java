package com.xingxue.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AjaxCheckInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * 检查是否是ajax请求
	 * @param request
	 * @return
	 */
	protected boolean isAjaxRequest(HttpServletRequest request) {
		String value = request.getHeader("x-requested-with");
		return value != null && value.equalsIgnoreCase("XMLHttpRequest");
	}

}
