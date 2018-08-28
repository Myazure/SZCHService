package org.myazure.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String password;
	private Date created_at;
	private String imei;
	private int group_id;
	private String group_name;
	private int is_admin;
	private String role_type;
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	
	
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	

	/**
	 * @return the group_id
	 */
	public int getGroup_id() {
		return group_id;
	}
	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}
	/**
	 * @return the group_name
	 */
	public String getGroup_name() {
		return group_name;
	}
	/**
	 * @param group_name the group_name to set
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	/**
	 * @return the is_admin
	 */
	public int getIs_admin() {
		return is_admin;
	}
	/**
	 * @param is_admin the is_admin to set
	 */
	public void setIs_admin(int is_admin) {
		this.is_admin = is_admin;
	}

	


	/**
	 * @return the role_type
	 */
	public String getRole_type() {
		return role_type;
	}
	/**
	 * @param role_type the role_type to set
	 */
	public void setRole_type(String role_type) {
		this.role_type = role_type;
	}




	private Date updated_at;
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + "]";
	}
	
	


	
	
	
}
