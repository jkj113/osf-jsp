<%@page import="com.osf.test.vo.PhotoBoardVO"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
    Map<String,String> userMap = new HashMap<>();
    userMap.put("name","채희주쯧");
    request.setAttribute("userMap",userMap);
    RequestDispatcher ps = request.getRequestDispatcher("/views/app.jsp"); //갈 준비
    ps.forward(request,response); //이 때 간다.
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>