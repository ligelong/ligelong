/*
 * Created on 2011-08-20
 */
package com.ligelong.product.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ligelong.hibernate.entity.PostEntity;
import com.ligelong.hibernate.service.PostService;
import com.ligelong.hibernate.service.UserService;
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
    	Integer id = WebUtil.getParameterInteger(request, "id", 0);
    	
    	return new ModelAndView("user/login", model);
    }
    
    @Resource
    private UserService userService;
}
