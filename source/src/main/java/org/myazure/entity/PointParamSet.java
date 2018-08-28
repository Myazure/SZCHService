package org.myazure.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class PointParamSet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int point_paramid;
	private String name;
	private String value;
	private String pic_path;
	private MultipartFile pic;
	
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
	 * @return the point_paramid
	 */
	public int getPoint_paramid() {
		return point_paramid;
	}
	/**
	 * @param point_paramid the point_paramid to set
	 */
	public void setPoint_paramid(int point_paramid) {
		this.point_paramid = point_paramid;
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
	 * @return the pic_path
	 */
	public String getPic_path() {
		return pic_path;
	}
	/**
	 * @param pic_path the pic_path to set
	 */
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	

	/**
	 * @return the pic
	 */
	public MultipartFile getPic() {
		return pic;
	}
	/**
	 * @param pic the pic to set
	 */
	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PointParamSet [id=" + id + ", point_paramid=" + point_paramid + ", name=" + name + ", value=" + value
				+ "]";
	}

	
	

}
