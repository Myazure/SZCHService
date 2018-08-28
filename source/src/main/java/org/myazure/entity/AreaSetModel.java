package org.myazure.entity;

import java.io.Serializable;
import java.util.List;

public class AreaSetModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1187075219542363870L;
	private List<AreaParamSet> addParams;
	private List<AreaParamSet> editParams;
	private List<AreaParamSet> deleteParams;
	/**
	 * @return the addParams
	 */
	public List<AreaParamSet> getAddParams() {
		return addParams;
	}
	/**
	 * @param addParams the addParams to set
	 */
	public void setAddParams(List<AreaParamSet> addParams) {
		this.addParams = addParams;
	}
	/**
	 * @return the editParams
	 */
	public List<AreaParamSet> getEditParams() {
		return editParams;
	}
	/**
	 * @param editParams the editParams to set
	 */
	public void setEditParams(List<AreaParamSet> editParams) {
		this.editParams = editParams;
	}
	/**
	 * @return the deleteParams
	 */
	public List<AreaParamSet> getDeleteParams() {
		return deleteParams;
	}
	/**
	 * @param deleteParams the deleteParams to set
	 */
	public void setDeleteParams(List<AreaParamSet> deleteParams) {
		this.deleteParams = deleteParams;
	}


	
	

}
