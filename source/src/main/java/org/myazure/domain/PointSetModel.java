package org.myazure.domain;

import java.io.Serializable;
import java.util.List;

public class PointSetModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<PointParamSet> addParams;
	private List<PointParamSet> editParams;
	private List<PointParamSet> deleteParams;

	/**
	 * @return the addParams
	 */
	public List<PointParamSet> getAddParams() {
		return addParams;
	}

	/**
	 * @param addParams the addParams to set
	 */
	public void setAddParams(List<PointParamSet> addParams) {
		this.addParams = addParams;
	}

	/**
	 * @return the editParams
	 */
	public List<PointParamSet> getEditParams() {
		return editParams;
	}

	/**
	 * @param editParams the editParams to set
	 */
	public void setEditParams(List<PointParamSet> editParams) {
		this.editParams = editParams;
	}

	/**
	 * @return the deleteParams
	 */
	public List<PointParamSet> getDeleteParams() {
		return deleteParams;
	}

	/**
	 * @param deleteParams the deleteParams to set
	 */
	public void setDeleteParams(List<PointParamSet> deleteParams) {
		this.deleteParams = deleteParams;
	}
	
	

}
