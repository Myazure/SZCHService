package com.milestone.mygeo.dao;

import java.util.List;

import com.milestone.mygeo.models.Coordinate;

public interface ICoordinateDao {
	public void insertCoordinate(Coordinate coordinate);
	
	public void updateCoordinate(Coordinate coordinate);
	
	public List<Coordinate> selectAllCoordinate();
	
	public void deleteCoordinate(int id);
	
	public Coordinate selectCoordinateById(int id);

}
