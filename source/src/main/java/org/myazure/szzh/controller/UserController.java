package org.myazure.szzh.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import org.myazure.repository.UserRepository;
import org.myazure.utils.Base64;
import org.myazure.entity.User;



  

@Controller  
@RequestMapping("/user")  
public class UserController {  
    @Resource  
    private UserRepository userDao; 
    private int ret_num = 0;
    private int ret_error = 1;
      
//    @RequestMapping("/showUser")  
//    public String showUser(HttpServletRequest request,Model model){  
//        int userId = Integer.parseInt(request.getParameter("id"));  
//        User user = this.userService.getUserById(userId);  
//        System.out.println(JSON.toJSONString(user));
//        model.addAttribute("user", user);  
//        return "showUser";  
//    }
//    
//    /** 
//     * 
//     * @param session 
//     * @return 
//     */  
//    @RequestMapping(value="/getUserById")  
//    @ResponseBody  
//    public Object test(HttpServletRequest request,int id){  
////    	int userId = Integer.parseInt(request.getParameter("id"));  
//        User user = this.userService.getUserById(id);  
//        return JSON.toJSONString(user);  
//    } 
//    
    /** 
     * 
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/users",method=RequestMethod.GET)  
    @ResponseBody  
    public Object getAllUser(HttpServletRequest request){  
    	JSONObject object  = new JSONObject();
        List<User> users = userDao.getAllUser();  
        object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        object.put("data", users);
        return object;          
        
    } 
    
    
    /** 
     * 注册
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/addUser",method=RequestMethod.POST)  
    @ResponseBody  
    public Object register(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	String name = String.valueOf(request.getParameter("name")); 
    	String password = String.valueOf(request.getParameter("password")); 
    	String imei = String.valueOf(request.getParameter("imei")); 
    	int group_id = Integer.parseInt(request.getParameter("group_id")); 
    	int is_admin = Integer.parseInt(request.getParameter("is_admin")); 
    	
//    	User user = userDao.getUserByName(phone); 
//    	if(user!=null){
//    		object.put("ret_num", ret_error);
//        	object.put("ret_message", "该手机号已注册");
//        	return object; 
//    	}
    	
		
		User userNew = new User();
		userNew.setName(name);
		userNew.setPassword(Base64.getBase64(password));
		userNew.setImei(imei);
		userNew.setCreated_at(new Date());
		userNew.setUpdated_at(new Date());
		userNew.setGroup_id(group_id);
		userNew.setIs_admin(is_admin);
		userDao.insertUser(userNew);
		    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    } 
      
    
    /** 
     * 修改用户
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/updateUser",method=RequestMethod.POST)  
    @ResponseBody  
    public Object updateUser(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	String name = String.valueOf(request.getParameter("name")); 
//    	String password = String.valueOf(request.getParameter("password")); 
    	String imei = String.valueOf(request.getParameter("imei")); 
    	int id = Integer.parseInt(request.getParameter("id")); 
    	int group_id = Integer.parseInt(request.getParameter("group_id"));  
    	int is_admin = Integer.parseInt(request.getParameter("is_admin")); 

    	
//    	Map<String,Object> map = new HashMap<String, Object>();
//    	map.put("name", name);
//    	map.put("password", password);
//    	map.put("imei", imei);
//    	map.put("id", id);
    	
    	User user = new User();
    	user.setId(id);    	
    	user.setName(name);
    	user.setImei(imei);
//		user.setPassword(Base64.getBase64(password));
		user.setUpdated_at(new Date());
		user.setGroup_id(group_id);
		user.setIs_admin(is_admin);
		userDao.updateUser(user);		    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");   	  	    	 
        return object;  
    } 
    
    /** 
     * 重置密码
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/resetPwd",method=RequestMethod.POST)  
    @ResponseBody  
    public Object resetPwd(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	String password = String.valueOf(request.getParameter("password")); 
    	int id = Integer.parseInt(request.getParameter("id")); 
 	
    	User user = new User();
    	user.setId(id);    	
		user.setPassword(Base64.getBase64(password));
		user.setUpdated_at(new Date());
		user.setIs_admin(-1);
		userDao.updateUser(user);		    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");   	  	    	 
        return object;  
    } 
    
    /** 
     * 删除User
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/deleteUser",method=RequestMethod.POST)  
    @ResponseBody  
    public Object deleteBeacon(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();  	
    	
    	int id = Integer.parseInt(request.getParameter("id")); 
    	userDao.deleteUserById(id);		    		
		object.put("ret_num", ret_num);
		object.put("ret_message", "success");
        return object;  
    } 
    
    
    /** 
     * 登录
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/login",method=RequestMethod.POST)  
    @ResponseBody  
    public Object login(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	String name = String.valueOf(request.getParameter("name")); 
    	String password = String.valueOf(request.getParameter("password"));
    	User user = userDao.getUserByName(name); 
    	if(user==null){
    		object.put("ret_num", ret_error);
        	object.put("ret_message", "该用户不存在");
    	}else if(user.getIs_admin()==0){
    		object.put("ret_num", ret_error);
        	object.put("ret_message", "该用户非管理员");
    	}else if(!Base64.getBase64(password).equals(user.getPassword())){
    		object.put("ret_num", ret_error);
        	object.put("ret_message", "用户密码不正确");
    	}else{    		    		   		   		   		    		
    		object.put("ret_num", ret_num);
    		object.put("ret_message", "success");
    		object.put("user", user);
    	}    	
    	
        return object;  
    }
    
    
    
    /** 
     * 登录
     * @param session 
     * @return 
     */  
    @RequestMapping(value="/newLogin",method=RequestMethod.POST)  
    @ResponseBody  
    public Object newLogin(HttpServletRequest request){ 
    	JSONObject object  = new JSONObject();
    	String name = String.valueOf(request.getParameter("name")); 
    	String password = String.valueOf(request.getParameter("password"));
    	String imei = String.valueOf(request.getParameter("imei"));
    	User user = userDao.getUserByName(name); 
    	if(user==null){
    		object.put("ret_num", ret_error);
        	object.put("ret_message", "该用户不存在");
    	}else if(!Base64.getBase64(password).equals(user.getPassword())){
    		object.put("ret_num", ret_error);
        	object.put("ret_message", "用户密码不正确");
    	}else if(imei.equals("") || !imei.equals(user.getImei())){
    		object.put("ret_num", ret_error);
        	object.put("ret_message", "信息验证不正确");
    	}else{    		    		   		   		   		    		
    		object.put("ret_num", ret_num);
    		object.put("ret_message", "success");
    		object.put("user", user);
    	}    	
    	
        return object;  
    }
    
    
}
