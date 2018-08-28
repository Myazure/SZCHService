package org.myazure.entity;

import java.io.Serializable;
import java.util.Date;

public class City implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 619888597804951046L;
	private int city_id;
	private String city_name;
	private String creater;
	private Date created_at;
	
	public int getCity_id() {
		return city_id;
	}
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	
	/**
	 * @return the creater
	 */
	public String getCreater() {
		return creater;
	}
	/**
	 * @param creater the creater to set
	 */
	public void setCreater(String creater) {
		this.creater = creater;
	}
	/**
	 * @return the created_at
	 */
	public Date getCreated_at() {
		return created_at;
	}
	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "City [city_id=" + city_id + ", city_name=" + city_name + ", creater=" + creater + ", created_at="
				+ created_at + "]";
	}
	
	

	

	

	
	


	
	
	
}
