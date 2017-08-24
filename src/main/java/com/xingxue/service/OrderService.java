package com.xingxue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingxue.entity.Order;
import com.xingxue.mapper.OrderMapper;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;

	public List<Order> findBy(int customerId, Integer[] orgIds) {
		return orderMapper.findBy(customerId, orgIds);
	}

	public List<Order> findAll(Integer[] orgIds) {
		return orderMapper.findAll(orgIds);
	}

}
