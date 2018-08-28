package org.myazure.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myazure.service.InfoDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController extends BaseController {

	public WebController() {

	}

	@Autowired
	InfoDataService infoDataService;

	private static final Logger LOG = LoggerFactory
			.getLogger(WebController.class);


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(path = "/test", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void test(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		System.out.println("---------获取请求数据方式1-------------");
		// 获取指定的请求数据
		String value = request.getParameter("username");
		if (value != null && !value.trim().equals("")) {
			System.out.println(value);
		}

		System.out.println("---------获取请求数据方式2-------------");
		// 获取所有的请求数据
		Enumeration<String> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String paramName = (String) e.nextElement();
			String value2 = request.getParameter(paramName);
			System.out.println(paramName + "=" + value2);
		}

		System.out.println("---------获取请求数据方式3-------------");
		// 获取所有的请求数据，同名的只能获取一次，就是第一次
		String[] values = request.getParameterValues("username");
		for (int i = 0; values != null && i < values.length; i++) {
			System.out.println(values[i]);
		}

		System.out.println("---------获取请求数据方式5-------------");
		// request.getInputStream();是上传文件的时候获取数据的方式
		// 普通数据是获取不到的
		InputStream in = request.getInputStream();
		int len = 0;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) > 0) {
			System.out.println(new String(buffer, 0, len));
		}

	}

}
