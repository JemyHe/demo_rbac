package com.xingxue.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xingxue.entity.Module;
import com.xingxue.entity.User;
import com.xingxue.exception.LoginException;
import com.xingxue.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	// 登录页面
	@RequestMapping(path = "/user/login", method = RequestMethod.GET)
	public void page() {

	}

	// 登录操作
	@RequestMapping(path = "/user/login", method = RequestMethod.POST)
	public String login(String username, String password, Model model, HttpSession session) {
		User user = userService.findUser(username, password);
		if (user == null) {
			log.info("用户名或密码不正确");
			throw new LoginException("用户名或密码不正确");
		}
		List<Module> modules = userService.findModules();
		log.info("将modules对象存入session作用域: {}", modules);
		session.setAttribute("modules", modules);
		log.info("将user对象存入session作用域: {}", user);
		session.setAttribute("user", user);
		log.info("用户 [{}]已登录", user.getUsername());
		return "redirect:/index";
	}

	// 首页面
	@RequestMapping(path = {"/", "/index"}, method = RequestMethod.GET)
	public String index() {
		return "index";
	}

	// 注销操作
	@RequestMapping(path = "/user/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			log.info("用户 [{}]已注销", user.getUsername());
		}
		session.invalidate();
		return "redirect:/user/login";
	}

}
