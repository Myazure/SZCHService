package org.myazure.repository;

import java.util.List;

import org.myazure.domain.Layout;
import org.myazure.domain.LayoutCategory;
import org.myazure.domain.LayoutCity;




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
