package com.osf.test.utils;

import javax.servlet.http.HttpServletRequest;

public class URICommand {
	//가상으로 만드는 목데이터 (rPath => rootPath)
//    static String uri = "/test/foods/3";
//    static String rPath = "/test";
    public static String getCommand(HttpServletRequest req) {
    	String rPath = req.getContextPath() + "/";
    	String uri = req.getRequestURI();
    	uri = uri.substring(rPath.length());
    	return uri.substring(0, uri.indexOf("/"));
    }
    
//	public static void main(String[] args) {
//		rPath += "/";
//		uri = uri.substring(rPath.length());
//		System.out.println(uri);
//		String cmd = uri.substring(0,uri.indexOf("/"));
//		System.out.println(cmd);
//	}

}
