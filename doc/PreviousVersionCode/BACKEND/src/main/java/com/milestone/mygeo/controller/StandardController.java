package com.milestone.mygeo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.milestone.mygeo.dao.IStandardDao;
import com.milestone.mygeo.models.Standard;
import com.milestone.mygeo.models.User;


@Controller  
@RequestMapping("/standard") 
public class StandardController {
	@Resource  
    private IStandardDao standardDao; 
    private int ret_num = 0;
    private int ret_error = -1;
    private String ret_message="";
	
	
    /** 
     * 新增标准
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/addStandArd",method=RequestMethod.POST)  
    @ResponseBody  
    public Object register(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	String name = String.valueOf(request.getParameter("name")).trim(); 
    	String code = String.valueOf(request.getParameter("code")).trim(); 
    	
    	if(name.equals("") || code.equals("") ){
    		ret_message = "名称或编码不能为空";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;  
    		
    	}
    	
		
		Standard standard = new Standard();
		standard.setName(name);
		standard.setCode(code);
    	List<Standard> standards = standardDao.getStandards(standard);
    	if(standards!=null && standards.size()>0){
    		ret_message = "该标准名称或编码已存在";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;  
    	}
    		
    	try{
    		standardDao.insertStandard(standard);
    		
    		String sql="CREATE TABLE `"+code+"_layer` (`id` int(11) NOT NULL AUTO_INCREMENT,"
    				+ "`father_id` int(11) NOT NULL DEFAULT '0',"
    				+ "`name` varchar(255) DEFAULT NULL,"
    				+ "`code` varchar(255) DEFAULT NULL,"
    				+ "`is_last` int(11) DEFAULT '0',"
    				+ "PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
    		Map<String , Object> map=new HashMap<String , Object>();
    		map.put("sql",sql); 
    		standardDao.createLayerTable(map);
    	}catch (Exception e) {
			// TODO: handle exception
    		ret_message = "创建标准失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
		
		    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    } 
    
    
    /** 
     * 查询标准
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/standards",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getAllStandard(HttpServletRequest request){  
    	JSONObject object  = new JSONObject();
    	List<Standard> standards = standardDao.getAllStandard();
        object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        object.put("data", standards);

        return object;          
        
    } 

}
