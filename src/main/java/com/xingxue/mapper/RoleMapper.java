package com.xingxue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xingxue.entity.Role;

public interface RoleMapper {

	/**
	 * 建立角色-模块关系
	 * @param roleId
	 * @param moduleId
	 */
	public void insertRoleModule(@Param("roleId") int roleId, @Param("moduleId") int moduleId);
	
	/**
	 * 删除角色所有的模块
	 */
	public void deleteRoleModule(int roleId);
	
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> findAll();

}
