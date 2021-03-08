/**
 * 
 */
package com.yesjpt.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 陈剑波
 * 2017-5-14
 */
public class ContextUtil  {
    public static Set<String> getParameterNames() {
    	Set<String> all = new HashSet<String>();
    	Enumeration<String> enu = getRequest().getParameterNames();
    	while(enu.hasMoreElements()){
    		String p = enu.nextElement();
    		if(p.indexOf(".") > 0) all.add(p.substring(0, p.indexOf(".")));
    		all.add(p);
    	}
    	return all;
	}
    //取得上下文路径
    public static String getContextPath() {
		return getRequest().getContextPath();
	}
    //取得request的属性
    public static Object getAttribute(String name) { 
        return getRequest().getAttribute(name);   
    }   
    
	//取得访问者的IP地址
	public static String getIpAddr() { 
		HttpServletRequest request = getRequest();
	    String ip = request.getHeader("x-forwarded-for");    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("WL-Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getRemoteAddr();    
	    }    
	    return ip;    
	}
 	public static HttpServletRequest getRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
 	public static ServletContext getContext(){
		return getSession().getServletContext();
	}
	public static HttpSession getSession(){
		return getRequest().getSession();
	}
    //把前端取得的所有参数放到一个HashMap中
    public static Map<String, Object> getParams() {
    	HttpServletRequest request = getRequest();
		Map<String, Object> params = new HashMap<String, Object>();
    	Enumeration<String> enu = request.getParameterNames(); 
    	while(enu.hasMoreElements()) {
    		 String name = enu.nextElement();
    		 if(name.indexOf("*") == 0){//前端以【*参数名*】来区分是单个的参数还是数组
    			 params.put(name, request.getParameterValues(name));
    			 /*
    			 String[] arr = req.getParameterValues(name);
    			 if(arr.length > 0){
    				 System.out.println("个数：" + arr.length);
    				 for(String s: arr){
    					 System.out.println(name + " = " + s);
    				 }
    			 }
    			 */
    		 }else {
    			 params.put(name, request.getParameter(name));
    		 }
    	}
		return params;
	}
    
    public static String[] getParameterValues(String name){
    	return getRequest().getParameterValues(name);
    }
    public static JSONObject getParamsJson(){
    	JSONObject o = new JSONObject();
    	o.putAll(getParams());
    	return o;
    }
    public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}
}
