package org.myazure.repository;

import java.util.List;

import org.myazure.entity.LayoutCategory;





public interface LayoutCategoryRepository {
	
	public List<LayoutCategory> getLayoutCategoryByFid(int father_id);	
	
	public void insertLayoutCategory(LayoutCategory layoutCategory);
	
	public List<LayoutCategory> getLayoutCategories(LayoutCategory layoutCategory);	
	

}
