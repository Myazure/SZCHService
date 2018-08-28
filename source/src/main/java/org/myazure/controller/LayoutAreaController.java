package org.myazure.controller;

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
import org.myazure.domain.AreaParam;
import org.myazure.domain.AreaParamSet;
import org.myazure.domain.AreaSetModel;
import org.myazure.domain.LayoutArea;




@Controller  
@RequestMapping("/layout_area") 
public class LayoutAreaController {
	@Resource  
    private ILayoutAreaDao layoutAreaDao; 
    private int ret_num = 0;
    private int ret_error = -1;
    private String ret_message="";
	
	
    /** 
     * 修改范围线
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/updateArea",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateArea(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int area_id = Integer.parseInt(request.getParameter("area_id")); 
    	String area_color = String.valueOf(request.getParameter("area_color")).trim(); 
    	  	    		
    	try{	 		
    		LayoutArea layoutArea = new LayoutArea();
    		layoutArea.setArea_id(area_id);
    		layoutArea.setArea_color(area_color);;
    		layoutAreaDao.updateLayoutArea(layoutArea);    		
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
     * 根据图层查询范围线信息
     * @return 
     */  
    @RequestMapping(value="/areaDetail",method=RequestMethod.GET)  
    @ResponseBody  
    public Object areaDetail(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int father_layout = Integer.parseInt(request.getParameter("father_layout"));	    		
    	try{
    		LayoutArea layoutArea= layoutAreaDao.selectAreaByLayout(father_layout);
    		object.put("data", layoutArea);
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
    
    
//    /** 
//     * 新增范围线属性
//     * @return 
//     */  
//    @RequestMapping(value="/addAreaParam",method=RequestMethod.POST)  
//    @ResponseBody  
//    public Object addAreaParam(HttpServletRequest request,AreaParam areaParam){ 
//    	JSONObject object  = new JSONObject();
// 	   	
//    	try{
//        	List<AreaParam> params = layoutAreaDao.getAreaParams(areaParam);
//        	if(params!=null && params.size()>0){
//    			ret_message = "该名称或编码的属性已存在";
//    			object.put("ret_num", ret_error);
//        		object.put("ret_message", ret_message+"名称或编码重复");
//                return object;
//    		}
//        	areaParam.setIs_operate(1);
//        	layoutAreaDao.addAreaParam(areaParam);	
//        	object.put("area_paramid", areaParam.getArea_paramid());
//    	}catch (Exception e) {
//			// TODO: handle exception
//    		e.printStackTrace();
//    		ret_message = "新增失败";
//    		object.put("ret_num", ret_error);
//    		object.put("ret_message", ret_message);
//            return object;
//		}		
//		object.put("ret_num", ret_num);
//		object.put("ret_message", "success");   	
//        return object;   	
//    } 
    
    
    /** 
     * 范围属性操作
     * @return 
     */  
    @RequestMapping(value="/areaParamAndSet",method=RequestMethod.POST)  
    @ResponseBody  
    public Object areaParamAndSet(HttpServletRequest request, AreaParam areaParam,AreaSetModel areaSetModel){ 
    	JSONObject object  = new JSONObject();   	    	
    	try{
        	List<AreaParam> params = layoutAreaDao.getAreaParams(areaParam);
        	if(areaParam.getArea_paramid()==0){
	        	if(params!=null && params.size()>0){
	    			ret_message = "该名称或编码的属性已存在";
	    			object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message+"名称或编码重复");
	                return object;
	    		}
	        	areaParam.setIs_operate(1);
	        	List<AreaParam> areaParams= layoutAreaDao.getAreaParamsByArea(areaParam.getFather_area());
	        	if(areaParams!=null && areaParams.size()>0){
	        		areaParam.setOrder_num(areaParams.size()+1);	
	        	}else{
	        		areaParam.setOrder_num(1);
	        	}
	        	layoutAreaDao.addAreaParam(areaParam);
        	}else{
	        	if(params!=null && params.size()>0){
	        		AreaParam existParam = params.get(0);
	        		if(existParam.getArea_paramid() != areaParam.getArea_paramid()){
		    			ret_message = "该名称或编码的属性已存在";
		    			object.put("ret_num", ret_error);
		        		object.put("ret_message", ret_message+"名称或编码重复");
		                return object;
	        		}
	    		}
	        	layoutAreaDao.updateAreaParam(areaParam);
        	}
        
        	List<AreaParamSet> addParams = areaSetModel.getAddParams();
    		if(addParams!=null && addParams.size()>0){
				for(int i=0;i<addParams.size();i++){ 
					AreaParamSet areaParamSet= addParams.get(i);
					areaParamSet.setArea_paramid(areaParam.getArea_paramid());		        	
		        	layoutAreaDao.addAreaParamSet(areaParamSet);
				}
			}
    		List<AreaParamSet> editParams = areaSetModel.getEditParams();
    		if(editParams!=null && editParams.size()>0){
				for(int i=0;i<editParams.size();i++){ 
					AreaParamSet areaParamSet= editParams.get(i);		        	
					layoutAreaDao.updateAreaParamSet(areaParamSet);
				}
			}
    		List<AreaParamSet> deleteParams = areaSetModel.getDeleteParams();
    		if(deleteParams!=null && deleteParams.size()>0){
				for(int i=0;i<deleteParams.size();i++){ 
					AreaParamSet areaParamSet= deleteParams.get(i);		        	
					layoutAreaDao.deleteAreaParamSetById(areaParamSet.getId());  
				}
			}
        	object.put("area_paramid", areaParam.getArea_paramid());
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
     * 修改范围线属性
     * @return 
     */  
    @RequestMapping(value="/updateAreaParam",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateAreaParam(HttpServletRequest request, AreaParam areaParam){ 
    	JSONObject object  = new JSONObject();
   	
    	try{
        	List<AreaParam> params = layoutAreaDao.getAreaParams(areaParam);
        	if(params!=null && params.size()>0){
        		AreaParam existParam = params.get(0);
        		if(existParam.getArea_paramid() != areaParam.getArea_paramid()){
	    			ret_message = "该名称或编码的属性已存在";
	    			object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message+"名称或编码重复");
	                return object;
        		}
    		}
        	layoutAreaDao.updateAreaParam(areaParam);
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
     * 范围线属性列表
     * @return 
     */  
    @RequestMapping(value="/getAreaParams",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getAreaParams(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int father_area = Integer.parseInt(request.getParameter("father_area"));	    		
    	try{
    		List<AreaParam> areaParams= layoutAreaDao.getAreaParamsByArea(father_area);
    		JSONArray JSONArray = new JSONArray();
    		for(AreaParam areaParam:areaParams){
    			JSONObject obj  = new JSONObject();
    			obj.put("area_paramid", areaParam.getArea_paramid());
    			obj.put("father_area", areaParam.getFather_area());
    			obj.put("param_name", areaParam.getParam_name());
    			obj.put("param_code", areaParam.getParam_code());
    			obj.put("param_type", areaParam.getParam_type());
    			obj.put("is_input", areaParam.getIs_input());
    			obj.put("is_select", areaParam.getIs_select());
    			obj.put("is_must", areaParam.getIs_must());
    			obj.put("is_light", areaParam.getIs_light());
    			obj.put("is_memory", areaParam.getIs_memory());
    			obj.put("remark", areaParam.getRemark());
    			obj.put("param_length", areaParam.getParam_length());
    			obj.put("father_param", areaParam.getFather_param());
    			if(areaParam.getFather_param()!=0){
    				AreaParam param = layoutAreaDao.getAreaParamByParamId(areaParam.getFather_param());
	    			obj.put("father_param_name", param.getParam_name());
    			}else{
    				obj.put("father_param_name", "");
    			}
    			obj.put("is_operate", areaParam.getIs_operate());
    			obj.put("order_num", areaParam.getOrder_num());
    			JSONArray.add(obj);
    		}
    		
    		object.put("data", JSONArray);
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
     * 新增范围线属性配置
     * @return 
     */  
    @RequestMapping(value="/addAreaParamSet",method=RequestMethod.POST)  
    @ResponseBody  
    public Object addAreaParamSet(HttpServletRequest request, AreaParamSet areaParamSet){ 
    	JSONObject object  = new JSONObject();	    	
    	try{
    		layoutAreaDao.addAreaParamSet(areaParamSet);	
        	object.put("id", areaParamSet.getId());
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "添加失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");   	
        return object;  
    } 
    
    /** 
     * 修改范围线属性配置
     * @return 
     */  
    @RequestMapping(value="/updateAreaParamSet",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateAreaParamSet(HttpServletRequest request, AreaParamSet areaParamSet){ 
    	JSONObject object  = new JSONObject();	    	
    	try{
    		layoutAreaDao.updateAreaParamSet(areaParamSet);	
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
     * 范围线属性配置列表
     * @return 
     */  
    @RequestMapping(value="/getAreaParamSets",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getAreaParamSets(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int area_paramid = Integer.parseInt(request.getParameter("area_paramid"));	    		
    	try{
    		List<AreaParamSet> areaParamSets= layoutAreaDao.getAreaParamSetsByParamId(area_paramid);
    		object.put("data", areaParamSets);
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
     * 复制范围线属性
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/copyAreaParam",method=RequestMethod.POST)  
    @ResponseBody  
    public Object copyAreaParam(HttpServletRequest request,AreaParam areaParam){  	
    	JSONObject object  = new JSONObject();   	    	
    	try{
        	List<AreaParam> params = layoutAreaDao.getAreaParams(areaParam);
        	if(params!=null && params.size()>0){
    			ret_message = "该名称或编码的属性已存在";
    			object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message+"名称或编码重复");
                return object;
    		}
        	AreaParam param = layoutAreaDao.getAreaParamByParamId(areaParam.getArea_paramid());
        	param.setParam_name(areaParam.getParam_name());
        	param.setParam_code(areaParam.getParam_code());
        	param.setIs_operate(1);
        	List<AreaParam> areaParams= layoutAreaDao.getAreaParamsByArea(areaParam.getFather_area());
        	if(areaParams!=null && areaParams.size()>0){
        		param.setOrder_num(areaParams.size()+1);	
        	}else{
        		param.setOrder_num(1);
        	}
        	layoutAreaDao.addAreaParam(param);
        	if(param.getIs_select()==1){
        		List<AreaParamSet> areaParamSets = layoutAreaDao.getAreaParamSetsByParamId(areaParam.getArea_paramid());
        		for(AreaParamSet areaParamSet:areaParamSets){
        			areaParamSet.setArea_paramid(param.getArea_paramid());
        			layoutAreaDao.addAreaParamSet(areaParamSet);
        		}
       		
        	}       	
        	object.put("area_paramid", param.getArea_paramid());
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
     * 删除范围线属性配置
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deleteAreaParamSetById",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteAreaParamSetById(HttpServletRequest request){ 
    	int id = Integer.parseInt(request.getParameter("id"));    	
    	JSONObject object  = new JSONObject();   	    	
    	try{     	
    		layoutAreaDao.deleteAreaParamSetById(id);        	
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
     * 删除范围线属性
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deleteAreaParamByParamId",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteAreaParamByParamId(HttpServletRequest request){ 
    	int area_paramid = Integer.parseInt(request.getParameter("area_paramid"));    	
    	JSONObject object  = new JSONObject();   	    	
    	try{     	
    		AreaParam param = layoutAreaDao.getAreaParamByParamId(area_paramid);
    		layoutAreaDao.updateAreaParamOrderUp(param);
    		layoutAreaDao.deleteAreaParamByParamId(area_paramid);       
    		layoutAreaDao.deleteAreaParamSetByParamId(area_paramid);    
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
     * 属性顺序变化
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/changeAreaParamOrder",method=RequestMethod.POST)  
    @ResponseBody  
    public Object changeAreaParamOrder(HttpServletRequest request){ 
    	int area_paramid = Integer.parseInt(request.getParameter("area_paramid"));   
    	int order_num = Integer.parseInt(request.getParameter("order_num"));    	
    	JSONObject object  = new JSONObject();   	    	
    	try{     	
    		
    		AreaParam param = layoutAreaDao.getAreaParamByParamId(area_paramid);
    		int old_order = param.getOrder_num();
    		boolean isUp = true;
    		if(order_num>old_order){
    			isUp = false;
    		}
    		List<AreaParam> areaParams= layoutAreaDao.getAreaParamsByArea(param.getFather_area());
    		for(AreaParam areaParam:areaParams){
    			if(isUp){
    				if(areaParam.getOrder_num()>=order_num && areaParam.getOrder_num()<old_order){
    					areaParam.setOrder_num(areaParam.getOrder_num()+1);
    					layoutAreaDao.updateAreaParamOrder(areaParam);
    				}
    			}else{
    				if(areaParam.getOrder_num()>old_order && areaParam.getOrder_num()<=order_num){
    					areaParam.setOrder_num(areaParam.getOrder_num()-1);
    					layoutAreaDao.updateAreaParamOrder(areaParam);
    				}
    			}
    		}
    		param.setOrder_num(order_num);
    		layoutAreaDao.updateAreaParamOrder(param);    	   
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
    		ret_message = "变化失败";
    		object.put("ret_num", ret_error);
    		object.put("ret_message", ret_message);
            return object;
		}		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");   	
        return object;  
    }

}
