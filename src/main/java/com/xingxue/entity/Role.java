package com.xingxue.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Role implements Serializable {

	private int id;
	
	private String name;
	
	private List<Module> modules;
}
