package com.xingxue.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class User implements Serializable {

	private int id;

	private String username;

	@JsonIgnore
	private String password;

	private List<Role> roles;
	
	private Integer orgId;

	private Integer[] orgIds;

	@JsonIgnore
	public Set<Module> getAvailableModules() {
		Set<Module> available = new HashSet<>();
		for (Role role : this.roles) {
			available.addAll(role.getModules());
		}
		return available;
	}

	/**
	 * 检查是否包含改父模块
	 * 
	 * @param parentModule
	 *            父模块对象
	 * @return
	 */
	public boolean containsParentModule(Module parentModule) {
		for (Module m : getAvailableModules()) {
			if (m.getPid() == parentModule.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查是否包含该模块,根据id检查
	 * 
	 * @param module
	 *            模块对象
	 * @return
	 */
	public boolean containsModule(Module module) {
		for (Module m : getAvailableModules()) {
			if (m.getId() == module.getId()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检查是否包含该模块,根据code检查
	 * <br>
	 * 以开头匹配即可
	 * @param code
	 * @return
	 */
	public boolean containsModule(String code) {
		for (Module m : getAvailableModules()) {
			if (code.startsWith(m.getCode())) {
				return true;
			}
		}
		return false;
	}

}
