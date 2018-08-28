package org.myazure.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class LayoutPoint implements Serializable{
	private int city_id;
	private int point_id;
	private int father_layout;
	private String point_name;
	private String point_code;
	private String point_pic;
	
	private List<PointParam> PointParams;
	
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
	 * @return the point_id
	 */
	public int getPoint_id() {
		return point_id;
	}
	/**
	 * @param point_id the point_id to set
	 */
	public void setPoint_id(int point_id) {
		this.point_id = point_id;
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
	 * @return the point_name
	 */
	public String getPoint_name() {
		return point_name;
	}
	/**
	 * @param point_name the point_name to set
	 */
	public void setPoint_name(String point_name) {
		this.point_name = point_name;
	}
	/**
	 * @return the point_code
	 */
	public String getPoint_code() {
		return point_code;
	}
	/**
	 * @param point_code the point_code to set
	 */
	public void setPoint_code(String point_code) {
		this.point_code = point_code;
	}
	/**
	 * @return the point_pic
	 */
	public String getPoint_pic() {
		return point_pic;
	}
	/**
	 * @param point_pic the point_pic to set
	 */
	public void setPoint_pic(String point_pic) {
		this.point_pic = point_pic;
	}
	


	/**
	 * @return the pointParams
	 */
	public List<PointParam> getPointParams() {
		return PointParams;
	}
	/**
	 * @param pointParams the pointParams to set
	 */
	public void setPointParams(List<PointParam> pointParams) {
		PointParams = pointParams;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LayoutPoint [city_id=" + city_id + ", point_id=" + point_id + ", father_layout=" + father_layout
				+ ", point_name=" + point_name + ", point_code=" + point_code + ", point_pic=" + point_pic + "]";
	}
	

	
	
	

}
