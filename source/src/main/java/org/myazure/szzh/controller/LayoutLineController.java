package org.myazure.szzh.controller;

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

import org.myazure.repository.LayoutLineRepository;
import org.myazure.entity.LayoutLine;
import org.myazure.entity.LineParam;
import org.myazure.entity.LineParamSet;
import org.myazure.entity.LineSetModel;


@Controller  
@RequestMapping("/layout_line") 
public class LayoutLineController {
	@Resource  
    private LayoutLineRepository layoutLineDao; 
    private int ret_num = 0;
    private int ret_error = -1;
    private String ret_message="";
	
	
    /** 
     * 修改管线
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/updateLine",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateLine(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int line_id = Integer.parseInt(request.getParameter("line_id")); 
    	String line_color = String.valueOf(request.getParameter("line_color")).trim(); 
    	  	    		
    	try{	 		
    		LayoutLine layoutLine = new LayoutLine();
    		layoutLine.setLine_id(line_id);
    		layoutLine.setLine_color(line_color);
    		layoutLineDao.updateLayoutLine(layoutLine);    		
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
     * 根据图层查询管线信息
     * @return 
     */  
    @RequestMapping(value="/lineDetail",method=RequestMethod.GET)  
    @ResponseBody  
    public Object lineDetail(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int father_layout = Integer.parseInt(request.getParameter("father_layout"));	    		
    	try{
    		LayoutLine layoutLine= layoutLineDao.selectLineByLayout(father_layout);
    		object.put("data", layoutLine);
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
//     * 新增管线属性
//     * @return 
//     */  
//    @RequestMapping(value="/addLineParam",method=RequestMethod.POST)  
//    @ResponseBody  
//    public Object addLineParam(HttpServletRequest request,LineParam lineParam){ 
//    	JSONObject object  = new JSONObject();
// 	   	
//    	try{
//        	List<LineParam> params = layoutLineDao.getLineParams(lineParam);
//        	if(params!=null && params.size()>0){
//    			ret_message = "该名称或编码的属性已存在";
//    			object.put("ret_num", ret_error);
//        		object.put("ret_message", ret_message+"名称或编码重复");
//                return object;
//    		}
//        	lineParam.setIs_operate(1);
//        	layoutLineDao.addLineParam(lineParam);	
//        	object.put("line_paramid", lineParam.getLine_paramid());
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
//    
    
    /** 
     * 管线属性操作
     * @return 
     */  
    @RequestMapping(value="/lineParamAndSet",method=RequestMethod.POST)  
    @ResponseBody  
    public Object lineParamAndSet(HttpServletRequest request, LineParam lineParam,LineSetModel lineSetModel){ 
    	JSONObject object  = new JSONObject();   	    	
    	try{
        	List<LineParam> params = layoutLineDao.getLineParams(lineParam);
        	if(lineParam.getLine_paramid()==0){
	        	if(params!=null && params.size()>0){
	    			ret_message = "该名称或编码的属性已存在";
	    			object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message+"名称或编码重复");
	                return object;
	    		}
	        	lineParam.setIs_operate(1);
	        	List<LineParam> lineParams= layoutLineDao.getLineParamsByLine(lineParam.getFather_line());
	        	if(lineParams!=null && lineParams.size()>0){
	        		lineParam.setOrder_num(lineParams.size()+1);	
	        	}else{
	        		lineParam.setOrder_num(1);
	        	}
	        	layoutLineDao.addLineParam(lineParam);
        	}else{
	        	if(params!=null && params.size()>0){
	        		LineParam existParam = params.get(0);
	        		if(existParam.getLine_paramid() != lineParam.getLine_paramid()){
		    			ret_message = "该名称或编码的属性已存在";
		    			object.put("ret_num", ret_error);
		        		object.put("ret_message", ret_message+"名称或编码重复");
		                return object;
	        		}
	    		}
	        	layoutLineDao.updateLineParam(lineParam);
        	}
        
        	List<LineParamSet> addParams = lineSetModel.getAddParams();
    		if(addParams!=null && addParams.size()>0){
				for(int i=0;i<addParams.size();i++){ 
					LineParamSet lineParamSet= addParams.get(i);
					lineParamSet.setLine_paramid(lineParam.getLine_paramid());		        	
		        	layoutLineDao.addLineParamSet(lineParamSet);
				}
			}
    		List<LineParamSet> editParams = lineSetModel.getEditParams();
    		if(editParams!=null && editParams.size()>0){
				for(int i=0;i<editParams.size();i++){ 
					LineParamSet lineParamSet= editParams.get(i);		        	
		        	layoutLineDao.updateLineParamSet(lineParamSet);
				}
			}
    		List<LineParamSet> deleteParams = lineSetModel.getDeleteParams();
    		if(deleteParams!=null && deleteParams.size()>0){
				for(int i=0;i<deleteParams.size();i++){ 
					LineParamSet lineParamSet= deleteParams.get(i);		        	
					layoutLineDao.deleteLineParamSetById(lineParamSet.getId());  
				}
			}
        	object.put("line_paramid", lineParam.getLine_paramid());
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
     * 修改管点属性
     * @return 
     */  
    @RequestMapping(value="/updateLineParam",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateLineParam(HttpServletRequest request, LineParam lineParam){ 
    	JSONObject object  = new JSONObject();
   	
    	try{
        	List<LineParam> params = layoutLineDao.getLineParams(lineParam);
        	if(params!=null && params.size()>0){
        		LineParam existParam = params.get(0);
        		if(existParam.getLine_paramid() != lineParam.getLine_paramid()){
	    			ret_message = "该名称或编码的属性已存在";
	    			object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message+"名称或编码重复");
	                return object;
        		}
    		}
        	layoutLineDao.updateLineParam(lineParam);
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
     * 管点属性列表
     * @return 
     */  
    @RequestMapping(value="/getLineParams",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getLineParams(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int father_line = Integer.parseInt(request.getParameter("father_line"));	    		
    	try{
    		List<LineParam> lineParams= layoutLineDao.getLineParamsByLine(father_line);
    		JSONArray JSONArray = new JSONArray();
    		for(LineParam lineParam:lineParams){
    			JSONObject obj  = new JSONObject();
    			obj.put("line_paramid", lineParam.getLine_paramid());
    			obj.put("father_line", lineParam.getFather_line());
    			obj.put("param_name", lineParam.getParam_name());
    			obj.put("param_code", lineParam.getParam_code());
    			obj.put("param_type", lineParam.getParam_type());
    			obj.put("is_input", lineParam.getIs_input());
    			obj.put("is_select", lineParam.getIs_select());
    			obj.put("is_must", lineParam.getIs_must());
    			obj.put("is_light", lineParam.getIs_light());
    			obj.put("is_memory", lineParam.getIs_memory());
    			obj.put("remark", lineParam.getRemark());
    			obj.put("param_length", lineParam.getParam_length());
    			obj.put("father_param", lineParam.getFather_param());
    			if(lineParam.getFather_param()!=0){
    				LineParam param = layoutLineDao.getLineParamByParamId(lineParam.getFather_param());
	    			obj.put("father_param_name", param.getParam_name());
    			}else{
    				obj.put("father_param_name", "");
    			}
    			obj.put("is_operate", lineParam.getIs_operate());
    			obj.put("order_num", lineParam.getOrder_num());
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
     * 新增管线属性配置
     * @return 
     */  
    @RequestMapping(value="/addLineParamSet",method=RequestMethod.POST)  
    @ResponseBody  
    public Object addLineParamSet(HttpServletRequest request, LineParamSet lineParamSet){ 
    	JSONObject object  = new JSONObject();	    	
    	try{
    		layoutLineDao.addLineParamSet(lineParamSet);	
        	object.put("id", lineParamSet.getId());
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
     * 修改管线属性配置
     * @return 
     */  
    @RequestMapping(value="/updateLineParamSet",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateLineParamSet(HttpServletRequest request, LineParamSet lineParamSet){ 
    	JSONObject object  = new JSONObject();	    	
    	try{
    		layoutLineDao.updateLineParamSet(lineParamSet);	
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
     * 管线属性配置列表
     * @return 
     */  
    @RequestMapping(value="/getLineParamSets",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getLineParamSets(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int line_paramid = Integer.parseInt(request.getParameter("line_paramid"));	    		
    	try{
    		List<LineParamSet> lineParamSets= layoutLineDao.getLineParamSetsByParamId(line_paramid);
    		object.put("data", lineParamSets);
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
     * 复制管线属性
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/copyLineParam",method=RequestMethod.POST)  
    @ResponseBody  
    public Object copyLineParam(HttpServletRequest request,LineParam lineParam){  	
    	JSONObject object  = new JSONObject();   	    	
    	try{
        	List<LineParam> params = layoutLineDao.getLineParams(lineParam);
        	if(params!=null && params.size()>0){
    			ret_message = "该名称或编码的属性已存在";
    			object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message+"名称或编码重复");
                return object;
    		}
        	LineParam param = layoutLineDao.getLineParamByParamId(lineParam.getLine_paramid());
        	param.setParam_name(lineParam.getParam_name());
        	param.setParam_code(lineParam.getParam_code());
        	param.setIs_operate(1);
        	List<LineParam> lineParams= layoutLineDao.getLineParamsByLine(lineParam.getFather_line());
        	if(lineParams!=null && lineParams.size()>0){
        		param.setOrder_num(lineParams.size()+1);	
        	}else{
        		param.setOrder_num(1);
        	}
        	layoutLineDao.addLineParam(param);
        	if(param.getIs_select()==1){
        		List<LineParamSet> lineParamSets = layoutLineDao.getLineParamSetsByParamId(lineParam.getLine_paramid());
        		for(LineParamSet lineParamSet:lineParamSets){
        			lineParamSet.setLine_paramid(param.getLine_paramid());
        			layoutLineDao.addLineParamSet(lineParamSet);
        		}
       		
        	}       	
        	object.put("line_paramid", param.getLine_paramid());
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
     * 删除管线属性配置
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deleteLineParamSetById",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteLineParamSetById(HttpServletRequest request){ 
    	int id = Integer.parseInt(request.getParameter("id"));    	
    	JSONObject object  = new JSONObject();   	    	
    	try{     	
    		layoutLineDao.deleteLineParamSetById(id);        	
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
     * 删除管线属性
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deleteLineParamByParamId",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteLineParamByParamId(HttpServletRequest request){ 
    	int line_paramid = Integer.parseInt(request.getParameter("line_paramid"));    	
    	JSONObject object  = new JSONObject();   	    	
    	try{     	
    		LineParam param = layoutLineDao.getLineParamByParamId(line_paramid);
    		layoutLineDao.updateLineParamOrderUp(param);
    		layoutLineDao.deleteLineParamByParamId(line_paramid);       
    		layoutLineDao.deleteLineParamSetByParamId(line_paramid);    
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
     * 管线属性顺序变化
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/changeLineParamOrder",method=RequestMethod.POST)  
    @ResponseBody  
    public Object changeLineParamOrder(HttpServletRequest request){ 
    	int line_paramid = Integer.parseInt(request.getParameter("line_paramid"));   
    	int order_num = Integer.parseInt(request.getParameter("order_num"));    	
    	JSONObject object  = new JSONObject();   	    	
    	try{     	
    		
    		LineParam param = layoutLineDao.getLineParamByParamId(line_paramid);
    		int old_order = param.getOrder_num();
    		boolean isUp = true;
    		if(order_num>old_order){
    			isUp = false;
    		}
    		List<LineParam> lineParams= layoutLineDao.getLineParamsByLine(param.getFather_line());
    		for(LineParam lineParam:lineParams){
    			if(isUp){
    				if(lineParam.getOrder_num()>=order_num && lineParam.getOrder_num()<old_order){
    					lineParam.setOrder_num(lineParam.getOrder_num()+1);
    					layoutLineDao.updateLineParamOrder(lineParam);
    				}
    			}else{
    				if(lineParam.getOrder_num()>old_order && lineParam.getOrder_num()<=order_num){
    					lineParam.setOrder_num(lineParam.getOrder_num()-1);
    					layoutLineDao.updateLineParamOrder(lineParam);
    				}
    			}
    		}
    		param.setOrder_num(order_num);
    		layoutLineDao.updateLineParamOrder(param);    	   
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
