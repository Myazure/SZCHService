package com.milestone.mygeo.models;

import java.io.Serializable;

public class AreaParamSet implements Serializable{
	private int id;
	private int area_paramid;
	private String name;
	private String value;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	
	/**
	 * @return the area_paramid
	 */
	public int getArea_paramid() {
		return area_paramid;
	}
	/**
	 * @param area_paramid the area_paramid to set
	 */
	public void setArea_paramid(int area_paramid) {
		this.area_paramid = area_paramid;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AreaParamSet [id=" + id + ", area_paramid=" + area_paramid + ", name=" + name + ", value=" + value
				+ "]";
	}


	
	

}
