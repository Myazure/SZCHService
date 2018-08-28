package org.myazure.repository;

import java.util.List;

import org.myazure.domain.AreaParam;
import org.myazure.domain.AreaParamSet;
import org.myazure.domain.LayoutArea;


public interface ILayoutAreaDao {
	
	
	public void insertLayoutArea(LayoutArea layoutArea);
	
	public void addAreaParamList(List<AreaParam> areaParams);
	
	public void updateLayoutArea(LayoutArea layoutArea);
	
	public LayoutArea selectAreaByLayout(int father_layout);
	
	public List<AreaParam> getAreaParams(AreaParam areaParam);
	
	public void addAreaParam(AreaParam areaParam);
	
	public void updateAreaParam(AreaParam areaParam);
	
	public List<AreaParam> getAreaParamsByArea(int father_area);
	
	public void addAreaParamSet(AreaParamSet areaParamSet);
	
	public void updateAreaParamSet(AreaParamSet areaParamSet);
	
	public List<AreaParamSet> getAreaParamSetsByParamId(int area_paramid);	

	public AreaParam getAreaParamByParamId(int area_paramid);	
	
	public void deleteAreaParamSetById(int id);
	
	public void deleteAreaParamByParamId(int area_paramid);
	
	public void deleteAreaParamSetByParamId(int area_paramid);
	
	public void deleteAreaByAreaId(int area_id);
	
	public void updateAreaParamOrderUp(AreaParam areaParam);
	
	public void updateAreaParamOrder(AreaParam areaParam);
}
