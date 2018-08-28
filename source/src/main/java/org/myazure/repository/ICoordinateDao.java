package org.myazure.repository;

import java.util.List;

import org.myazure.domain.Coordinate;

public interface ICoordinateDao {
	public void insertCoordinate(Coordinate coordinate);
	
	public void updateCoordinate(Coordinate coordinate);
	
	public List<Coordinate> selectAllCoordinate();
	
	public void deleteCoordinate(int id);
	
	public Coordinate selectCoordinateById(int id);

}
