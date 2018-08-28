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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.myazure.repository.ILayoutAreaDao;
import org.myazure.repository.ILayoutCityDao;
import org.myazure.repository.ILayoutLineDao;
import org.myazure.repository.ILayoutPointDao;
import org.myazure.domain.AreaParam;
import org.myazure.domain.AreaParamSet;
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
@RequestMapping("/layout_city") 
public class LayoutCityController {
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
    
    private String pointParamStr = "[{ 'param_name': '管点编码', 'param_code': 'GDBM' , 'param_type': 'VARCHAR', 'is_input': 1, 'is_select': 0 , 'is_must': 1 , 'is_light': 0, 'is_memory': 0, 'remark': '', 'param_length': '50', 'father_param': '0', 'is_operate': '0', 'order_num': '1'},"
    		+ "{ 'param_name': '地面标高', 'param_code': 'DMBG' , 'param_type': 'DOUBLE', 'is_input': 1, 'is_select': 0 , 'is_must': 1 , 'is_light': 0, 'is_memory': 0, 'remark': '', 'param_length': '9', 'father_param': '0', 'is_operate': '0', 'order_num': '2'},"
    		+ "{ 'param_name': '横坐标', 'param_code': 'E' , 'param_type': 'DOUBLE', 'is_input': 0, 'is_select': 0 , 'is_must': 1 , 'is_light': 0, 'is_memory': 0, 'remark': '', 'param_length': '9', 'father_param': '0', 'is_operate': '0', 'order_num': '3'},"
    		+ "{ 'param_name': '纵坐标', 'param_code': 'N' , 'param_type': 'DOUBLE', 'is_input': 0, 'is_select': 0 , 'is_must': 1 , 'is_light': 0, 'is_memory': 0, 'remark': '', 'param_length': '9', 'father_param': '0', 'is_operate': '0', 'order_num': '4'},"
    		+ "{ 'param_name': '附属物类型', 'param_code': 'FSWLX' , 'param_type': 'VARCHAR', 'is_input': 0, 'is_select': 1 , 'is_must': 0 , 'is_light': 0, 'is_memory': 1, 'remark': '', 'param_length': '', 'father_param': '0', 'is_operate': '0', 'order_num': '5'},"
    		+ "{ 'param_name': '管点类型', 'param_code': 'GDLX' , 'param_type': 'VARCHAR', 'is_input': 0, 'is_select': 1 , 'is_must': 0 , 'is_light': 0, 'is_memory': 1, 'remark': '', 'param_length': '', 'father_param': '0', 'is_operate': '0', 'order_num': '6'}]";		
    
    private String lineParamStr = "[{ 'param_name': '管段编号', 'param_code': 'GD_ID' , 'param_type': 'VARCHAR', 'is_input': 0, 'is_select': 0 , 'is_must': 1 , 'is_light': 0, 'is_memory': 0, 'remark': '', 'param_length': '', 'father_param': '0', 'is_operate': '0', 'order_num': '1'},"
    		+ "{ 'param_name': '起点点号', 'param_code': 'QD_ID' , 'param_type': 'VARCHAR', 'is_input': 0, 'is_select': 0 , 'is_must': 1 , 'is_light': 0, 'is_memory': 0, 'remark': '', 'param_length': '', 'father_param': '0', 'is_operate': '0', 'order_num': '2'},"
    		+ "{ 'param_name': '终点点号', 'param_code': 'ZD_ID' , 'param_type': 'VARCHAR', 'is_input': 0, 'is_select': 0 , 'is_must': 1 , 'is_light': 0, 'is_memory': 0, 'remark': '', 'param_length': '', 'father_param': '0', 'is_operate': '0', 'order_num': '3'}]";
	
    private String areaParamStr = "[{ 'param_name': '管井范围编号', 'param_code': 'GJFWBH' , 'param_type': 'VARCHAR', 'is_input': 1, 'is_select': 0 , 'is_must': 1 , 'is_light': 0, 'is_memory': 0, 'remark': '', 'param_length': '50', 'father_param': '0', 'is_operate': '0', 'order_num': '1'},"
    		+ "{ 'param_name': '关联井号', 'param_code': 'GLJH' , 'param_type': 'VARCHAR', 'is_input': 0, 'is_select': 0 , 'is_must': 1 , 'is_light': 0, 'is_memory': 0, 'remark': '', 'param_length': '', 'father_param': '0', 'is_operate': '0', 'order_num': '2'}]";
	
	
    
    /** 
     * 新增图层类型
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/addLayoutCategory",method=RequestMethod.POST)  
    @ResponseBody  
    public Object addLayoutCategory(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int city_id = Integer.parseInt(request.getParameter("city_id")); 
    	String name = String.valueOf(request.getParameter("name")).trim(); 
    	
    	if(name.equals("")){
    		ret_message = "名称不能为空";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;  
    		
    	}    	
    		
    	try{
    		LayoutCategory layoutCategory = new LayoutCategory();
    		layoutCategory.setName(name);
    		layoutCategory.setCity_id(city_id);

        	List<LayoutCategory> layoutCategories = layoutCityDao.getLayoutCategories(layoutCategory);
        	if(layoutCategories!=null && layoutCategories.size()>0){
        		ret_message = "该图层类型名称已存在";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        	}
        	layoutCityDao.insertLayoutCategory(layoutCategory);
        	
        	LayoutCity layout_city = new LayoutCity(); 
    		layout_city.setCity_id(city_id);
    		layout_city.setLayout_category_id(layoutCategory.getId());
    		layoutCityDao.insertLayoutCity(layout_city);
    		object.put("id", layoutCategory.getId());
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "添加图层类型失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
				    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    } 
    
    
    /** 
     * 修改图层类型
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/updateLayoutCategory",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateLayoutCategory(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int city_id = Integer.parseInt(request.getParameter("city_id")); 
    	int id = Integer.parseInt(request.getParameter("id")); 
    	String name = String.valueOf(request.getParameter("name")).trim(); 
    	
    	if(name.equals("")){
    		ret_message = "名称不能为空";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;  
    		
    	}    	
    		
    	try{
    		LayoutCategory layoutCategory = new LayoutCategory();
    		layoutCategory.setId(id);
    		layoutCategory.setName(name);
    		layoutCategory.setCity_id(city_id);

        	List<LayoutCategory> layoutCategories = layoutCityDao.getLayoutCategories(layoutCategory);
        	if(layoutCategories!=null && layoutCategories.size()>0){
        		LayoutCategory existLayoutCategory = layoutCategories.get(0);
        		if(existLayoutCategory.getId() != id){
	        		ret_message = "该图层类型名称已存在";
	        		object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message);
        		}
                return object;  
        	}
        	layoutCityDao.updateLayoutCategory(layoutCategory);
        	
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "添加图层类型失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
				    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    } 
    
    
    /** 
     * 查询图层列表
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/layoutCategories",method=RequestMethod.GET)  
    @ResponseBody  
    public Object layoutCategories(HttpServletRequest request){  
    	int city_id = Integer.parseInt(request.getParameter("city_id")); 
    	JSONObject object  = new JSONObject();
    	List<LayoutCategory> layoutCategories = layoutCityDao.getLayoutCategoryByCid(city_id);
        object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        object.put("data", layoutCategories);

        return object;          
        
    } 
    
    
    /** 
     * 新增图层
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/addLayout",method=RequestMethod.POST)  
    @ResponseBody  
    public Object addLayout(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int city_id = Integer.parseInt(request.getParameter("city_id")); 
    	int father_category = Integer.parseInt(request.getParameter("father_category")); 
    	String layout_name = String.valueOf(request.getParameter("layout_name")).trim(); 
    	String layout_code = String.valueOf(request.getParameter("layout_code")).trim(); 
    	if(layout_name==null || layout_name.equals("") || layout_name.equals("null")){
    		ret_message = "图层名称不能为空";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;  
    		
    	}
    	if(layout_code==null ||layout_code.equals("") ||layout_code.equals("null")){
    		ret_message = "图层代码不能为空";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;  
    		
    	}
    	
    		
    	try{
    		Layout layout = new Layout();
    		layout.setFather_category(father_category);
    		layout.setLayout_name(layout_name);
    		layout.setCity_id(city_id);
    		layout.setLayout_code(layout_code);    		
    		List<Layout> layouts = layoutCityDao.getLayouts(layout);
        	if(layouts!=null && layouts.size()>0){
        		ret_message = "当前标准下该名称或代码已存在";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        	}  
        	//添加图层
    		layoutCityDao.insertLayout(layout);
    		//添加管点及固定属性
    		LayoutPoint layoutPoint = new LayoutPoint();
    		layoutPoint.setFather_layout(layout.getLayout_id());
    		layoutPointDao.insertLayoutPoint(layoutPoint);
    		List<PointParam> pointParams = new ArrayList<PointParam>();
    		JSONArray pointArray = JSONArray.parseArray(pointParamStr);
    		for(int i=0;i<pointArray.size();i++){
    			PointParam pointParam= new PointParam();
    			pointParam.setFather_point(layoutPoint.getPoint_id());
    			pointParam.setParam_name(pointArray.getJSONObject(i).getString("param_name"));
    			pointParam.setParam_code(pointArray.getJSONObject(i).getString("param_code"));
    			pointParam.setParam_type(pointArray.getJSONObject(i).getString("param_type"));
    			pointParam.setIs_input(pointArray.getJSONObject(i).getInteger("is_input"));
    			pointParam.setIs_select(pointArray.getJSONObject(i).getInteger("is_select"));
    			pointParam.setIs_must(pointArray.getJSONObject(i).getInteger("is_must"));
    			pointParam.setIs_light(pointArray.getJSONObject(i).getInteger("is_light"));
    			pointParam.setIs_memory(pointArray.getJSONObject(i).getInteger("is_memory"));
    			pointParam.setRemark(pointArray.getJSONObject(i).getString("remark"));
    			pointParam.setParam_length(pointArray.getJSONObject(i).getString("param_length"));
    			pointParam.setIs_operate(pointArray.getJSONObject(i).getInteger("is_operate"));
    			pointParam.setOrder_num(pointArray.getJSONObject(i).getInteger("order_num"));
    			pointParams.add(pointParam);
    		} 
    		layoutPointDao.addPointParamList(pointParams);	
//    		//添加管线及固定属性
    		LayoutLine layoutLine = new LayoutLine();
    		layoutLine.setFather_layout(layout.getLayout_id());
    		layoutLine.setLine_color("");
    		layoutLineDao.insertLayoutLine(layoutLine);
    		List<LineParam> lineParams = new ArrayList<LineParam>();
    		JSONArray lineArray = JSONArray.parseArray(lineParamStr);
    		for(int i=0;i<lineArray.size();i++){
    			LineParam lineParam= new LineParam();
    			lineParam.setFather_line(layoutLine.getLine_id());
    			lineParam.setParam_name(lineArray.getJSONObject(i).getString("param_name"));
    			lineParam.setParam_code(lineArray.getJSONObject(i).getString("param_code"));
    			lineParam.setParam_type(lineArray.getJSONObject(i).getString("param_type"));
    			lineParam.setIs_input(lineArray.getJSONObject(i).getInteger("is_input"));
    			lineParam.setIs_select(lineArray.getJSONObject(i).getInteger("is_select"));
    			lineParam.setIs_must(lineArray.getJSONObject(i).getInteger("is_must"));
    			lineParam.setIs_light(lineArray.getJSONObject(i).getInteger("is_light"));
    			lineParam.setIs_memory(lineArray.getJSONObject(i).getInteger("is_memory"));
    			lineParam.setRemark(lineArray.getJSONObject(i).getString("remark"));
    			lineParam.setParam_length(lineArray.getJSONObject(i).getString("param_length"));
    			lineParam.setIs_operate(lineArray.getJSONObject(i).getInteger("is_operate"));
    			lineParam.setOrder_num(lineArray.getJSONObject(i).getInteger("order_num"));
    			lineParams.add(lineParam);
    		} 		
    		layoutLineDao.addLineParamList(lineParams);	  		
    		//添加范围面及固定属性
    		LayoutArea layoutArea = new LayoutArea();
    		layoutArea.setFather_layout(layout.getLayout_id());
    		layoutArea.setArea_color("");
    		layoutAreaDao.insertLayoutArea(layoutArea);
    		List<AreaParam> areaParams = new ArrayList<AreaParam>();
    		JSONArray areaArray = JSONArray.parseArray(areaParamStr);
    		for(int i=0;i<areaArray.size();i++){
    			AreaParam areaParam= new AreaParam();
    			areaParam.setFather_area(layoutArea.getArea_id());
    			areaParam.setParam_name(areaArray.getJSONObject(i).getString("param_name"));
    			areaParam.setParam_code(areaArray.getJSONObject(i).getString("param_code"));
    			areaParam.setParam_type(areaArray.getJSONObject(i).getString("param_type"));
    			areaParam.setIs_input(areaArray.getJSONObject(i).getInteger("is_input"));
    			areaParam.setIs_select(areaArray.getJSONObject(i).getInteger("is_select"));
    			areaParam.setIs_must(areaArray.getJSONObject(i).getInteger("is_must"));
    			areaParam.setIs_light(areaArray.getJSONObject(i).getInteger("is_light"));
    			areaParam.setIs_memory(areaArray.getJSONObject(i).getInteger("is_memory"));
    			areaParam.setRemark(areaArray.getJSONObject(i).getString("remark"));
    			areaParam.setParam_length(areaArray.getJSONObject(i).getString("param_length"));
    			areaParam.setIs_operate(areaArray.getJSONObject(i).getInteger("is_operate"));
    			areaParam.setOrder_num(areaArray.getJSONObject(i).getInteger("order_num"));
    			areaParams.add(areaParam);
    		}
    		layoutAreaDao.addAreaParamList(areaParams);  
    		object.put("layout_id", layout.getLayout_id());
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "创建图层失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
				    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    } 
    
    
    /** 
     * 根据大类查询图层
     * @return 
     */  
    @RequestMapping(value="/getLayoutByCategory",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getLayoutByCategory(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int father_category = Integer.parseInt(request.getParameter("father_category")); 

    	
    		
    	try{
    		List<Layout> layouts= layoutCityDao.selectLayoutByCategory(father_category);
    		object.put("data", layouts);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "获取图层失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}
				    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    } 
    
    
    /** 
     * 复制次级图层
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/copyLayout",method=RequestMethod.POST)  
    @ResponseBody  
    public Object copyLayout(HttpServletRequest request,Layout layout){  	
    	JSONObject object  = new JSONObject();   	    	
    	try{
        	//图层
    		List<Layout> layouts = layoutCityDao.getLayouts(layout);
        	if(layouts!=null && layouts.size()>0){
        		ret_message = "当前标准下该名称或代码已存在";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        	}  
        	Layout newLayout = layoutCityDao.getLayoutByLid(layout.getLayout_id());
        	newLayout.setLayout_name(layout.getLayout_name());
        	newLayout.setLayout_code(layout.getLayout_code());
    		layoutCityDao.insertLayout(newLayout);
    		//管点
    		LayoutPoint layoutPoint = layoutPointDao.selectPointByLayout(layout.getLayout_id());
    		LayoutPoint newLayoutPoint = new LayoutPoint();
    		newLayoutPoint.setFather_layout(newLayout.getLayout_id());
//    		newLayoutPoint.setPoint_pic("");   		
    		layoutPointDao.insertLayoutPoint(newLayoutPoint);   		
        	List<PointParam> pointParams = layoutPointDao.getPointParamsByPoint(layoutPoint.getPoint_id());  	
        	if(pointParams!=null && pointParams.size()>0){
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
//                			pointParamSet.setPic_path("");
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
//	                    			pointParamSet.setPic_path("");
	                    			layoutPointDao.addPointParamSet(pointParamSet);
	                    		}
	                   		
	                    	}
                		}
                	}
        		}	
        		
        		
//        		for(PointParam pointParam:pointParams){
//        			int point_paramid = pointParam.getPoint_paramid();
//        			pointParam.setFather_point(newLayoutPoint.getPoint_id());
//        			pointParam.setFather_param(0);
//        			layoutPointDao.addPointParam(pointParam);
//                	if(pointParam.getIs_select()==1){
//                		List<PointParamSet> pointParamSets = layoutPointDao.getPointParamSetsByParamId(point_paramid);
//                		for(PointParamSet pointParamSet:pointParamSets){
//                			pointParamSet.setPoint_paramid(pointParam.getPoint_paramid());
//                			pointParamSet.setPic_path("");
//                			layoutPointDao.addPointParamSet(pointParamSet);
//                		}
//               		
//                	}
//        		}        		        		 
    		}
    		//管线
    		LayoutLine layoutLine = layoutLineDao.selectLineByLayout(layout.getLayout_id());
    		LayoutLine newLayoutLine = new LayoutLine();
    		newLayoutLine.setFather_layout(newLayout.getLayout_id());
    		newLayoutLine.setLine_color(layoutLine.getLine_color());    		
    		layoutLineDao.insertLayoutLine(newLayoutLine);   		
        	List<LineParam> lineParams = layoutLineDao.getLineParamsByLine(layoutLine.getLine_id());  	
        	if(lineParams!=null && lineParams.size()>0){
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
        		
//        		for(LineParam lineParam:lineParams){
//        			int line_paramid = lineParam.getLine_paramid();
//        			lineParam.setFather_line(newLayoutLine.getLine_id());
//        			lineParam.setFather_param(0);
//        			layoutLineDao.addLineParam(lineParam);
//                	if(lineParam.getIs_select()==1){
//                		List<LineParamSet> lineParamSets = layoutLineDao.getLineParamSetsByParamId(line_paramid);
//                		for(LineParamSet lineParamSet:lineParamSets){
//                			lineParamSet.setLine_paramid(lineParam.getLine_paramid());
//                			layoutLineDao.addLineParamSet(lineParamSet);
//                		}
//               		
//                	}
//        		}        		        		 
    		}
    		//范围    
    		LayoutArea layoutArea = layoutAreaDao.selectAreaByLayout(layout.getLayout_id());
    		LayoutArea newLayoutArea = new LayoutArea();
    		newLayoutArea.setFather_layout(newLayout.getLayout_id());
    		newLayoutArea.setArea_color(layoutArea.getArea_color());    		
    		layoutAreaDao.insertLayoutArea(newLayoutArea);   		
        	List<AreaParam> areaParams = layoutAreaDao.getAreaParamsByArea(layoutArea.getArea_id());  	
        	if(areaParams!=null && areaParams.size()>0){
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
		
//        		for(AreaParam areaParam:areaParams){
//        			int area_paramid = areaParam.getArea_paramid();
//        			areaParam.setFather_area(newLayoutArea.getArea_id());
//        			areaParam.setFather_param(0);
//                	layoutAreaDao.addAreaParam(areaParam);
//                	if(areaParam.getIs_select()==1){
//                		List<AreaParamSet> areaParamSets = layoutAreaDao.getAreaParamSetsByParamId(area_paramid);
//                		for(AreaParamSet areaParamSet:areaParamSets){
//                			areaParamSet.setArea_paramid(areaParam.getArea_paramid());
//                			layoutAreaDao.addAreaParamSet(areaParamSet);
//                		}
//               		
//                	}
//        		}        		        		 
    		}    	      	
        	object.put("layout_id", newLayout.getLayout_id());
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
     * 复制一级图层
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/copyLayoutCategory",method=RequestMethod.POST)  
    @ResponseBody  
    public Object copyLayoutCategory(HttpServletRequest request){  	
    	JSONObject object  = new JSONObject();   	    	
    	try{
    		int city_id = Integer.parseInt(request.getParameter("city_id")); 
        	String name = String.valueOf(request.getParameter("name")).trim(); 
        	int id = Integer.parseInt(request.getParameter("id")); 
        	if(name.equals("")){
        		ret_message = "名称不能为空";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        		
        	}

        	LayoutCategory layoutCategory = new LayoutCategory();
    		layoutCategory.setName(name);
    		layoutCategory.setCity_id(city_id);
        	List<LayoutCategory> layoutCategories = layoutCityDao.getLayoutCategories(layoutCategory);
        	if(layoutCategories!=null && layoutCategories.size()>0){
        		ret_message = "该图层类型名称已存在";
        		object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message);
                return object;  
        	}
        	layoutCityDao.insertLayoutCategory(layoutCategory);        	
        	LayoutCity layout_city = new LayoutCity(); 
    		layout_city.setCity_id(city_id);
    		layout_city.setLayout_category_id(layoutCategory.getId());
    		layoutCityDao.insertLayoutCity(layout_city);
    		List<Layout> layouts= layoutCityDao.selectLayoutByCategory(id);
    		if(layouts!=null && layouts.size()>0){
        		for(Layout layout:layouts){ 
                	Layout newLayout = layoutCityDao.getLayoutByLid(layout.getLayout_id());
                	newLayout.setFather_category(layoutCategory.getId());
                	newLayout.setLayout_name(layout.getLayout_name()+new Date().getTime()/1000);
                	newLayout.setLayout_code(layout.getLayout_code()+new Date().getTime()/1000);
            		layoutCityDao.insertLayout(newLayout);
            		//管点
            		LayoutPoint layoutPoint = layoutPointDao.selectPointByLayout(layout.getLayout_id());
            		LayoutPoint newLayoutPoint = new LayoutPoint();
            		newLayoutPoint.setFather_layout(newLayout.getLayout_id());
            		newLayoutPoint.setPoint_pic("");   		
            		layoutPointDao.insertLayoutPoint(newLayoutPoint);   		
                	List<PointParam> pointParams = layoutPointDao.getPointParamsByPoint(layoutPoint.getPoint_id());  	
                	if(pointParams!=null && pointParams.size()>0){
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
//                        			pointParamSet.setPic_path("");
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
//        	                    			pointParamSet.setPic_path("");
        	                    			layoutPointDao.addPointParamSet(pointParamSet);
        	                    		}
        	                   		
        	                    	}
                        		}
                        	}
                		}
                		
                		
//                		for(PointParam pointParam:pointParams){
//                			int point_paramid = pointParam.getPoint_paramid();
//                			pointParam.setFather_point(newLayoutPoint.getPoint_id());
//                			pointParam.setFather_param(0);
//                			layoutPointDao.addPointParam(pointParam);
//                        	if(pointParam.getIs_select()==1){
//                        		List<PointParamSet> pointParamSets = layoutPointDao.getPointParamSetsByParamId(point_paramid);
//                        		for(PointParamSet pointParamSet:pointParamSets){
//                        			pointParamSet.setPoint_paramid(pointParam.getPoint_paramid());
//                        			pointParamSet.setPic_path("");
//                        			layoutPointDao.addPointParamSet(pointParamSet);
//                        		}
//                       		
//                        	}
//                		}        		        		 
            		}
            		//管线
            		LayoutLine layoutLine = layoutLineDao.selectLineByLayout(layout.getLayout_id());
            		LayoutLine newLayoutLine = new LayoutLine();
            		newLayoutLine.setFather_layout(newLayout.getLayout_id());
            		newLayoutLine.setLine_color(layoutLine.getLine_color());    		
            		layoutLineDao.insertLayoutLine(newLayoutLine);   		
                	List<LineParam> lineParams = layoutLineDao.getLineParamsByLine(layoutLine.getLine_id());  	
                	if(lineParams!=null && lineParams.size()>0){
//                		for(LineParam lineParam:lineParams){
//                			int line_paramid = lineParam.getLine_paramid();
//                			lineParam.setFather_line(newLayoutLine.getLine_id());
//                			lineParam.setFather_param(0);
//                			layoutLineDao.addLineParam(lineParam);
//                        	if(lineParam.getIs_select()==1){
//                        		List<LineParamSet> lineParamSets = layoutLineDao.getLineParamSetsByParamId(line_paramid);
//                        		for(LineParamSet lineParamSet:lineParamSets){
//                        			lineParamSet.setLine_paramid(lineParam.getLine_paramid());
//                        			layoutLineDao.addLineParamSet(lineParamSet);
//                        		}
//                       		
//                        	}
//                		}
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
//                		for(AreaParam areaParam:areaParams){
//                			int area_paramid = areaParam.getArea_paramid();
//                			areaParam.setFather_area(newLayoutArea.getArea_id());
//                			areaParam.setFather_param(0);
//                        	layoutAreaDao.addAreaParam(areaParam);
//                        	if(areaParam.getIs_select()==1){
//                        		List<AreaParamSet> areaParamSets = layoutAreaDao.getAreaParamSetsByParamId(area_paramid);
//                        		for(AreaParamSet areaParamSet:areaParamSets){
//                        			areaParamSet.setArea_paramid(areaParam.getArea_paramid());
//                        			layoutAreaDao.addAreaParamSet(areaParamSet);
//                        		}
//                       		
//                        	}
//                		} 
                		
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
    		
        	   	
        	object.put("id", layoutCategory.getId());
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
     * 删除次级图层
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deleteLayoutByLayoutId",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteLayoutByLayoutId(HttpServletRequest request){  	
    	JSONObject object  = new JSONObject();   	    	
    	try{
    		int layout_id = Integer.parseInt(request.getParameter("layout_id"));         	 
    		//管点
    		LayoutPoint layoutPoint = layoutPointDao.selectPointByLayout(layout_id);  		
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
    		LayoutLine layoutLine = layoutLineDao.selectLineByLayout(layout_id);  		
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
    		LayoutArea layoutArea = layoutAreaDao.selectAreaByLayout(layout_id);  		
        	List<AreaParam> areaParams = layoutAreaDao.getAreaParamsByArea(layoutArea.getArea_id());  	
        	if(areaParams!=null && areaParams.size()>0){
        		for(AreaParam areaParam:areaParams){
        			int area_paramid = areaParam.getArea_paramid();
        			layoutAreaDao.deleteAreaParamByParamId(area_paramid);       
        			layoutAreaDao.deleteAreaParamSetByParamId(area_paramid); 
        		}        		        		 
    		}    
        	layoutAreaDao.deleteAreaByAreaId(layoutArea.getArea_id());        	
        	layoutCityDao.deleteLayoutByLayoutId(layout_id);       
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
     * 删除一级图层
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deleteLayoutCategoryById",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteLayoutCategoryById(HttpServletRequest request){  	
    	JSONObject object  = new JSONObject();   	    	
    	try{
    		int id = Integer.parseInt(request.getParameter("id"));   
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
