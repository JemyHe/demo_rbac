package com.xingxue.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xingxue.entity.User;
import com.xingxue.exception.LoginAjaxException;
import com.xingxue.exception.LoginException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor extends AjaxCheckInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("开始检查用户是否登录...");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String code = request.getRequestURI().replaceAll(request.getContextPath(), "");
		boolean no = user == null;
		log.info("当前的请求路径为: {}, 用户[{}]登录", code, no ? "未" : "已");
		if (no) {
			if (isAjaxRequest(request)) {
				throw new LoginAjaxException();
			} else {
				throw new LoginException();
			}
		}
		return true;
	}

}
