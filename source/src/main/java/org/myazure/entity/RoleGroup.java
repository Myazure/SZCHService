package org.myazure.entity;

import java.io.Serializable;

public class RoleGroup implements Serializable{
	private int group_id;
	private String group_name;
	private String role_type;
	
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoleGroup [group_id=" + group_id + ", group_name=" + group_name + "]";
	}
	

	
}
