package org.myazure.szzh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import org.myazure.repository.CityRepository;
import org.myazure.repository.LayoutAreaRepository;
import org.myazure.repository.LayoutCityRepository;
import org.myazure.repository.LayoutLineRepository;
import org.myazure.repository.LayoutPointRepository;
import org.myazure.entity.AreaParam;
import org.myazure.entity.AreaParamSet;
import org.myazure.entity.City;
import org.myazure.entity.Layout;
import org.myazure.entity.LayoutArea;
import org.myazure.entity.LayoutCategory;
import org.myazure.entity.LayoutCity;
import org.myazure.entity.LayoutLine;
import org.myazure.entity.LayoutPoint;
import org.myazure.entity.LineParam;
import org.myazure.entity.LineParamSet;
import org.myazure.entity.PointParam;
import org.myazure.entity.PointParamSet;


@Controller  
@RequestMapping("/city") 
public class CityController {
	@Resource  
    private CityRepository cityRepository; 
	@Resource  
    private LayoutCityRepository layoutCityRepository; 
	@Resource 
	private LayoutPointRepository layoutPointRepository;
	@Resource 
	private LayoutLineRepository layoutLineRepository;
	@Resource 
	private LayoutAreaRepository layoutAreaRepository;
  
	private int ret_num = 0;
    private int ret_error = -1;
    private String ret_message="";
	
	
    /** 
     * 新增标准
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/addCity",method=RequestMethod.POST)  
    @ResponseBody  
    public Object addCity(HttpServletRequest request){    	
    	JSONObject object  = new JSONObject();   		
    	try{
    		request.setCharacterEncoding("utf-8");        	
        	String name = String.valueOf(request.getParameter("name")).trim(); 
        	String creater = String.valueOf(request.getParameter("creater")).trim(); 
        	
        	if(name.equals("")){
        		ret_message = "名称不能为空";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        		
        	}
    		City city = new City();
    		city.setCity_name(name);
    		city.setCreated_at(new Date());
    		city.setCreater(creater);
    		
        	List<City> cities = cityRepository.geCities(city);
        	if(cities!=null && cities.size()>0){
        		ret_message = "该标准名称已存在";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        	}
    		cityRepository.insertCity(city);
    		object.put("city_id", city.getCity_id());
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
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
     * 修改标准
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/updateCity",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateCity(int city_id,String name,String creater){  
    	JSONObject object  = new JSONObject();   		
    	try{
    		
//    		request.setCharacterEncoding("UTF-8"); 
//    		int city_id = Integer.parseInt(request.getParameter("city_id"));  
//        	String name = String.valueOf(request.getParameter("name")).trim(); 
//        	String creater = String.valueOf(request.getParameter("creater")).trim(); 
        	if(name.equals("")){
        		ret_message = "名称不能为空";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        		
        	}
    		City city = new City();
    		city.setCity_id(city_id);
    		city.setCity_name(name);
    		city.setCreater(creater);
    		
        	List<City> cities = cityRepository.geCities(city);
        	if(cities!=null && cities.size()>0){
        		City existCity = cities.get(0);
        		if(existCity.getCity_id()!=city_id){
	        		ret_message = "该标准名称已存在";
	        		object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message);
	                return object; 
        		}
        	}
    		cityRepository.updateCity(city);
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
    
    
    /** 
     * 查询标准
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/cities",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getAllStandard(HttpServletRequest request){  
    	int page = Integer.parseInt(request.getParameter("page"));
    	if(page<=0){
    		page=1;
    	}
    	int index = (page-1)*10;
    	int count = cityRepository.cityCount();
    	int page_num = count/10+(count%10)==0?0:1;
    	
    	
    	
    	JSONObject object  = new JSONObject();
    	List<City> cities = cityRepository.getCityByPage(index);
        object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        object.put("data", cities);
        object.put("page_num", page_num);
        object.put("count", count);
        return object;          
        
    } 
    
    
    /** 
     * 复制标准
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/copyCity",method=RequestMethod.POST)  
    @ResponseBody  
    public Object copyCity(HttpServletRequest request){  	
    	JSONObject object  = new JSONObject();   	    	
    	try{
    		int city_id = Integer.parseInt(request.getParameter("city_id")); 
    		String name = String.valueOf(request.getParameter("name")).trim(); 
        	String creater = String.valueOf(request.getParameter("creater")).trim(); 
        	if(name.equals("")){
        		ret_message = "名称不能为空";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        		
        	}
    		City city = new City();
    		city.setCity_name(name);
    		city.setCreated_at(new Date());
    		city.setCreater(creater);    		
        	List<City> cities = cityRepository.geCities(city);
        	if(cities!=null && cities.size()>0){
        		ret_message = "该标准名称已存在";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        	}
    		cityRepository.insertCity(city);    

    		//图层
        	List<LayoutCategory> layoutCategories = layoutCityRepository.getLayoutCategoryByCid(city_id);
        	if(layoutCategories!=null && layoutCategories.size()>0){
        		for(LayoutCategory layoutCategory:layoutCategories){
            		int father_category = layoutCategory.getId();
            		layoutCategory.setCity_id(city.getCity_id());        
                	layoutCityRepository.insertLayoutCategory(layoutCategory);            	
                	LayoutCity layout_city = new LayoutCity(); 
            		layout_city.setCity_id(city.getCity_id());
            		layout_city.setLayout_category_id(layoutCategory.getId());
            		layoutCityRepository.insertLayoutCity(layout_city);        		
            		List<Layout> layouts= layoutCityRepository.selectLayoutByCategory(father_category);
            		if(layouts!=null && layouts.size()>0){
	            		for(Layout layout:layouts){
	            			Layout newLayout = layoutCityRepository.getLayoutByLid(layout.getLayout_id());
	            			newLayout.setFather_category(layoutCategory.getId());
	            			newLayout.setLayout_name(layout.getLayout_name());	                    	
	                    	newLayout.setLayout_code(layout.getLayout_code());
	                    	newLayout.setCity_id(city.getCity_id());
	                		layoutCityRepository.insertLayout(newLayout);
	                		//管点
	                		LayoutPoint layoutPoint = layoutPointRepository.selectPointByLayout(layout.getLayout_id());
	                		LayoutPoint newLayoutPoint = new LayoutPoint();
	                		newLayoutPoint.setFather_layout(newLayout.getLayout_id());
	                		newLayoutPoint.setPoint_pic(layoutPoint.getPoint_pic());   		
	                		layoutPointRepository.insertLayoutPoint(newLayoutPoint);   		
	                    	List<PointParam> pointParams = layoutPointRepository.getPointParamsByPoint(layoutPoint.getPoint_id());  	
	                    	if(pointParams!=null && pointParams.size()>0){
//	                    		for(PointParam pointParam:pointParams){
//	                    			int point_paramid = pointParam.getPoint_paramid();
//	                    			pointParam.setFather_point(newLayoutPoint.getPoint_id());
//	                    			pointParam.setFather_param(0);
//	                    			layoutPointDao.addPointParam(pointParam);
//	                            	if(pointParam.getIs_select()==1){
//	                            		List<PointParamSet> pointParamSets = layoutPointDao.getPointParamSetsByParamId(point_paramid);
//	                            		for(PointParamSet pointParamSet:pointParamSets){
//	                            			pointParamSet.setPoint_paramid(pointParam.getPoint_paramid());
//	                            			pointParamSet.setPic_path("");
//	                            			layoutPointDao.addPointParamSet(pointParamSet);
//	                            		}
//	                           		
//	                            	}
//	                    		}  
	                    		
	                    		//复制属性时有从属关系，分开复制
	                    		List<PointParam> pointFatherParams = new ArrayList<PointParam>();
	                    		List<PointParam> pointChildParams = new ArrayList<PointParam>();
	                    		for(PointParam pointParam:pointParams){
	                    			if(pointParam.getFather_param()==0){
	                    				pointFatherParams.add(pointParam);
	                    			}else{
	                    				pointChildParams.add(pointParam);
	                    			}
	                    		}
	                    		for(PointParam pointParam:pointFatherParams){        			
	                    			//无父属性的复制
	                    			int point_paramid = pointParam.getPoint_paramid();
	                    			pointParam.setFather_point(newLayoutPoint.getPoint_id());
	                    			pointParam.setFather_param(0);
	                    			layoutPointRepository.addPointParam(pointParam);
	                            	if(pointParam.getIs_select()==1){
	                            		List<PointParamSet> pointParamSets = layoutPointRepository.getPointParamSetsByParamId(point_paramid);
	                            		for(PointParamSet pointParamSet:pointParamSets){
	                            			pointParamSet.setPoint_paramid(pointParam.getPoint_paramid());
//	                            			pointParamSet.setPic_path("");
	                            			layoutPointRepository.addPointParamSet(pointParamSet);
	                            		}
	                           		
	                            	}               	
	                            	for(PointParam param:pointChildParams){
	                            		//如果父属性是当前属性，进行复制
	                            		if(param.getFather_param()==point_paramid){
	            	                		int paramid = param.getPoint_paramid();
	            	                		param.setFather_point(newLayoutPoint.getPoint_id());
	            	                		param.setFather_param(pointParam.getPoint_paramid());
	            	            			layoutPointRepository.addPointParam(param);
	            	                    	if(param.getIs_select()==1){
	            	                    		List<PointParamSet> pointParamSets = layoutPointRepository.getPointParamSetsByParamId(paramid);
	            	                    		for(PointParamSet pointParamSet:pointParamSets){
	            	                    			pointParamSet.setPoint_paramid(param.getPoint_paramid());
//	            	                    			pointParamSet.setPic_path("");
	            	                    			layoutPointRepository.addPointParamSet(pointParamSet);
	            	                    		}
	            	                   		
	            	                    	}
	                            		}
	                            	}
	                    		}
	                		}
	                		//管线
	                		LayoutLine layoutLine = layoutLineRepository.selectLineByLayout(layout.getLayout_id());
	                		LayoutLine newLayoutLine = new LayoutLine();
	                		newLayoutLine.setFather_layout(newLayout.getLayout_id());
	                		newLayoutLine.setLine_color(layoutLine.getLine_color());    		
	                		layoutLineRepository.insertLayoutLine(newLayoutLine);   		
	                    	List<LineParam> lineParams = layoutLineRepository.getLineParamsByLine(layoutLine.getLine_id());  	
	                    	if(lineParams!=null && lineParams.size()>0){
//	                    		for(LineParam lineParam:lineParams){
//	                    			int line_paramid = lineParam.getLine_paramid();
//	                    			lineParam.setFather_line(newLayoutLine.getLine_id());
//	                    			lineParam.setFather_param(0);
//	                    			layoutLineDao.addLineParam(lineParam);
//	                            	if(lineParam.getIs_select()==1){
//	                            		List<LineParamSet> lineParamSets = layoutLineDao.getLineParamSetsByParamId(line_paramid);
//	                            		for(LineParamSet lineParamSet:lineParamSets){
//	                            			lineParamSet.setLine_paramid(lineParam.getLine_paramid());
//	                            			layoutLineDao.addLineParamSet(lineParamSet);
//	                            		}
//	                           		
//	                            	}
//	                    		} 
	                    		//复制属性时有从属关系，分开复制
	                    		List<LineParam> lineFatherParams = new ArrayList<LineParam>();
	                    		List<LineParam> lineChildParams = new ArrayList<LineParam>();
	                    		for(LineParam pointParam:lineParams){
	                    			if(pointParam.getFather_param()==0){
	                    				lineFatherParams.add(pointParam);
	                    			}else{
	                    				lineChildParams.add(pointParam);
	                    			}
	                    		}
	                    		
	                    		for(LineParam lineParam:lineFatherParams){
	                    			int line_paramid = lineParam.getLine_paramid();
	                    			lineParam.setFather_line(newLayoutLine.getLine_id());
	                    			lineParam.setFather_param(0);
	                    			layoutLineRepository.addLineParam(lineParam);
	                            	if(lineParam.getIs_select()==1){
	                            		List<LineParamSet> lineParamSets = layoutLineRepository.getLineParamSetsByParamId(line_paramid);
	                            		for(LineParamSet lineParamSet:lineParamSets){
	                            			lineParamSet.setLine_paramid(lineParam.getLine_paramid());
	                            			layoutLineRepository.addLineParamSet(lineParamSet);
	                            		}
	                           		
	                            	}
	                            	for(LineParam param:lineChildParams){
	                            		//如果父属性是当前属性，进行复制
	                            		if(param.getFather_param() == line_paramid){
	                                		int paramid = param.getLine_paramid();
	                                		param.setFather_line(newLayoutLine.getLine_id());
	                                		param.setFather_param(lineParam.getLine_paramid());
	                                		layoutLineRepository.addLineParam(param);
	                                    	if(param.getIs_select()==1){
	                                    		List<LineParamSet> lineParamSets = layoutLineRepository.getLineParamSetsByParamId(paramid);
	                                    		for(LineParamSet lineParamSet:lineParamSets){
	                                    			lineParamSet.setLine_paramid(param.getLine_paramid());
	                                    			layoutLineRepository.addLineParamSet(lineParamSet);
	                                    		}
	                                   		
	                                    	}
	                            		}
	                            	}
	                    		}
	                		}
	                		//范围    
	                		LayoutArea layoutArea = layoutAreaRepository.selectAreaByLayout(layout.getLayout_id());
	                		LayoutArea newLayoutArea = new LayoutArea();
	                		newLayoutArea.setFather_layout(newLayout.getLayout_id());
	                		newLayoutArea.setArea_color(layoutArea.getArea_color());    		
	                		layoutAreaRepository.insertLayoutArea(newLayoutArea);   		
	                    	List<AreaParam> areaParams = layoutAreaRepository.getAreaParamsByArea(layoutArea.getArea_id());  	
	                    	if(areaParams!=null && areaParams.size()>0){
//	                    		for(AreaParam areaParam:areaParams){
//	                    			int area_paramid = areaParam.getArea_paramid();
//	                    			areaParam.setFather_area(newLayoutArea.getArea_id());
//	                    			areaParam.setFather_param(0);
//	                            	layoutAreaDao.addAreaParam(areaParam);
//	                            	if(areaParam.getIs_select()==1){
//	                            		List<AreaParamSet> areaParamSets = layoutAreaDao.getAreaParamSetsByParamId(area_paramid);
//	                            		for(AreaParamSet areaParamSet:areaParamSets){
//	                            			areaParamSet.setArea_paramid(areaParam.getArea_paramid());
//	                            			layoutAreaDao.addAreaParamSet(areaParamSet);
//	                            		}
//	                           		
//	                            	}
//	                    		}
	                    		//复制属性时有从属关系，分开复制
	                    		List<AreaParam> areaFatherParams = new ArrayList<AreaParam>();
	                    		List<AreaParam> areaChildParams = new ArrayList<AreaParam>();
	                    		for(AreaParam areaParam:areaParams){
	                    			if(areaParam.getFather_param()==0){
	                    				areaFatherParams.add(areaParam);
	                    			}else{
	                    				areaChildParams.add(areaParam);
	                    			}
	                    		}
	                    		
	                    		
	                    		for(AreaParam areaParam:areaFatherParams){
	                    			int area_paramid = areaParam.getArea_paramid();
	                    			areaParam.setFather_area(newLayoutArea.getArea_id());
	                    			areaParam.setFather_param(0);
	                            	layoutAreaRepository.addAreaParam(areaParam);
	                            	if(areaParam.getIs_select()==1){
	                            		List<AreaParamSet> areaParamSets = layoutAreaRepository.getAreaParamSetsByParamId(area_paramid);
	                            		for(AreaParamSet areaParamSet:areaParamSets){
	                            			areaParamSet.setArea_paramid(areaParam.getArea_paramid());
	                            			layoutAreaRepository.addAreaParamSet(areaParamSet);
	                            		}
	                           		
	                            	}
	                            	
	                            	for(AreaParam param:areaChildParams){
	                            		//如果父属性是当前属性，进行复制
	                            		if(param.getFather_param() == area_paramid){
	                            			int paramid = param.getArea_paramid();
	                            			param.setFather_area(newLayoutArea.getArea_id());
	                            			param.setFather_param(areaParam.getArea_paramid());
	                                    	layoutAreaRepository.addAreaParam(param);
	                                    	if(param.getIs_select()==1){
	                                    		List<AreaParamSet> areaParamSets = layoutAreaRepository.getAreaParamSetsByParamId(paramid);
	                                    		for(AreaParamSet areaParamSet:areaParamSets){
	                                    			areaParamSet.setArea_paramid(param.getArea_paramid());
	                                    			layoutAreaRepository.addAreaParamSet(areaParamSet);
	                                    		}
	                                   		
	                                    	}
	                            		}
	                            	}
	                    		} 
	                		}    	      	
	            		}
            		}
        		}
        		
        	}
        	object.put("city_id", city.getCity_id());
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "复制失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");   	
        return object;  
    } 
    
    
    /** 
     * 删除标准
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deleteCityByCityId",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteCityByCityId(HttpServletRequest request){  	
    	JSONObject object  = new JSONObject();   	    	
    	try{
    		int city_id = Integer.parseInt(request.getParameter("city_id")); 
    		
    		//图层
        	List<LayoutCategory> layoutCategories = layoutCityRepository.getLayoutCategoryByCid(city_id);
        	if(layoutCategories!=null && layoutCategories.size()>0){
        		for(LayoutCategory layoutCategory:layoutCategories){
        			int id = layoutCategory.getId();
        			List<Layout> layouts= layoutCityRepository.selectLayoutByCategory(id);
            		if(layouts!=null && layouts.size()>0){
                		for(Layout layout:layouts){
        		    		//管点
        		    		LayoutPoint layoutPoint = layoutPointRepository.selectPointByLayout(layout.getLayout_id());  		
        		        	List<PointParam> pointParams = layoutPointRepository.getPointParamsByPoint(layoutPoint.getPoint_id());  	
        		        	if(pointParams!=null && pointParams.size()>0){
        		        		for(PointParam pointParam:pointParams){
        		        			int point_paramid = pointParam.getPoint_paramid();
        		        			layoutPointRepository.deletePointParamByParamId(point_paramid);       
        		                	layoutPointRepository.deletePointParamSetByParamId(point_paramid); 
        		        		}        		        		 
        		    		}
        		        	layoutPointRepository.deletePointByPointId(layoutPoint.getPoint_id());    	
        		    		//管线
        		    		LayoutLine layoutLine = layoutLineRepository.selectLineByLayout(layout.getLayout_id());  		
        		        	List<LineParam> lineParams = layoutLineRepository.getLineParamsByLine(layoutLine.getLine_id());  	
        		        	if(lineParams!=null && lineParams.size()>0){
        		        		for(LineParam lineParam:lineParams){
        		        			int line_paramid = lineParam.getLine_paramid();
        		        			layoutLineRepository.deleteLineParamByParamId(line_paramid);       
        		        			layoutLineRepository.deleteLineParamSetByParamId(line_paramid); 
        		        		}        		        		 
        		    		}
        		        	layoutLineRepository.deleteLineByLineId(layoutLine.getLine_id());  
        		    		//范围    
        		    		LayoutArea layoutArea = layoutAreaRepository.selectAreaByLayout(layout.getLayout_id());  		
        		        	List<AreaParam> areaParams = layoutAreaRepository.getAreaParamsByArea(layoutArea.getArea_id());  	
        		        	if(areaParams!=null && areaParams.size()>0){
        		        		for(AreaParam areaParam:areaParams){
        		        			int area_paramid = areaParam.getArea_paramid();
        		        			layoutAreaRepository.deleteAreaParamByParamId(area_paramid);       
        		        			layoutAreaRepository.deleteAreaParamSetByParamId(area_paramid); 
        		        		}        		        		 
        		    		}    
        		        	layoutAreaRepository.deleteAreaByAreaId(layoutArea.getArea_id());        	
        		        	layoutCityRepository.deleteLayoutByLayoutId(layout.getLayout_id());       
                		}
            		}
            		layoutCityRepository.deleteLayoutCategoryById(id);
        		}
        	}
    		cityRepository.deleteCityByCityId(city_id);
    		
    		
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "复制失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");   	
        return object;  
    } 

}
