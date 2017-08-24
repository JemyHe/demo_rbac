package com.xingxue.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xingxue.entity.User;
import com.xingxue.exception.AuthorizeAjaxException;
import com.xingxue.exception.AuthorizeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModuleInterceptor extends AjaxCheckInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("开始检查用户是否有权限访问...");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String code = request.getRequestURI().replaceAll(request.getContextPath(), "");
		boolean no = !user.containsModule(code);
		log.info("当前的请求路径为: {}, 用户[{}]权限访问该路径", code, no ? "无" : "有");
		if (no) {
			if (isAjaxRequest(request)) {
				throw new AuthorizeAjaxException();
			} else {
				throw new AuthorizeException();
			}
		}
		return true;
	}

}
