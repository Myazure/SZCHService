package com.milestone.mygeo.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LayoutArea implements Serializable{
	private int city_id;
	private int area_id;
	private int father_layout;
	private String area_name;
	private String area_code;
	private String area_color;
	private List<AreaParam> areaParams = new ArrayList<AreaParam>();
	
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
	 * @return the area_id
	 */
	public int getArea_id() {
		return area_id;
	}
	/**
	 * @param area_id the area_id to set
	 */
	public void setArea_id(int area_id) {
		this.area_id = area_id;
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
	 * @return the area_name
	 */
	public String getArea_name() {
		return area_name;
	}
	/**
	 * @param area_name the area_name to set
	 */
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	/**
	 * @return the area_code
	 */
	public String getArea_code() {
		return area_code;
	}
	/**
	 * @param area_code the area_code to set
	 */
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	/**
	 * @return the area_color
	 */
	public String getArea_color() {
		return area_color;
	}
	/**
	 * @param area_color the area_color to set
	 */
	public void setArea_color(String area_color) {
		this.area_color = area_color;
	}
	
	/**
	 * @return the areaParams
	 */
	public List<AreaParam> getAreaParams() {
		return areaParams;
	}
	/**
	 * @param areaParams the areaParams to set
	 */
	public void setAreaParams(List<AreaParam> areaParams) {
		this.areaParams = areaParams;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LayoutArea [city_id=" + city_id + ", area_id=" + area_id + ", father_layout=" + father_layout
				+ ", area_name=" + area_name + ", area_code=" + area_code + ", area_color=" + area_color + "]";
	}
	
	

	
	
	

}
