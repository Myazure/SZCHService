package com.milestone.mygeo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.milestone.mygeo.dao.ILayoutPointDao;
import com.milestone.mygeo.models.LayoutPoint;
import com.milestone.mygeo.models.PointParam;
import com.milestone.mygeo.models.PointParamSet;
import com.milestone.mygeo.models.PointSetModel;

@Controller  
@RequestMapping("/layout_point") 
public class LayoutPointController {
	@Resource  
    private ILayoutPointDao layoutPointDao; 
    private int ret_num = 0;
    private int ret_error = -1;
    private String ret_message="";
//    private static final String uploadFilePath = "d:\\temp_upload_file\\mgo_img";  
	
	
	  /** 
	  * 修改管点
	  */  
	 @RequestMapping(value="/updatePoint",method=RequestMethod.POST)  
	 @ResponseBody   
	 public Object updatePoint(HttpServletRequest request,int city_id,int point_id, String layout_code,MultipartFile point_pic) {  
		 JSONObject object  = new JSONObject();  
		 try {  
			 
			 if(point_pic!=null && point_pic.getSize()>0){
	         InputStream is = point_pic.getInputStream();  
	         // 如果服务器已经存在和上传文件同名的文件，则输出提示信息  
	         String filename = layout_code+city_id+"_point.png";
//	         String pathdir = "/image";// 构建图片保存的目录
	         String realpath = request.getServletContext().getRealPath("/upload") ;
			 // 创建文件目录
			 File savedir = new File(realpath);
			 // 如果目录不存在就创建
			 if (!savedir.exists()) {
				 savedir.mkdirs();
			 }
	         
			 filename = realpath+"/"+filename;
//	         System.out.println("文件：" + realpath+"/"+filename); 
//			 File tempFile = new File(filename); 
			 File tempFile = new File(filename);
			 if (tempFile.exists()) {  
			     boolean delResult = tempFile.delete();  
			     System.out.println("删除已存在的文件：" + delResult);  
			 }  
			 // 开始保存文件到服务器  
			 if (!filename.equals("")) {  
				 FileOutputStream fos = new FileOutputStream(filename);  
				 byte[] buffer = new byte[8192]; // 每次读8K字节  
				 int count = 0;  
				 // 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中  
				 while ((count = is.read(buffer)) > 0) {  
				     fos.write(buffer, 0, count); // 向服务端文件写入字节流  
				 }  
				 fos.close(); // 关闭FileOutputStream对象  
				 is.close(); // InputStream对象  
	         }  
			 LayoutPoint layoutPoint = new LayoutPoint();
			 layoutPoint.setPoint_id(point_id);
			 layoutPoint.setPoint_pic("upload/"+layout_code+city_id+"_point.png");
			 layoutPointDao.updateLayoutPoint(layoutPoint);
			 }
	     } catch (FileNotFoundException e) {  
	         e.printStackTrace();  
	     } catch (IOException e) {  
	         e.printStackTrace();  
	     }  
		 
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");   	
        return object;  
	 }
	 
	 
	 /** 
     * 根据图层查询管点信息
     * @return 
     */  
    @RequestMapping(value="/pointDetail",method=RequestMethod.GET)  
    @ResponseBody  
    public Object pointDetail(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int father_layout = Integer.parseInt(request.getParameter("father_layout"));	    		
    	try{
    		LayoutPoint layoutPoint= layoutPointDao.selectPointByLayout(father_layout);
    		object.put("data", layoutPoint);
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
//     * 新增管点属性
//     * @return 
//     */  
//    @RequestMapping(value="/addPointParam",method=RequestMethod.POST)  
//    @ResponseBody  
//    public Object addPointParam(HttpServletRequest request, PointParam pointParam){ 
//    	JSONObject object  = new JSONObject();
//    	    	
//    	try{
//        	List<PointParam> params = layoutPointDao.getPointParams(pointParam);
//        	if(params!=null && params.size()>0){
//    			ret_message = "该名称或编码的属性已存在";
//    			object.put("ret_num", ret_error);
//        		object.put("ret_message", ret_message+"名称或编码重复");
//                return object;
//    		}
//        	pointParam.setIs_operate(1);
//        	layoutPointDao.addPointParam(pointParam);	
//        	object.put("point_paramid", pointParam.getPoint_paramid());
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
     * 管点属性操作
     * @return 
     */  
    @RequestMapping(value="/pointParamAndSet",method=RequestMethod.POST)  
    @ResponseBody  
    public Object pointParamAndSet(HttpServletRequest request, PointParam pointParam,PointSetModel pointSetModel){ 
    	JSONObject object  = new JSONObject();   	    	
    	try{
        	List<PointParam> params = layoutPointDao.getPointParams(pointParam);
        	if(pointParam.getPoint_paramid()==0){
	        	if(params!=null && params.size()>0){
	    			ret_message = "该名称或编码的属性已存在";
	    			object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message+"名称或编码重复");
	                return object;
	    		}
	        	pointParam.setIs_operate(1);
	        	List<PointParam> pointParams= layoutPointDao.getPointParamsByPoint(pointParam.getFather_point());
	        	if(pointParams!=null && pointParams.size()>0){
	        		pointParam.setOrder_num(pointParams.size()+1);	
	        	}else{
	        		pointParam.setOrder_num(1);
	        	}	        	
	        	layoutPointDao.addPointParam(pointParam);
        	}else{
	        	if(params!=null && params.size()>0){
	        		PointParam existParam = params.get(0);
	        		if(existParam.getPoint_paramid() != pointParam.getPoint_paramid()){
		    			ret_message = "该名称或编码的属性已存在";
		    			object.put("ret_num", ret_error);
		        		object.put("ret_message", ret_message+"名称或编码重复");
		                return object;
	        		}
	    		}
	        	layoutPointDao.updatePointParam(pointParam);
        	}
        
        	List<PointParamSet> addParams = pointSetModel.getAddParams();
    		if(addParams!=null && addParams.size()>0){
				for(int i=0;i<addParams.size();i++){ 
					PointParamSet pointParamSet= addParams.get(i);
					pointParamSet.setPoint_paramid(pointParam.getPoint_paramid());
		        	if(pointParamSet.getPic()!=null && !pointParamSet.getPic().equals("")){
			    		InputStream is = pointParamSet.getPic().getInputStream();  
				        // 如果服务器已经存在和上传文件同名的文件，则输出提示信息  
				        String filename = pointParamSet.getValue()+pointParamSet.getPoint_paramid()+"_point.png";
				        String realpath = request.getServletContext().getRealPath("/upload") ;
						// 创建文件目录
						File savedir = new File(realpath);
						// 如果目录不存在就创建
						if (!savedir.exists()) {
							savedir.mkdirs();
						}
						filename = realpath+"/"+filename;
						File tempFile = new File(filename);
						if (tempFile.exists()) {  
						    boolean delResult = tempFile.delete();  
						    System.out.println("删除已存在的文件：" + delResult);  
						}  
						// 开始保存文件到服务器  
						if (!filename.equals("")) {  
							FileOutputStream fos = new FileOutputStream(filename);  
							byte[] buffer = new byte[8192]; // 每次读8K字节  
							int count = 0;  
							// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中  
							while ((count = is.read(buffer)) > 0) {  
							    fos.write(buffer, 0, count); // 向服务端文件写入字节流  
							}  
							fos.close(); // 关闭FileOutputStream对象  
							is.close(); // InputStream对象  
				        }      		
						pointParamSet.setPic_path("upload/"+pointParamSet.getValue()+pointParamSet.getPoint_paramid()+"_point.png");	    		
		    		}
		        	layoutPointDao.addPointParamSet(pointParamSet);
				}
			}
    		List<PointParamSet> editParams = pointSetModel.getEditParams();
    		if(editParams!=null && editParams.size()>0){
				for(int i=0;i<editParams.size();i++){ 
					PointParamSet pointParamSet= editParams.get(i);
		        	if(pointParamSet.getPic()!=null && !pointParamSet.getPic().equals("")){
			    		InputStream is = pointParamSet.getPic().getInputStream();  
				        // 如果服务器已经存在和上传文件同名的文件，则输出提示信息  
				        String filename = pointParamSet.getValue()+pointParamSet.getPoint_paramid()+"_point.png";
				        String realpath = request.getServletContext().getRealPath("/upload") ;
						// 创建文件目录
						File savedir = new File(realpath);
						// 如果目录不存在就创建
						if (!savedir.exists()) {
							savedir.mkdirs();
						}
						filename = realpath+"/"+filename;
						File tempFile = new File(filename);
						if (tempFile.exists()) {  
						    boolean delResult = tempFile.delete();  
						    System.out.println("删除已存在的文件：" + delResult);  
						}  
						// 开始保存文件到服务器  
						if (!filename.equals("")) {  
							FileOutputStream fos = new FileOutputStream(filename);  
							byte[] buffer = new byte[8192]; // 每次读8K字节  
							int count = 0;  
							// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中  
							while ((count = is.read(buffer)) > 0) {  
							    fos.write(buffer, 0, count); // 向服务端文件写入字节流  
							}  
							fos.close(); // 关闭FileOutputStream对象  
							is.close(); // InputStream对象  
				        }      		
						pointParamSet.setPic_path("upload/"+pointParamSet.getValue()+pointParamSet.getPoint_paramid()+"_point.png");	    		
		    		}
		        	layoutPointDao.updatePointParamSet(pointParamSet);
				}
			}
    		List<PointParamSet> deleteParams = pointSetModel.getDeleteParams();
    		if(deleteParams!=null && deleteParams.size()>0){
				for(int i=0;i<deleteParams.size();i++){ 
					PointParamSet pointParamSet= deleteParams.get(i);		        	
					layoutPointDao.deletePointParamSetById(pointParamSet.getId());  
				}
			}
        	object.put("point_paramid", pointParam.getPoint_paramid());
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
    @RequestMapping(value="/updatePointParam",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updatePointParam(HttpServletRequest request, PointParam pointParam){ 
    	JSONObject object  = new JSONObject();
	
    	
    	try{
        	List<PointParam> params = layoutPointDao.getPointParams(pointParam);
        	if(params!=null && params.size()>0){
        		PointParam existParam = params.get(0);
        		if(existParam.getPoint_paramid() != pointParam.getPoint_paramid()){
	    			ret_message = "该名称或编码的属性已存在";
	    			object.put("ret_num", ret_error);
	        		object.put("ret_message", ret_message+"名称或编码重复");
	                return object;
        		}
    		}
        	layoutPointDao.updatePointParam(pointParam);	
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
    @RequestMapping(value="/getPointParams",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getPointParams(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int father_point = Integer.parseInt(request.getParameter("father_point"));	    		
    	try{
    		List<PointParam> pointParams= layoutPointDao.getPointParamsByPoint(father_point);
    		JSONArray JSONArray = new JSONArray();
    		for(PointParam pointParam:pointParams){
    			JSONObject obj  = new JSONObject();
    			obj.put("point_paramid", pointParam.getPoint_paramid());
    			obj.put("father_point", pointParam.getFather_point());
    			obj.put("param_name", pointParam.getParam_name());
    			obj.put("param_code", pointParam.getParam_code());
    			obj.put("param_type", pointParam.getParam_type());
    			obj.put("is_input", pointParam.getIs_input());
    			obj.put("is_select", pointParam.getIs_select());
    			obj.put("is_must", pointParam.getIs_must());
    			obj.put("is_light", pointParam.getIs_light());
    			obj.put("is_memory", pointParam.getIs_memory());
    			obj.put("remark", pointParam.getRemark());
    			obj.put("param_length", pointParam.getParam_length());
    			obj.put("father_param", pointParam.getFather_param());
    			if(pointParam.getFather_param()!=0){
	    			PointParam param = layoutPointDao.getPointParamByParamId(pointParam.getFather_param());
	    			obj.put("father_param_name", param.getParam_name());
    			}else{
    				obj.put("father_param_name", "");
    			}
    			obj.put("is_operate", pointParam.getIs_operate());
    			obj.put("order_num", pointParam.getOrder_num());
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
     * 新增管点属性配置
     * @return 
     */  
    @RequestMapping(value="/addPointParamSet",method=RequestMethod.POST)  
    @ResponseBody  
    public Object addPointParamSet(HttpServletRequest request, PointParamSet pointParamSet,MultipartFile point_pic){ 
    	JSONObject object  = new JSONObject();	  
    	try{    
    		System.out.println("point_pic========="+point_pic);
    		if(point_pic!=null && !point_pic.equals("")){
	    		InputStream is = point_pic.getInputStream();  
		        // 如果服务器已经存在和上传文件同名的文件，则输出提示信息  
		        String filename = pointParamSet.getValue()+pointParamSet.getPoint_paramid()+"_point.png";
		        String realpath = request.getServletContext().getRealPath("/upload") ;
				// 创建文件目录
				File savedir = new File(realpath);
				// 如果目录不存在就创建
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				filename = realpath+"/"+filename;
				File tempFile = new File(filename);
				if (tempFile.exists()) {  
				    boolean delResult = tempFile.delete();  
				    System.out.println("删除已存在的文件：" + delResult);  
				}  
				// 开始保存文件到服务器  
				if (!filename.equals("")) {  
					FileOutputStream fos = new FileOutputStream(filename);  
					byte[] buffer = new byte[8192]; // 每次读8K字节  
					int count = 0;  
					// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中  
					while ((count = is.read(buffer)) > 0) {  
					    fos.write(buffer, 0, count); // 向服务端文件写入字节流  
					}  
					fos.close(); // 关闭FileOutputStream对象  
					is.close(); // InputStream对象  
		        }      		
				pointParamSet.setPic_path("upload/"+pointParamSet.getValue()+pointParamSet.getPoint_paramid()+"_point.png");	    		
    		}
        	layoutPointDao.addPointParamSet(pointParamSet);	
        	object.put("id", pointParamSet.getId());
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
     * 修改管点属性配置
     * @return 
     */  
    @RequestMapping(value="/updatePointParamSet",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updatePointParamSet(HttpServletRequest request, PointParamSet pointParamSet,MultipartFile point_pic){ 
    	JSONObject object  = new JSONObject();	    	
    	try{
    		if(point_pic!=null){
	    		InputStream is = point_pic.getInputStream();  
		        // 如果服务器已经存在和上传文件同名的文件，则输出提示信息  
		        String filename = pointParamSet.getValue()+pointParamSet.getPoint_paramid()+"_point.png";
		        String realpath = request.getServletContext().getRealPath("/upload") ;
				// 创建文件目录
				File savedir = new File(realpath);
				// 如果目录不存在就创建
				if (!savedir.exists()) {
					savedir.mkdirs();
				}
				filename = realpath+"/"+filename;
				File tempFile = new File(filename);
				if (tempFile.exists()) {  
				    boolean delResult = tempFile.delete();  
				    System.out.println("删除已存在的文件：" + delResult);  
				}  
				// 开始保存文件到服务器  
				if (!filename.equals("")) {  
					FileOutputStream fos = new FileOutputStream(filename);  
					byte[] buffer = new byte[8192]; // 每次读8K字节  
					int count = 0;  
					// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中  
					while ((count = is.read(buffer)) > 0) {  
					    fos.write(buffer, 0, count); // 向服务端文件写入字节流  
					}  
					fos.close(); // 关闭FileOutputStream对象  
					is.close(); // InputStream对象  
		        }      		
				pointParamSet.setPic_path("upload/"+pointParamSet.getValue()+pointParamSet.getPoint_paramid()+"_point.png");	    		
    		}
        	layoutPointDao.updatePointParamSet(pointParamSet);	
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
     * 管点属性配置列表
     * @return 
     */  
    @RequestMapping(value="/getPointParamSets",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getPointParamSets(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	int point_paramid = Integer.parseInt(request.getParameter("point_paramid"));	    		
    	try{
    		List<PointParamSet> pointParamSets= layoutPointDao.getPointParamSetsByParamId(point_paramid);
    		object.put("data", pointParamSets);
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
     * 复制管点属性
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/copyPointParam",method=RequestMethod.POST)  
    @ResponseBody  
    public Object copyPointParam(HttpServletRequest request,PointParam pointParam){ 
//    	int point_paramid = Integer.parseInt(request.getParameter("point_paramid")); 
//    	String param_name = String.valueOf(request.getParameter("param_name")).trim();
//    	String param_code = String.valueOf(request.getParameter("param_code")).trim();   	
    	JSONObject object  = new JSONObject();   	    	
    	try{
        	List<PointParam> params = layoutPointDao.getPointParams(pointParam);
        	if(params!=null && params.size()>0){
    			ret_message = "该名称或编码的属性已存在";
    			object.put("ret_num", ret_error);
        		object.put("ret_message", ret_message+"名称或编码重复");
                return object;
    		}
        	PointParam param = layoutPointDao.getPointParamByParamId(pointParam.getPoint_paramid());
        	param.setParam_name(pointParam.getParam_name());
        	param.setParam_code(pointParam.getParam_code());
        	param.setIs_operate(1);
        	List<PointParam> pointParams= layoutPointDao.getPointParamsByPoint(pointParam.getFather_point());
        	if(pointParams!=null && pointParams.size()>0){
        		param.setOrder_num(pointParams.size()+1);
        	}else{
        		param.setOrder_num(1);
        	}	
        	layoutPointDao.addPointParam(param);
        	if(param.getIs_select()==1){
        		List<PointParamSet> pointParamSets = layoutPointDao.getPointParamSetsByParamId(pointParam.getPoint_paramid());
        		for(PointParamSet pointParamSet:pointParamSets){
        			pointParamSet.setPoint_paramid(param.getPoint_paramid());
//        			pointParamSet.setPic_path("");
        			layoutPointDao.addPointParamSet(pointParamSet);
        		}
       		
        	}       	
        	object.put("point_paramid", param.getPoint_paramid());
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
     * 删除管点属性配置
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deletePointParamSetById",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deletePointParamSetById(HttpServletRequest request){ 
    	int id = Integer.parseInt(request.getParameter("id"));    	
    	JSONObject object  = new JSONObject();   	    	
    	try{     	
        	layoutPointDao.deletePointParamSetById(id);        	
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
     * 删除管点属性
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/deletePointParamByParamId",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deletePointParamByParamId(HttpServletRequest request){ 
    	int point_paramid = Integer.parseInt(request.getParameter("point_paramid"));    	
    	JSONObject object  = new JSONObject();   	    	
    	try{     	
    		
    		PointParam param = layoutPointDao.getPointParamByParamId(point_paramid);
    		layoutPointDao.updatePointParamOrderUp(param);
        	layoutPointDao.deletePointParamByParamId(point_paramid);       
        	layoutPointDao.deletePointParamSetByParamId(point_paramid);    
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
     * 管点属性顺序变化
     * @return 
     */  
    @Transactional
    @RequestMapping(value="/changePointParamOrder",method=RequestMethod.POST)  
    @ResponseBody  
    public Object changePointParamOrder(HttpServletRequest request){ 
    	int point_paramid = Integer.parseInt(request.getParameter("point_paramid"));   
    	int order_num = Integer.parseInt(request.getParameter("order_num"));    	
    	JSONObject object  = new JSONObject();   	    	
    	try{     	
    		
    		PointParam param = layoutPointDao.getPointParamByParamId(point_paramid);
    		int old_order = param.getOrder_num();
    		boolean isUp = true;
    		if(order_num>old_order){
    			isUp = false;
    		}
    		List<PointParam> pointParams= layoutPointDao.getPointParamsByPoint(param.getFather_point());
    		for(PointParam pointParam:pointParams){
    			if(isUp){
    				if(pointParam.getOrder_num()>=order_num && pointParam.getOrder_num()<old_order){
    					pointParam.setOrder_num(pointParam.getOrder_num()+1);
    					layoutPointDao.updatePointParamOrder(pointParam);
    				}
    			}else{
    				if(pointParam.getOrder_num()>old_order && pointParam.getOrder_num()<=order_num){
    					pointParam.setOrder_num(pointParam.getOrder_num()-1);
    					layoutPointDao.updatePointParamOrder(pointParam);
    				}
    			}
    		}
    		param.setOrder_num(order_num);
    		layoutPointDao.updatePointParamOrder(param);    	   
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
