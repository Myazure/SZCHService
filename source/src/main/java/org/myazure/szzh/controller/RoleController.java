package org.myazure.szzh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import org.myazure.repository.RoleRepository;
import org.myazure.entity.RoleGroup;



  

@Controller  
@RequestMapping("/role")  
public class RoleController {  
    @Resource  
    private RoleRepository roleDao; 
    private int ret_num = 0;
    private int ret_error = -1;
    private String ret_message="";
         
    /** 
     * 
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/roleGroups",method=RequestMethod.GET)  
    @ResponseBody  
    public Object roleGroups(HttpServletRequest request){  
    	JSONObject object  = new JSONObject();
        List<RoleGroup> roleGroups = roleDao.getAllRoleGroups();  
        object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        object.put("data", roleGroups);
        return object;          
        
    } 
    
    
    @RequestMapping(value="/addRoleGroup",method=RequestMethod.POST)  
    @ResponseBody  
    public Object addRoleGroup(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	try{
	    	String group_name = String.valueOf(request.getParameter("group_name")); 
	    	String role_type = String.valueOf(request.getParameter("role_type")); 
	    	if(group_name.equals("")){
	    		ret_message = "名称不能为空";
	    		object.put("ret_num", ret_error);
	    		object.put("ret_message", ret_message);
	            return object;  
	    		
	    	}			
	    	RoleGroup roleGroup = new RoleGroup();
	    	roleGroup.setGroup_name(group_name);
	    	roleGroup.setRole_type(role_type);
	    	List<RoleGroup> roleGroups = roleDao.geRoleGroups(roleGroup);
	    	if(roleGroups!=null && roleGroups.size()>0){
	    		ret_message = "该名称已存在";
	    		object.put("ret_num", ret_error);
	    		object.put("ret_message", ret_message);
	            return object;  
	    	}
	    		    	
	    	roleDao.insertRoleGroup(roleGroup);
	    	object.put("group_id", roleGroup.getGroup_id());		    		
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "新增分组失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}			
   	
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;   
    } 
      
    
    @RequestMapping(value="/updateRoleGroup",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateRoleGroup(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();   		
    	try{ 
    		int group_id = Integer.parseInt(request.getParameter("group_id")); 
        	String group_name = String.valueOf(request.getParameter("group_name")).trim(); 
        	String role_type = String.valueOf(request.getParameter("role_type")); 
        	if(group_name.equals("")){
        		ret_message = "名称不能为空";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        		
        	}
        	RoleGroup roleGroup = new RoleGroup();
        	roleGroup.setGroup_id(group_id);
    		roleGroup.setGroup_name(group_name);    	
    		roleGroup.setRole_type(role_type);
    		List<RoleGroup> roleGroups = roleDao.geRoleGroups(roleGroup);
        	if(roleGroups!=null && roleGroups.size()>0){
        		RoleGroup group = roleGroups.get(0);
        		if(group.getGroup_id()!=group_id){
	        		ret_message = "该名称已存在";
	        		object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message);
	                return object; 
        		}
        	}
        	roleDao.updateRoleGroup(roleGroup);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "修改标准失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
			
    	
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;    
    } 
    

  
    @RequestMapping(value="/deleteRoleGroup",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteRoleGroup(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();  	    	
    	int group_id = Integer.parseInt(request.getParameter("group_id")); 
    	roleDao.deleteRoleGroupById(group_id);		    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    } 
    
    
 
    
    
}
