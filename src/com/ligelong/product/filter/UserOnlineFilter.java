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

import com.ligelong.hibernate.entity.UserEntity;

/**
 *<code>UserOnlineFilter</code>
 *
 * @author David Gong
 */
public class UserOnlineFilter implements Filter {

	private FilterConfig filterConfig;
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		UserEntity user = (UserEntity) request.getAttribute("user");
		if(user==null) {
			user = getUserFromCookie(request);
		}
		chain.doFilter(request, response);
	}

	private UserEntity getUserFromCookie(ServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
