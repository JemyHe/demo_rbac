package com.xingxue.mapper;

import java.util.List;

import com.xingxue.entity.Module;

public interface ModuleMapper {
	
	/**
	 * 查询所有模块，以嵌套格式表示
	 * @return
	 */
	public List<Module> findAll();
	
	/**
	 * 根据父编号，查询下属模块
	 * @param pid
	 * @return
	 */
	public List<Module> findByPid(int pid);

	/**
	 * 查询角色拥有的模块
	 * @param roleId
	 * @return
	 */
	public List<Integer> findByRoleId(int roleId);

}
