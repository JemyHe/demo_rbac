package com.xingxue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xingxue.entity.Order;

public interface OrderMapper {

	public List<Order> findBy(@Param("customerId") int customerId, @Param("orgIds") Integer[] orgIds);

	public List<Order> findAll(@Param("orgIds") Integer[] orgIds);

}
