package com.xingxue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xingxue.entity.Module;
import com.xingxue.entity.Org;
import com.xingxue.entity.Role;
import com.xingxue.entity.User;
import com.xingxue.mapper.ModuleMapper;
import com.xingxue.mapper.OrgMapper;
import com.xingxue.mapper.RoleMapper;
import com.xingxue.mapper.UserMapper;

@Service
public class SystemService {
	
	@Autowired
	private OrgMapper orgMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired
	private ModuleMapper moduleMapper;
	
	public List<Org> findOrgs(Integer[] ids) {
		return orgMapper.findByIds(ids);
	}

	public List<User> findUsersByOrgId(int orgId) {
		return userMapper.findByOrgId(orgId);
	}
	
	public User findUserById(int userId) {
		return userMapper.findById(userId);
	}
	
	public List<Role> findRoles() {
		return roleMapper.findAll();
	}

	/**
	 * 更新用户权限
	 * @param userId
	 * @param roleIds
	 */
	@Transactional
	public void updateUserRoles(int userId, List<Integer> roleIds) {
		userMapper.deleteUserRole(userId);
		for(int roleId: roleIds) {
			userMapper.insertUserRole(userId, roleId);
		}		
	}

	/**
	 * 更新用户组织
	 * @param userId
	 * @param orgIds
	 */
	public void updateUserOrgs(int userId, Integer[] orgIds) {
		User user = new User();
		user.setId(userId);
		user.setOrgIds(orgIds);
		userMapper.updateUserOrgs(user);
	}

	public List<Module> findModules() {
		return moduleMapper.findAll();
	}

	/**
	 * 查询某个权限下所有模块
	 * @param roleId
	 * @return
	 */
	public List<Integer> findModulesByRoleId(int roleId) {
		return moduleMapper.findByRoleId(roleId);
	}

	/**
	 * 更新某个权限下所有模块
	 * @param roleId
	 * @param moduleIds
	 */
	@Transactional
	public void updateRoleModules(int roleId, Integer[] moduleIds) {
		roleMapper.deleteRoleModule(roleId);
		for(int moduleId: moduleIds) {
			roleMapper.insertRoleModule(roleId, moduleId);
		}
	}

}
