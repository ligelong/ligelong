/**
 * 
 */
package com.ligelong.product.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ligelong.hibernate.entity.UserEntity;
import com.ligelong.hibernate.service.UserService;
import com.ligelong.util.Constants;
import com.ligelong.util.WebUtil;

/**
 *<code>UserOnlineFilter</code>
 *
 * @author David Gong
 */
public class UserOnlineFilter implements Filter {
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// StringBuffer requestURL = request.getRequestURL();
		// 先从session中获取用户信息
		UserEntity user = (UserEntity) request.getSession().getAttribute("user_entity");
		if (user == null) {
			// session失效时,从Cookie中获取信息
			String userEntityId = WebUtil.getCookie(request, Constants.USER_ID_COOKIE_NAME);
			if (userEntityId != null) {
				// 如果存在userentityid, 去数据库读取
				try {
					WebApplicationContext webApplicationContext = WebApplicationContextUtils
							.getWebApplicationContext(request.getSession()
									.getServletContext());
					UserService userService = (UserService) webApplicationContext
							.getBean(com.ligelong.hibernate.service.UserService.class);
					user = userService.find(Integer.parseInt(userEntityId));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		/* 用户用户不为空则放入session和页面中 */
		if (user != null) {
			request.getSession().setAttribute("user_entity", user);
			request.setAttribute("user", user);
		}
		filterChain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
