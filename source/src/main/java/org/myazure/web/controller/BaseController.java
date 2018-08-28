package org.myazure.web.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.myazure.configuration.PrimaryConfiguration;
import org.myazure.response.StatusResponse;
import org.myazure.service.InfoDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Controller
public class BaseController {
	private static final Logger LOG = LoggerFactory
			.getLogger(BaseController.class);
	@Autowired
	PrimaryConfiguration primaryConfiguration;
	@Autowired
	InfoDataService infoDataService;

	public BaseController() {

	}


	protected void sentUnauthorizedResponse(HttpServletResponse response) throws IOException {
		response.addCookie(new Cookie("4682543566466", System
				.currentTimeMillis() + ""));
		response.sendRedirect("40400000.html");
		sentResponse(response, new StatusResponse(
				"access denied,login required", 6, false));
	}

	protected void sentLoginErrResponse(HttpServletResponse response) {
		response.addCookie(new Cookie("UserId", System.currentTimeMillis() + ""));
		sentResponse(response, new StatusResponse(
				"invalid username/password logon denied", 6, false));
	}

	protected void sentAuthorizedResponse(HttpServletResponse response) {
		response.addCookie(new Cookie("UserId", ""));
		sentResponse(response, new StatusResponse("logon sucess", 0, true));
	}

	protected void sentMissParamResponse(HttpServletResponse response) {
		sentResponse(response, new StatusResponse(
				"that a parameter is missing!double check pls!", 1, false));
	}

	protected boolean checkParams(HttpServletRequest request) {

		return true;
	}

	protected void sentResponse(HttpServletResponse response, Object object) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		try {
			LOG.debug("here is right test");
			if (object == null) {
				LOG.debug("here is right test2000");
				response.getWriter()
						.write(JSON
								.toJSONString(
										"Null MSG 500!",
										SerializerFeature.DisableCircularReferenceDetect,
										SerializerFeature.WriteMapNullValue));
			} else {
				LOG.debug(object.toString());
				LOG.debug("here is right test266");
				response.getWriter()
						.write(JSON
								.toJSONString(
										object,
										SerializerFeature.DisableCircularReferenceDetect,
										SerializerFeature.WriteNullNumberAsZero,
										SerializerFeature.WriteNullStringAsEmpty,
										SerializerFeature.WriteNullBooleanAsFalse,
										SerializerFeature.WriteMapNullValue,
										SerializerFeature.WriteNullListAsEmpty,
										SerializerFeature.WriteEnumUsingToString,
										SerializerFeature.WriteNonStringKeyAsString));
				LOG.debug("here is right test299");
			}
			response.getWriter().close();
		} catch (IOException e) {
			LOG.debug(e.getMessage());
			e.printStackTrace();
		}
	}

	protected Map<String, String> getRequestDatas(HttpServletRequest request) {
		Enumeration<String> e = request.getParameterNames();
		Map<String, String> datas = new HashMap<String, String>();
		while (e.hasMoreElements()) {
			String paramName = (String) e.nextElement();
			String value = request.getParameter(paramName);
			datas.put(paramName, value);
		}
		return datas;
	}

	protected boolean checkParam(Map<String, String> datas,
			List<String> requoreListString) {
		if (datas == null) {
			return true;
		}
		if (datas.keySet() == null) {
			return true;
		}
		if (datas.keySet().size() < 1) {
			return true;
		}
		for (String key : datas.keySet()) {
			LOG.debug("***********" + key + "***********" + datas.get(key)
					+ "***********");
		}
		if (requoreListString == null) {
			return true;
		}
		if (datas.keySet().containsAll(requoreListString)) {
			return true;
		} else {
			return false;
			// throw new MissingParamException("Params Not Founded");
		}
	}

	protected String checkParamString(Map<String, String> datas,
			List<String> requoreListString) {
		for (String key : datas.keySet()) {
			LOG.debug("***********" + key + "***********" + datas.get(key)
					+ "***********");
		}
		if (requoreListString == null) {
			return "No Param Required";
		}
		if (datas.keySet().containsAll(requoreListString)) {

		} else {
			for (String string : requoreListString) {
				if (datas.keySet().contains(string)) {

				} else {
					return string + " Not Founded in Params!";
				}
			}
		}
		return "All Param Here";
	}

}
