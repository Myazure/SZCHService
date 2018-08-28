package org.myazure.entity;

import java.io.Serializable;

public class Layout implements Serializable{
	
	private int layout_id;
	private int father_category;
	private String layout_name;
	private int city_id;
	private String layout_code;
	
	
	/**
	 * @return the layout_id
	 */
	public int getLayout_id() {
		return layout_id;
	}
	/**
	 * @param layout_id the layout_id to set
	 */
	public void setLayout_id(int layout_id) {
		this.layout_id = layout_id;
	}
	/**
	 * @return the father_category
	 */
	public int getFather_category() {
		return father_category;
	}
	/**
	 * @param father_category the father_category to set
	 */
	public void setFather_category(int father_category) {
		this.father_category = father_category;
	}
	/**
	 * @return the layout_name
	 */
	public String getLayout_name() {
		return layout_name;
	}
	/**
	 * @param layout_name the layout_name to set
	 */
	public void setLayout_name(String layout_name) {
		this.layout_name = layout_name;
	}
	
	
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
	 * @return the layout_code
	 */
	public String getLayout_code() {
		return layout_code;
	}
	/**
	 * @param layout_code the layout_code to set
	 */
	public void setLayout_code(String layout_code) {
		this.layout_code = layout_code;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Layout [layout_id=" + layout_id + ", father_category=" + father_category + ", layout_name="
				+ layout_name + ", city_id=" + city_id + ", layout_code=" + layout_code + "]";
	}

	
	
	

	

	

	
	


	
	
	
}
