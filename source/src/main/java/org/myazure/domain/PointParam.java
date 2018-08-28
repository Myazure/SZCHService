package org.myazure.domain;

import java.io.Serializable;
import java.util.List;

public class PointParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int point_paramid;
	private int father_point;
	private String param_name;
	private String param_code;
	private String param_type;
	private int is_input;
	private int is_select;
	private int is_must;
	private int is_light;
	private int is_memory;
	private String remark;
	private String param_length;
	private int father_param;
	private int is_operate;
	private int order_num;
	
	private List<PointParamSet> pointParamSets;
	
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
	 * @return the father_point
	 */
	public int getFather_point() {
		return father_point;
	}
	/**
	 * @param father_point the father_point to set
	 */
	public void setFather_point(int father_point) {
		this.father_point = father_point;
	}
	/**
	 * @return the param_name
	 */
	public String getParam_name() {
		return param_name;
	}
	/**
	 * @param param_name the param_name to set
	 */
	public void setParam_name(String param_name) {
		this.param_name = param_name;
	}
	/**
	 * @return the param_code
	 */
	public String getParam_code() {
		return param_code;
	}
	/**
	 * @param param_code the param_code to set
	 */
	public void setParam_code(String param_code) {
		this.param_code = param_code;
	}
	/**
	 * @return the param_type
	 */
	public String getParam_type() {
		return param_type;
	}
	/**
	 * @param param_type the param_type to set
	 */
	public void setParam_type(String param_type) {
		this.param_type = param_type;
	}
	/**
	 * @return the is_input
	 */
	public int getIs_input() {
		return is_input;
	}
	/**
	 * @param is_input the is_input to set
	 */
	public void setIs_input(int is_input) {
		this.is_input = is_input;
	}
	/**
	 * @return the is_select
	 */
	public int getIs_select() {
		return is_select;
	}
	/**
	 * @param is_select the is_select to set
	 */
	public void setIs_select(int is_select) {
		this.is_select = is_select;
	}
	/**
	 * @return the is_must
	 */
	public int getIs_must() {
		return is_must;
	}
	/**
	 * @param is_must the is_must to set
	 */
	public void setIs_must(int is_must) {
		this.is_must = is_must;
	}
	/**
	 * @return the is_light
	 */
	public int getIs_light() {
		return is_light;
	}
	/**
	 * @param is_light the is_light to set
	 */
	public void setIs_light(int is_light) {
		this.is_light = is_light;
	}
	/**
	 * @return the is_memory
	 */
	public int getIs_memory() {
		return is_memory;
	}
	/**
	 * @param is_memory the is_memory to set
	 */
	public void setIs_memory(int is_memory) {
		this.is_memory = is_memory;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the param_length
	 */
	public String getParam_length() {
		return param_length;
	}
	/**
	 * @param param_length the param_length to set
	 */
	public void setParam_length(String param_length) {
		this.param_length = param_length;
	}
	
	
	/**
	 * @return the father_param
	 */
	public int getFather_param() {
		return father_param;
	}
	/**
	 * @param father_param the father_param to set
	 */
	public void setFather_param(int father_param) {
		this.father_param = father_param;
	}

	
	/**
	 * @return the pointParamSets
	 */
	public List<PointParamSet> getPointParamSets() {
		return pointParamSets;
	}
	/**
	 * @param pointParamSets the pointParamSets to set
	 */
	public void setPointParamSets(List<PointParamSet> pointParamSets) {
		this.pointParamSets = pointParamSets;
	}
	/**
	 * @return the is_operate
	 */
	public int getIs_operate() {
		return is_operate;
	}
	/**
	 * @param is_operate the is_operate to set
	 */
	public void setIs_operate(int is_operate) {
		this.is_operate = is_operate;
	}
	/**
	 * @return the order_num
	 */
	public int getOrder_num() {
		return order_num;
	}
	/**
	 * @param order_num the order_num to set
	 */
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PointParam [point_paramid=" + point_paramid + ", father_point=" + father_point + ", param_name="
				+ param_name + ", param_code=" + param_code + ", param_type=" + param_type + ", is_input=" + is_input
				+ ", is_select=" + is_select + ", is_must=" + is_must + ", is_light=" + is_light + ", is_memory="
				+ is_memory + ", remark=" + remark + ", param_length=" + param_length + ", father_param=" + father_param
				+ ", is_operate=" + is_operate + ", order_num=" + order_num + ", pointParamSets=" + pointParamSets
				+ "]";
	}
	

	
	
	
	
}
