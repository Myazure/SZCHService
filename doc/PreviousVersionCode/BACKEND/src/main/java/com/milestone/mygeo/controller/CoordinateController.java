package com.milestone.mygeo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.milestone.mygeo.dao.ICoordinateDao;
import com.milestone.mygeo.models.Coordinate;


@Controller  
@RequestMapping("/coordinate") 
public class CoordinateController {
	@Resource 
	private ICoordinateDao coordinateDao;
    private int ret_num = 0;
    private int ret_error = -1;
    private String ret_message="";
    
    
    /** 
     * 新增坐标
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/addCoordinate",method=RequestMethod.POST)  
    @ResponseBody  
    public Object addCoordinate(HttpServletRequest request,Coordinate coordinate){    	
    	JSONObject object  = new JSONObject();   		
    	try{
    		coordinateDao.insertCoordinate(coordinate);
    		object.put("id", coordinate.getId());
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "新增失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
			
    	
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    } 

    
    /** 
     * 删除坐标
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deleteCoordinate",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteCoordinate(HttpServletRequest request){    	
    	JSONObject object  = new JSONObject();   		
    	try{
    		int id = Integer.parseInt(request.getParameter("id"));
    		coordinateDao.deleteCoordinate(id);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "删除失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
			
    	
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    }
    
    /** 
     * 修改坐标
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/updateCoordinate",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateCoordinate(HttpServletRequest request,Coordinate coordinate){    	
    	JSONObject object  = new JSONObject();   		
    	try{
    		coordinateDao.updateCoordinate(coordinate);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "修改失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
			
    	
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    }
    
    /** 
     * 查询坐标列表
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/coordinates",method=RequestMethod.GET)  
    @ResponseBody  
    public Object coordinates(HttpServletRequest request,Coordinate coordinate){    	
    	JSONObject object  = new JSONObject();   		
    	try{
    		List<Coordinate> coordinates = coordinateDao.selectAllCoordinate();
    		object.put("data", coordinates);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "查询失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
			
    	
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    }
}
