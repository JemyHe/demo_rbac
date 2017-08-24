package com.xingxue.entity;

import lombok.Data;

@Data
public class Order {

	private int id;
	
	private int customerId;
	
	private int orgId;
	
	private Double total;

}
