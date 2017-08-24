package com.xingxue.mapper;

import java.util.List;

import com.xingxue.entity.Org;

public interface OrgMapper {
	
	/**
	 * 根据父编号，查询下属组织机构
	 * @param pid
	 * @return
	 */
	public List<Org> findByPid(int pid);
	
	/**
	 * 根据多个id进行查询
	 * @param ids
	 * @return
	 */
	public List<Org> findByIds(Integer[] ids);

}
