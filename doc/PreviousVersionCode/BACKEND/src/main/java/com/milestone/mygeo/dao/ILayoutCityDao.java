package com.milestone.mygeo.dao;

import java.util.List;

import com.milestone.mygeo.models.Layout;
import com.milestone.mygeo.models.LayoutCategory;
import com.milestone.mygeo.models.LayoutCity;




public interface ILayoutCityDao {
	
	public List<LayoutCategory> getLayoutCategoryByCid(int city_id);	
	
	public void insertLayoutCategory(LayoutCategory layoutCategory);
	
	public void updateLayoutCategory(LayoutCategory layoutCategory);
	
	public List<LayoutCategory> getLayoutCategories(LayoutCategory layoutCategory);	
	
	public void insertLayoutCity(LayoutCity layoutCity);
	
	public void insertLayout(Layout layout);
	
	public List<Layout> getLayouts(Layout layout);
	
	public List<Layout> selectLayoutByCategory(int father_category);
	
	public List<Layout> getLayoutByCid(int city_id);	
	
	public Layout getLayoutByLid(int layout_id);
	
	public void deleteLayoutByLayoutId(int layout_id);
	
	public void deleteLayoutCategoryById(int id);
	
}
