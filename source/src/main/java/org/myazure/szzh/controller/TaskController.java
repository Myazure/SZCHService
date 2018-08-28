package org.myazure.szzh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.myazure.repository.CityRepository;
import org.myazure.repository.CoordinateRepository;
import org.myazure.repository.LayoutAreaRepository;
import org.myazure.repository.LayoutCityRepository;
import org.myazure.repository.LayoutLineRepository;
import org.myazure.repository.LayoutPointRepository;
import org.myazure.entity.AreaParam;
import org.myazure.entity.AreaParamSet;
import org.myazure.entity.City;
import org.myazure.entity.Coordinate;
import org.myazure.entity.Layout;
import org.myazure.entity.LayoutArea;
import org.myazure.entity.LayoutLine;
import org.myazure.entity.LayoutPoint;
import org.myazure.entity.LineParam;
import org.myazure.entity.LineParamSet;
import org.myazure.entity.PointParam;
import org.myazure.entity.PointParamSet;


@Controller  
@RequestMapping("/task") 
public class TaskController {
	@Resource  
    private CityRepository cityDao; 
	@Resource  
    private LayoutCityRepository layoutCityDao; 
	@Resource 
	private LayoutPointRepository layoutPointDao;
	@Resource 
	private LayoutLineRepository layoutLineDao;
	@Resource 
	private LayoutAreaRepository layoutAreaDao;
	@Resource 
	private CoordinateRepository coordinateDao;
    private int ret_num = 0;
    private int ret_error = -1;
    private String ret_message="";
	
    
    /** 
     * 查询标准
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/taskSet",method=RequestMethod.GET)  
    @ResponseBody  
    public Object taskSet(HttpServletRequest request){  
    	JSONObject object  = new JSONObject();
    	
    	try{
    		List<City> cities = cityDao.getAllCity();
    		List<Coordinate> coordinates = coordinateDao.selectAllCoordinate();
    		object.put("cityData", cities);
    		object.put("coordinateData", coordinates);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "获取失败";
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
    @RequestMapping(value="/taskMessage",method=RequestMethod.GET)  
    @ResponseBody  
    public Object taskMessage(HttpServletRequest request){  
    	int city_id = Integer.parseInt(request.getParameter("city_id"));
    	int geo_id = Integer.parseInt(request.getParameter("geo_id")); 	
    	JSONObject object  = new JSONObject();
    	
    	try{
    		JSONArray jsonArray = new JSONArray();	
    		List<Layout> layouts = layoutCityDao.getLayoutByCid(city_id);
    		if(layouts!=null && layouts.size()>0){
    			for(int i=0;i<layouts.size();i++){
    				JSONObject layoutObject  = new JSONObject();   				
    				Layout layout = layouts.get(i);
    				layoutObject.put("layout_id", layout.getLayout_id());
    				layoutObject.put("layout_name", layout.getLayout_name());
    				layoutObject.put("layout_code", layout.getLayout_code());
    				//管点信息
    				LayoutPoint layoutPoint= layoutPointDao.selectPointByLayout(layout.getLayout_id());	
    				List<PointParam> pointParams= layoutPointDao.getPointParamsByPoint(layoutPoint.getPoint_id());   				
    				if(pointParams!=null && pointParams.size()>0){					
    	    			for(int j=0;j<pointParams.size();j++){
    	    				PointParam pointParam = pointParams.get(j);   	    				
    	    				List<PointParamSet> pointParamSets= layoutPointDao.getPointParamSetsByParamId(pointParam.getPoint_paramid());
    	    				pointParam.setPointParamSets(pointParamSets);
    	    			}
    				}
    				layoutPoint.setPointParams(pointParams);
    				layoutObject.put("point", layoutPoint);
    				//管线信息
    				LayoutLine layoutline= layoutLineDao.selectLineByLayout(layout.getLayout_id());	
    				List<LineParam> lineParams= layoutLineDao.getLineParamsByLine(layoutline.getLine_id());   				
    				if(lineParams!=null && lineParams.size()>0){					
    	    			for(int j=0;j<lineParams.size();j++){
    	    				LineParam lineParam = lineParams.get(j);   	    				
    	    				List<LineParamSet> lineParamSets= layoutLineDao.getLineParamSetsByParamId(lineParam.getLine_paramid());
    	    				lineParam.setLineParamSets(lineParamSets);
    	    			}
    				}
    				layoutline.setLineParams(lineParams);
    				layoutObject.put("line", layoutline);  				
    				//面信息
    				LayoutArea layoutArea= layoutAreaDao.selectAreaByLayout(layout.getLayout_id());	
    				List<AreaParam> areaParams= layoutAreaDao.getAreaParamsByArea(layoutArea.getArea_id());   				
    				if(areaParams!=null && areaParams.size()>0){					
    	    			for(int j=0;j<areaParams.size();j++){
    	    				AreaParam areaParam = areaParams.get(j);   	    				
    	    				List<AreaParamSet> areaParamSets= layoutAreaDao.getAreaParamSetsByParamId(areaParam.getArea_paramid());
    	    				areaParam.setAreaParamSets(areaParamSets);
    	    			}
    				}
    				layoutArea.setAreaParams(areaParams);
    				layoutObject.put("area", layoutArea);
    				jsonArray.add(layoutObject);
    			}
    		}
    		object.put("data", jsonArray);    		
    		Coordinate coordinate = coordinateDao.selectCoordinateById(geo_id);
    		object.put("coordinate", coordinate);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "获取失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
    	
    	
        object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;          
        
    } 
    
    
    

}
