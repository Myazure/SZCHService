package org.myazure.repository;

import java.util.List;
import java.util.Map;

import org.myazure.domain.Standard;



public interface IStandardDao {

	
	public	void insertStandard(Standard standard);
	
	public	void createLayerTable(Map<String , Object> map);
	
	public List<Standard> getStandards(Standard standard);	
	
	public List<Standard> getAllStandard();	

}
