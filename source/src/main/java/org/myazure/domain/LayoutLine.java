package org.myazure.domain;

import java.io.Serializable;
import java.util.List;

public class LayoutLine implements Serializable{
	private int city_id;
	private int line_id;
	private int father_layout;
	private String line_name;
	private String line_code;
	private String line_color;
	
	private List<LineParam> lineParams;
	
	
	/**
	 * @return the city_id
	 */
	public int getCity_id() {
		return city_id;
	}
	/**
	 * @param city_id the city_id to set
	 */
	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}
	/**
	 * @return the line_id
	 */
	public int getLine_id() {
		return line_id;
	}
	/**
	 * @param line_id the line_id to set
	 */
	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}
	/**
	 * @return the father_layout
	 */
	public int getFather_layout() {
		return father_layout;
	}
	/**
	 * @param father_layout the father_layout to set
	 */
	public void setFather_layout(int father_layout) {
		this.father_layout = father_layout;
	}
	/**
	 * @return the line_name
	 */
	public String getLine_name() {
		return line_name;
	}
	/**
	 * @param line_name the line_name to set
	 */
	public void setLine_name(String line_name) {
		this.line_name = line_name;
	}
	/**
	 * @return the line_code
	 */
	public String getLine_code() {
		return line_code;
	}
	/**
	 * @param line_code the line_code to set
	 */
	public void setLine_code(String line_code) {
		this.line_code = line_code;
	}
	/**
	 * @return the line_color
	 */
	public String getLine_color() {
		return line_color;
	}
	/**
	 * @param line_color the line_color to set
	 */
	public void setLine_color(String line_color) {
		this.line_color = line_color;
	}
	

	/**
	 * @return the lineParams
	 */
	public List<LineParam> getLineParams() {
		return lineParams;
	}
	/**
	 * @param lineParams the lineParams to set
	 */
	public void setLineParams(List<LineParam> lineParams) {
		this.lineParams = lineParams;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LayoutLine [city_id=" + city_id + ", line_id=" + line_id + ", father_layout=" + father_layout
				+ ", line_name=" + line_name + ", line_code=" + line_code + ", line_color=" + line_color + "]";
	}

	
	
	

}
