package com.xingxue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingxue.entity.Module;
import com.xingxue.entity.User;
import com.xingxue.mapper.ModuleMapper;
import com.xingxue.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ModuleMapper moduleMapper;

	public User findUser(String username, String password) {
		return userMapper.findUserWithRoleAndModuleAndOrg(username, password);
	}
	
	public List<Module> findModules() {
		return moduleMapper.findAll();
	}

}
