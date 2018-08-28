package org.myazure.domain;

import java.io.Serializable;

public class LineParamSet implements Serializable{
	private int id;
	private int line_paramid;
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
	 * @return the line_paramid
	 */
	public int getLine_paramid() {
		return line_paramid;
	}
	/**
	 * @param line_paramid the line_paramid to set
	 */
	public void setLine_paramid(int line_paramid) {
		this.line_paramid = line_paramid;
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
		return "LineParamSet [id=" + id + ", line_paramid=" + line_paramid + ", name=" + name + ", value=" + value
				+ "]";
	}
	
	

}
