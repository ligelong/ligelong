/*
 * Created on 2011-10-20
 */
package com.ligelong.util;

import org.springframework.web.servlet.ModelAndView;

/**
 * <code>Contants</code>
 *
 * @author David Gong
 */
public interface Constants {
    /**
     *
     */
    int PAGE_LENGTH = 20;
    /**
     *
     */
    ModelAndView PERMIT_DENY_VIEW = new ModelAndView("/user/permitdeny");
	/**
	 *
	 */
	int[] VIP_USER_IDS = new int[]{2,10,137,138};
	/**
	 *
	 */
	String USER_ID_COOKIE_NAME = "user_entity_id";
	/**
	 *
	 */
	String USER_MD5_COOKIE_NAME = "md5_user_id";
}