/**
 * Create on 2011-10-28
 */
package com.ligelong.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <code>WebUtil</code>
 *
 * @author David Gong
 */
public class WebUtil {
    private WebUtil() {//
    }

    /**
     * 
     * @param request
     * @param parameterName
     * @param defaultValue
     * @return
     */
    public static String getParameter(HttpServletRequest request, String parameterName, String defaultValue) {
        String temp = request.getParameter(parameterName);
        String ret = defaultValue;
        if (temp == null || "null".equals(temp)) {
        	return ret;
        }
        ret = temp;
        return ret.trim();
    }

    /**
     * 
     * @param request
     * @param parameterName
     * @param defaultValue
     * @return
     */
    public static int getParameterInteger(HttpServletRequest request, String parameterName, int defaultValue) {
        String temp = request.getParameter(parameterName);
        int ret = defaultValue;
        if(temp!=null) {
        	try {
        		ret = Integer.parseInt(temp.trim());
        	} catch (Exception e) {
        	}
        }
        return ret;
    }

    /**
     * 
     * @param request
     * @param parameterName
     * @param defaultValue
     * @return
     */
    public static long getParameterLong(HttpServletRequest request, String parameterName, long defaultValue) {
        String temp = request.getParameter(parameterName);
        long ret = defaultValue;
        if(temp!=null) {
        	try {
        		ret = Long.parseLong(temp.trim());
        	} catch (Exception e) {
        	}
        }
        return ret;
    }

    /**
     * 
     * @param request
     * @param parameterName
     * @param defaultValue
     * @return
     */
    public static String[] getParameters(HttpServletRequest request, String parameterName, String[] defaultValue) {
        String[] temp = request.getParameterValues(parameterName);
        String[] ret = defaultValue;
        try {
        	if(temp!=null) {
        		ret = temp;
        	}
        } catch (Exception e) {
        }
        return ret;
    }


    /**
     * 
     * @param time
     * @param format
     * @return
     */
    public static String formatTime(long time, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date(time));
    }

    /**
     * 
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        String cookieValue = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookieName.equals(cookies[i].getName())) {
                    return cookies[i].getValue();
                }
            }
        }
        return cookieValue;
    }

    /**
     * 
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param expire
     */
    public static void setCookies(HttpServletResponse response, String cookieName, String cookieValue, int expire) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(expire);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 
     * @param response
     * @param cookieName
     */
    public static void removeCookies(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 
     * @param request
     * @param action
     * @return
     */
    public static boolean isActionPermit(HttpServletRequest request, AclEnties action) {
        @SuppressWarnings("unchecked")
        Set<Integer> itemSet = (Set<Integer>) request.getAttribute("user_roleitem_list");
        if (itemSet == null) {
            return false;
        }
        if (itemSet.contains(action.getId())) {
            return true;
        }
        return false;
    }

    /**
     * 
     * @param response
     */
    public static void setResponseNoCache(HttpServletResponse response) {
    	response.addHeader("Expires", "Mon, 26 Jul 1997 05:00:00 GMT");
		response.addHeader("Cache-Control", "no-cache, must-revalidate");
		response.addHeader("Pragma", "no-cache");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("utf-8");
    }

    /**
     * 
     * @param url
     * @return
     */
    public static String getContentFromUrl(String url) {
    	String ret = "";
    	HttpURLConnection conn = null;
    	InputStream in = null;
    	try {
    		URL u = new URL(url);
    		conn = (HttpURLConnection)u.openConnection();
    		in = conn.getInputStream();
    		byte[] b = new byte[1024];
    		StringBuilder sb = new StringBuilder();
    		while((in.read(b))>0) {
    			sb.append(new String(b));
    		}
    		return sb.toString();
    	} catch(Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if(conn!=null) {
    			conn.disconnect();
    		}
    		if(in!=null) {
    			try {
    				in.close();
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return ret;
    }

    /**
     * 
     * @param f
     * @param response
     * @throws Exception
     */
    public static void downloadCsvFile(File f, HttpServletResponse response) throws Exception {
		if(!f.exists()){
			response.sendError(404,"File not found!");
			return;
		}
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		response.setContentType("application/csv");
		response.setHeader("Content-Disposition", "inline;filename="+
							new String(f.getName().getBytes("gbk"), "iso8859-1"));
		//�ļ���Ӧ�ñ����UTF-8
		byte[] buf = new byte[1024];
		int len = 0;
		OutputStream out = response.getOutputStream();
		while((len = br.read(buf)) >0) {
			out.write(buf,0,len);
		}
		br.close();
		out.close();
    }
}