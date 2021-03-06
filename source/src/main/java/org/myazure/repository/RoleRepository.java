package org.myazure.repository;

import java.util.List;

import org.myazure.entity.RoleGroup;



public interface RoleRepository {
	
	public List<RoleGroup> getAllRoleGroups();	
	
	public List<RoleGroup> geRoleGroups(RoleGroup roleGroup);	
	
	
	public	void insertRoleGroup(RoleGroup roleGroup);
	
	public	void updateRoleGroup(RoleGroup roleGroup);
	
	public void deleteRoleGroupById(int group_id);
	

}
