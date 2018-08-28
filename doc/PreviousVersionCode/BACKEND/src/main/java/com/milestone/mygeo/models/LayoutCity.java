package com.milestone.mygeo.models;

import java.io.Serializable;

public class LayoutCity implements Serializable{
	
	private int id;
	private int city_id;
	private int layout_category_id;
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
	 * @return the layout_category_id
	 */
	public int getLayout_category_id() {
		return layout_category_id;
	}
	/**
	 * @param layout_category_id the layout_category_id to set
	 */
	public void setLayout_category_id(int layout_category_id) {
		this.layout_category_id = layout_category_id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LayoutCity [id=" + id + ", city_id=" + city_id + ", layout_category_id=" + layout_category_id + "]";
	}


	
	
	
	

	

	

	
	


	
	
	
}
