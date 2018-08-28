package org.myazure.domain;

import java.io.Serializable;
import java.util.Date;

public class Standard implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "Standard [id=" + id + ", name=" + name + ", code=" + code + "]";
	}

	

	
	


	
	
	
}
