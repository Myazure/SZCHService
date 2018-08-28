package com.milestone.mygeo.dao;

import java.util.List;

import com.milestone.mygeo.models.RoleGroup;



public interface IRoleDao {
	
	public List<RoleGroup> getAllRoleGroups();	
	
	public List<RoleGroup> geRoleGroups(RoleGroup roleGroup);	
	
	
	public	void insertRoleGroup(RoleGroup roleGroup);
	
	public	void updateRoleGroup(RoleGroup roleGroup);
	
	public void deleteRoleGroupById(int group_id);
	

}
