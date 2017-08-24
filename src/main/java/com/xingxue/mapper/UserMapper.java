package com.xingxue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xingxue.entity.User;

public interface UserMapper {

	/**
	 * 根据用户名密码查询用户（连同用户的角色、能够操作的模块、组织机构）
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public User findUserWithRoleAndModuleAndOrg(@Param("username") String username, @Param("password") String password);

	/**
	 * 更新用户的角色
	 * 
	 * @param userId
	 * @param roleId
	 */
	public void insertUserRole(@Param("userId") int userId, @Param("roleId") int roleId);

	/**
	 * 清除用户所有角色
	 * 
	 * @param userId
	 * @param roleId
	 */
	public void deleteUserRole(int userId);
	
	/**
	 * 查询某机构下的用户
	 * @param orgId
	 * @return
	 */
	public List<User> findByOrgId(int orgId);
	
	/**
	 * 根据用户id查询（连同用户的角色、能够操作的模块、组织机构）
	 * @param userId
	 * @return
	 */
	public User findById(int userId);
	
	/**
	 * 更新用户可控制的组织机构
	 * @param user
	 */
	public void updateUserOrgs(User user);


}
