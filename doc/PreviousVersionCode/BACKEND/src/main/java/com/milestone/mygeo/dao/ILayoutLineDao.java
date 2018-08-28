package com.milestone.mygeo.dao;

import java.util.List;

import com.milestone.mygeo.models.LayoutLine;
import com.milestone.mygeo.models.LineParam;
import com.milestone.mygeo.models.LineParamSet;



public interface ILayoutLineDao {
	
	
	public void insertLayoutLine(LayoutLine layoutLine);
	
	public void addLineParamList(List<LineParam> lineParams);
	
	public void updateLayoutLine(LayoutLine layoutLine);
	
	public LayoutLine selectLineByLayout(int father_layout);
	
	public List<LineParam> getLineParams(LineParam lineParam);
	
	public void addLineParam(LineParam lineParam);
	
	public void updateLineParam(LineParam lineParam);
	
	public List<LineParam> getLineParamsByLine(int father_line);
	
	public void addLineParamSet(LineParamSet lineParamSet);
	
	public void updateLineParamSet(LineParamSet lineParamSet);
	
	public List<LineParamSet> getLineParamSetsByParamId(int line_paramid);	
	
	public LineParam getLineParamByParamId(int line_paramid);
	
	public void deleteLineParamSetById(int id);
	
	public void deleteLineParamByParamId(int line_paramid);
	
	public void deleteLineParamSetByParamId(int line_paramid);
	
	public void deleteLineByLineId(int line_id);
	
	public void updateLineParamOrderUp(LineParam lineParam);
	
	public void updateLineParamOrder(LineParam lineParam);
}
