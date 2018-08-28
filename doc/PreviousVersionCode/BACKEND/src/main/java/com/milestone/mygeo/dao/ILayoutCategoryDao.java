package com.milestone.mygeo.dao;

import java.util.List;

import com.milestone.mygeo.models.LayoutCategory;





public interface ILayoutCategoryDao {
	
	public List<LayoutCategory> getLayoutCategoryByFid(int father_id);	
	
	public void insertLayoutCategory(LayoutCategory layoutCategory);
	
	public List<LayoutCategory> getLayoutCategories(LayoutCategory layoutCategory);	
	

}
