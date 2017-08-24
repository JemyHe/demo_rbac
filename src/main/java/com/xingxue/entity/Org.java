package com.xingxue.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class Org implements Serializable {
	
	private int id;
	
	private String name;
	
	private int pid;
	
	private List<Org> children;
	
	private boolean open = true;

}
