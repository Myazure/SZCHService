package com.milestone.mygeo.dao;

import java.util.List;

import com.milestone.mygeo.models.City;




public interface ICityDao {
	
	public List<City> getAllCity();	
	
	public List<City> getCityByPage(int index);	
	
	public void insertCity(City city);
	
	public void updateCity(City city);
	
	public List<City> geCities(City city);
	
	public int cityCount();
	
	public void deleteCityByCityId(int city_id);
	
}
