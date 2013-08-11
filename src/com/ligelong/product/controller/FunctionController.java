package com.ligelong.product.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * <code>PostController</code>
 *
 * @author David Gong
 */
@Controller
@RequestMapping(value="/f/")
public class FunctionController {

	/**
	 *
	 */
	@RequestMapping(value = "/advice.do")
	public ModelAndView advice(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("function/advice", model);
	}

	/**
	 *
	 */
	@RequestMapping(value = "/setting.do")
	public ModelAndView setting(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("function/setting", model);
	}

	/**
	 *
	 */
	@RequestMapping(value = "/nav.do")
	public ModelAndView nav(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("function/nav", model);
	}

	/**
	 *
	 */
	@RequestMapping(value = "/corp.do")
	public ModelAndView corp(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("function/corp", model);
	}
}
