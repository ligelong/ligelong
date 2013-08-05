/*
 * Created on 2011-08-20
 */
package com.ligelong.product.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ligelong.hibernate.entity.UserEntity;
import com.ligelong.hibernate.service.UserService;
import com.ligelong.util.Constants;
import com.ligelong.util.MD5;
import com.ligelong.util.WebUtil;


/**
 * <code>PostController</code>
 *
 * @author David Gong
 */
@Controller
@RequestMapping(value="/user/")
public class UserController {

    @RequestMapping(value="/login.do")
	public ModelAndView post(HttpServletRequest request,
			HttpServletResponse response) {
    	Map<String, Object> model = new HashMap<String, Object>();
		if ("on".equals(WebUtil.getParameter(request, "action", ""))) {
			String username = WebUtil.getParameter(request, "username", "");
			String pwd = WebUtil.getParameter(request, "pwd", "");
			UserEntity user = userService.getUserByPwd(username, MD5.change(pwd));
			if (user != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user_entity", user);
				request.setAttribute("user", user);
				WebUtil.setCookies(response, Constants.USER_ID_COOKIE_NAME,
						user.getId() + "", 30 * 24 * 60 * 60);
				model.put("url", "/");
				return new ModelAndView("user/login_success", model);
			}
			model.put("info", "登录失败，请重试");
		}
    	return new ModelAndView("user/login", model);
    }
    
    @RequestMapping(value="/reg.do")
   	public ModelAndView reg(HttpServletRequest request,
   			HttpServletResponse response) {
    	if("on".equals(WebUtil.getParameter(request, "action", ""))) {
    		String username = WebUtil.getParameter(request, "username", "");
    		String pwd = WebUtil.getParameter(request, "pwd", "");
    		String email = WebUtil.getParameter(request, "email", "");
    		String phone = WebUtil.getParameter(request, "phone", "");
    		userService.addUser(username, MD5.change(pwd), email, phone);
    		return new ModelAndView("/user/login", "info", "创建成功，请登录");
    	}
    	return new ModelAndView("/user/reg");
    }
    
    
    @Resource
    private UserService userService;
}
