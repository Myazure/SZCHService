package org.myazure.controller;

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
import org.myazure.repository.ICityDao;
import org.myazure.repository.ILayoutAreaDao;
import org.myazure.repository.ILayoutCityDao;
import org.myazure.repository.ILayoutLineDao;
import org.myazure.repository.ILayoutPointDao;
import org.myazure.domain.AreaParam;
import org.myazure.domain.AreaParamSet;
import org.myazure.domain.City;
import org.myazure.domain.Layout;
import org.myazure.domain.LayoutArea;
import org.myazure.domain.LayoutCategory;
import org.myazure.domain.LayoutCity;
import org.myazure.domain.LayoutLine;
import org.myazure.domain.LayoutPoint;
import org.myazure.domain.LineParam;
import org.myazure.domain.LineParamSet;
import org.myazure.domain.PointParam;
import org.myazure.domain.PointParamSet;


@Controller  
@RequestMapping("/city") 
public class CityController {
	@Resource  
    private ICityDao cityDao; 
	@Resource  
    private ILayoutCityDao layoutCityDao; 
	@Resource 
	private ILayoutPointDao layoutPointDao;
	@Resource 
	private ILayoutLineDao layoutLineDao;
	@Resource 
	private ILayoutAreaDao layoutAreaDao;
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
    		
        	List<City> cities = cityDao.geCities(city);
        	if(cities!=null && cities.size()>0){
        		ret_message = "该标准名称已存在";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        	}
    		cityDao.insertCity(city);
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
    		
        	List<City> cities = cityDao.geCities(city);
        	if(cities!=null && cities.size()>0){
        		City existCity = cities.get(0);
        		if(existCity.getCity_id()!=city_id){
	        		ret_message = "该标准名称已存在";
	        		object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message);
	                return object; 
        		}
        	}
    		cityDao.updateCity(city);
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
    	int count = cityDao.cityCount();
    	int page_num = count/10+(count%10)==0?0:1;
    	
    	
    	
    	JSONObject object  = new JSONObject();
    	List<City> cities = cityDao.getCityByPage(index);
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
        	List<City> cities = cityDao.geCities(city);
        	if(cities!=null && cities.size()>0){
        		ret_message = "该标准名称已存在";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        	}
    		cityDao.insertCity(city);    

    		//图层
        	List<LayoutCategory> layoutCategories = layoutCityDao.getLayoutCategoryByCid(city_id);
        	if(layoutCategories!=null && layoutCategories.size()>0){
        		for(LayoutCategory layoutCategory:layoutCategories){
            		int father_category = layoutCategory.getId();
            		layoutCategory.setCity_id(city.getCity_id());        
                	layoutCityDao.insertLayoutCategory(layoutCategory);            	
                	LayoutCity layout_city = new LayoutCity(); 
            		layout_city.setCity_id(city.getCity_id());
            		layout_city.setLayout_category_id(layoutCategory.getId());
            		layoutCityDao.insertLayoutCity(layout_city);        		
            		List<Layout> layouts= layoutCityDao.selectLayoutByCategory(father_category);
            		if(layouts!=null && layouts.size()>0){
	            		for(Layout layout:layouts){
	            			Layout newLayout = layoutCityDao.getLayoutByLid(layout.getLayout_id());
	            			newLayout.setFather_category(layoutCategory.getId());
	            			newLayout.setLayout_name(layout.getLayout_name());	                    	
	                    	newLayout.setLayout_code(layout.getLayout_code());
	                    	newLayout.setCity_id(city.getCity_id());
	                		layoutCityDao.insertLayout(newLayout);
	                		//管点
	                		LayoutPoint layoutPoint = layoutPointDao.selectPointByLayout(layout.getLayout_id());
	                		LayoutPoint newLayoutPoint = new LayoutPoint();
	                		newLayoutPoint.setFather_layout(newLayout.getLayout_id());
	                		newLayoutPoint.setPoint_pic(layoutPoint.getPoint_pic());   		
	                		layoutPointDao.insertLayoutPoint(newLayoutPoint);   		
	                    	List<PointParam> pointParams = layoutPointDao.getPointParamsByPoint(layoutPoint.getPoint_id());  	
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
	                    			layoutPointDao.addPointParam(pointParam);
	                            	if(pointParam.getIs_select()==1){
	                            		List<PointParamSet> pointParamSets = layoutPointDao.getPointParamSetsByParamId(point_paramid);
	                            		for(PointParamSet pointParamSet:pointParamSets){
	                            			pointParamSet.setPoint_paramid(pointParam.getPoint_paramid());
//	                            			pointParamSet.setPic_path("");
	                            			layoutPointDao.addPointParamSet(pointParamSet);
	                            		}
	                           		
	                            	}               	
	                            	for(PointParam param:pointChildParams){
	                            		//如果父属性是当前属性，进行复制
	                            		if(param.getFather_param()==point_paramid){
	            	                		int paramid = param.getPoint_paramid();
	            	                		param.setFather_point(newLayoutPoint.getPoint_id());
	            	                		param.setFather_param(pointParam.getPoint_paramid());
	            	            			layoutPointDao.addPointParam(param);
	            	                    	if(param.getIs_select()==1){
	            	                    		List<PointParamSet> pointParamSets = layoutPointDao.getPointParamSetsByParamId(paramid);
	            	                    		for(PointParamSet pointParamSet:pointParamSets){
	            	                    			pointParamSet.setPoint_paramid(param.getPoint_paramid());
//	            	                    			pointParamSet.setPic_path("");
	            	                    			layoutPointDao.addPointParamSet(pointParamSet);
	            	                    		}
	            	                   		
	            	                    	}
	                            		}
	                            	}
	                    		}
	                		}
	                		//管线
	                		LayoutLine layoutLine = layoutLineDao.selectLineByLayout(layout.getLayout_id());
	                		LayoutLine newLayoutLine = new LayoutLine();
	                		newLayoutLine.setFather_layout(newLayout.getLayout_id());
	                		newLayoutLine.setLine_color(layoutLine.getLine_color());    		
	                		layoutLineDao.insertLayoutLine(newLayoutLine);   		
	                    	List<LineParam> lineParams = layoutLineDao.getLineParamsByLine(layoutLine.getLine_id());  	
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
	                    			layoutLineDao.addLineParam(lineParam);
	                            	if(lineParam.getIs_select()==1){
	                            		List<LineParamSet> lineParamSets = layoutLineDao.getLineParamSetsByParamId(line_paramid);
	                            		for(LineParamSet lineParamSet:lineParamSets){
	                            			lineParamSet.setLine_paramid(lineParam.getLine_paramid());
	                            			layoutLineDao.addLineParamSet(lineParamSet);
	                            		}
	                           		
	                            	}
	                            	for(LineParam param:lineChildParams){
	                            		//如果父属性是当前属性，进行复制
	                            		if(param.getFather_param() == line_paramid){
	                                		int paramid = param.getLine_paramid();
	                                		param.setFather_line(newLayoutLine.getLine_id());
	                                		param.setFather_param(lineParam.getLine_paramid());
	                                		layoutLineDao.addLineParam(param);
	                                    	if(param.getIs_select()==1){
	                                    		List<LineParamSet> lineParamSets = layoutLineDao.getLineParamSetsByParamId(paramid);
	                                    		for(LineParamSet lineParamSet:lineParamSets){
	                                    			lineParamSet.setLine_paramid(param.getLine_paramid());
	                                    			layoutLineDao.addLineParamSet(lineParamSet);
	                                    		}
	                                   		
	                                    	}
	                            		}
	                            	}
	                    		}
	                		}
	                		//范围    
	                		LayoutArea layoutArea = layoutAreaDao.selectAreaByLayout(layout.getLayout_id());
	                		LayoutArea newLayoutArea = new LayoutArea();
	                		newLayoutArea.setFather_layout(newLayout.getLayout_id());
	                		newLayoutArea.setArea_color(layoutArea.getArea_color());    		
	                		layoutAreaDao.insertLayoutArea(newLayoutArea);   		
	                    	List<AreaParam> areaParams = layoutAreaDao.getAreaParamsByArea(layoutArea.getArea_id());  	
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
	                            	layoutAreaDao.addAreaParam(areaParam);
	                            	if(areaParam.getIs_select()==1){
	                            		List<AreaParamSet> areaParamSets = layoutAreaDao.getAreaParamSetsByParamId(area_paramid);
	                            		for(AreaParamSet areaParamSet:areaParamSets){
	                            			areaParamSet.setArea_paramid(areaParam.getArea_paramid());
	                            			layoutAreaDao.addAreaParamSet(areaParamSet);
	                            		}
	                           		
	                            	}
	                            	
	                            	for(AreaParam param:areaChildParams){
	                            		//如果父属性是当前属性，进行复制
	                            		if(param.getFather_param() == area_paramid){
	                            			int paramid = param.getArea_paramid();
	                            			param.setFather_area(newLayoutArea.getArea_id());
	                            			param.setFather_param(areaParam.getArea_paramid());
	                                    	layoutAreaDao.addAreaParam(param);
	                                    	if(param.getIs_select()==1){
	                                    		List<AreaParamSet> areaParamSets = layoutAreaDao.getAreaParamSetsByParamId(paramid);
	                                    		for(AreaParamSet areaParamSet:areaParamSets){
	                                    			areaParamSet.setArea_paramid(param.getArea_paramid());
	                                    			layoutAreaDao.addAreaParamSet(areaParamSet);
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
        	List<LayoutCategory> layoutCategories = layoutCityDao.getLayoutCategoryByCid(city_id);
        	if(layoutCategories!=null && layoutCategories.size()>0){
        		for(LayoutCategory layoutCategory:layoutCategories){
        			int id = layoutCategory.getId();
        			List<Layout> layouts= layoutCityDao.selectLayoutByCategory(id);
            		if(layouts!=null && layouts.size()>0){
                		for(Layout layout:layouts){
        		    		//管点
        		    		LayoutPoint layoutPoint = layoutPointDao.selectPointByLayout(layout.getLayout_id());  		
        		        	List<PointParam> pointParams = layoutPointDao.getPointParamsByPoint(layoutPoint.getPoint_id());  	
        		        	if(pointParams!=null && pointParams.size()>0){
        		        		for(PointParam pointParam:pointParams){
        		        			int point_paramid = pointParam.getPoint_paramid();
        		        			layoutPointDao.deletePointParamByParamId(point_paramid);       
        		                	layoutPointDao.deletePointParamSetByParamId(point_paramid); 
        		        		}        		        		 
        		    		}
        		        	layoutPointDao.deletePointByPointId(layoutPoint.getPoint_id());    	
        		    		//管线
        		    		LayoutLine layoutLine = layoutLineDao.selectLineByLayout(layout.getLayout_id());  		
        		        	List<LineParam> lineParams = layoutLineDao.getLineParamsByLine(layoutLine.getLine_id());  	
        		        	if(lineParams!=null && lineParams.size()>0){
        		        		for(LineParam lineParam:lineParams){
        		        			int line_paramid = lineParam.getLine_paramid();
        		        			layoutLineDao.deleteLineParamByParamId(line_paramid);       
        		        			layoutLineDao.deleteLineParamSetByParamId(line_paramid); 
        		        		}        		        		 
        		    		}
        		        	layoutLineDao.deleteLineByLineId(layoutLine.getLine_id());  
        		    		//范围    
        		    		LayoutArea layoutArea = layoutAreaDao.selectAreaByLayout(layout.getLayout_id());  		
        		        	List<AreaParam> areaParams = layoutAreaDao.getAreaParamsByArea(layoutArea.getArea_id());  	
        		        	if(areaParams!=null && areaParams.size()>0){
        		        		for(AreaParam areaParam:areaParams){
        		        			int area_paramid = areaParam.getArea_paramid();
        		        			layoutAreaDao.deleteAreaParamByParamId(area_paramid);       
        		        			layoutAreaDao.deleteAreaParamSetByParamId(area_paramid); 
        		        		}        		        		 
        		    		}    
        		        	layoutAreaDao.deleteAreaByAreaId(layoutArea.getArea_id());        	
        		        	layoutCityDao.deleteLayoutByLayoutId(layout.getLayout_id());       
                		}
            		}
            		layoutCityDao.deleteLayoutCategoryById(id);
        		}
        	}
    		cityDao.deleteCityByCityId(city_id);
    		
    		
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
