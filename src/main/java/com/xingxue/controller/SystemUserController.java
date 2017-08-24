package com.xingxue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingxue.entity.Org;
import com.xingxue.entity.Role;
import com.xingxue.entity.User;
import com.xingxue.service.SystemService;

@Controller
public class SystemUserController {

	@Autowired
	private SystemService systemService;

	@RequestMapping("/system/user")
	public void user(Model model, HttpSession session) {
		// 准备功能权限字典数据(复选框)
		List<Role> roles = systemService.findRoles();
		model.addAttribute("roles", roles);
	}

	@RequestMapping("/system/user/findOrgs")
	@ResponseBody
	public List<Org> findOrgs(HttpSession session) {
		User user = (User) session.getAttribute("user");
		Integer[] orgIds = user.getOrgIds();
		// 准备左侧组织机构树
		List<Org> orgs = systemService.findOrgs(orgIds); 
		return orgs;
	}

	@RequestMapping("/system/user/findUsersByOrgId")
	@ResponseBody
	public List<User> findUsersByOrgId(int orgId) {
		// 查询组织机构下属用户
		List<User> users = systemService.findUsersByOrgId(orgId);
		return users;
	}

	@RequestMapping("/system/user/findUserById")
	@ResponseBody
	public User findUserById(int userId) {
		// 查询用户详细信息
		User user = systemService.findUserById(userId);
		return user;
	}

	@RequestMapping("/system/user/updateRoles")
	@ResponseBody
	public Map<String, Object> updateRoles(int userId, @RequestParam List<Integer> roleIds) {
		// 修改用户功能权限(角色)
		systemService.updateUserRoles(userId, roleIds);
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		return map;
	}
	
	@RequestMapping("/system/user/updateOrgs")
	@ResponseBody
	public Map<String, Object> updateOrgs(int userId, @RequestParam Integer[] orgIds) {
		// 修改用户数据权限
		systemService.updateUserOrgs(userId, orgIds);
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		return map;
	}

}
