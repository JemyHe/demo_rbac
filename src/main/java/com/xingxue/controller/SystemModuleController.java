package com.xingxue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xingxue.service.SystemService;

@Controller
public class SystemModuleController {
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping("/system/module")
	public void module() {
	}
	
	@RequestMapping("/system/module/findModulesByRoleId")
	@ResponseBody
	public List<Integer> findModulesByRoleId(int roleId){
		return systemService.findModulesByRoleId(roleId);
	}
	
	@RequestMapping("/system/module/updateModules")
	@ResponseBody
	public Map<String, Object> updateOrgs(int roleId, @RequestParam Integer[] moduleIds) {
		// 修改用户数据权限
		systemService.updateRoleModules(roleId, moduleIds);
		Map<String, Object> map = new HashMap<>();
		map.put("success", true);
		return map;
	}

}
