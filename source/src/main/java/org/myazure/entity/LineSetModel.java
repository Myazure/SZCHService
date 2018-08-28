package org.myazure.entity;

import java.io.Serializable;
import java.util.List;

public class LineSetModel implements Serializable{
	private List<LineParamSet> addParams;
	private List<LineParamSet> editParams;
	private List<LineParamSet> deleteParams;
	/**
	 * @return the addParams
	 */
	public List<LineParamSet> getAddParams() {
		return addParams;
	}
	/**
	 * @param addParams the addParams to set
	 */
	public void setAddParams(List<LineParamSet> addParams) {
		this.addParams = addParams;
	}
	/**
	 * @return the editParams
	 */
	public List<LineParamSet> getEditParams() {
		return editParams;
	}
	/**
	 * @param editParams the editParams to set
	 */
	public void setEditParams(List<LineParamSet> editParams) {
		this.editParams = editParams;
	}
	/**
	 * @return the deleteParams
	 */
	public List<LineParamSet> getDeleteParams() {
		return deleteParams;
	}
	/**
	 * @param deleteParams the deleteParams to set
	 */
	public void setDeleteParams(List<LineParamSet> deleteParams) {
		this.deleteParams = deleteParams;
	}

	
	

}
