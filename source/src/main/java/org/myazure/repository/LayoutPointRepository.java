package org.myazure.repository;

import java.util.List;

import org.myazure.entity.LayoutPoint;
import org.myazure.entity.PointParam;
import org.myazure.entity.PointParamSet;


public interface LayoutPointRepository {
	
	
	public void insertLayoutPoint(LayoutPoint layoutPoint);
	

	public void addPointParamList(List<PointParam> pointParams);
	
	public void updateLayoutPoint(LayoutPoint layoutPoint);
	
	public LayoutPoint selectPointByLayout(int father_layout);
	
	public List<PointParam> getPointParams(PointParam pointParam);
	
	public void addPointParam(PointParam pointParam);
	
	public void updatePointParam(PointParam pointParam);
	
	public List<PointParam> getPointParamsByPoint(int father_point);
	
	public void addPointParamSet(PointParamSet pointParamSet);
	
	public void updatePointParamSet(PointParamSet pointParamSet);
	
	public List<PointParamSet> getPointParamSetsByParamId(int point_paramid);
	
	public PointParam getPointParamByParamId(int point_paramid);
	
	public void deletePointParamSetById(int id);
	
	public void deletePointParamByParamId(int point_paramid);
	
	public void deletePointParamSetByParamId(int point_paramid);
	
	public void deletePointByPointId(int point_id);
	
	public void updatePointParamOrder(PointParam pointParam);
	
	public void updatePointParamOrderUp(PointParam pointParam);
	
}
