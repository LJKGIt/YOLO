<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>boardError</title>
</head>
<body>
	<h2>에러에러에러에러에러에러에러에러</h2>
	<h3>에러에러에러에러에러에러에러에러</h3>
	<h4>에러에러에러에러에러에러에러에러</h4>
	<br>Error가 발생했습니다. 주의하세요!!<br>
	<% if(exception != null){ %>
	에러 메세지 :  <%=exception.getMessage()%> 
	에러 종류는 <%=exception.getClass().getName()%> 입니다. 
	<% } %>
	<br>
	<h1>Servlet 에서 전송한 에러 메세지 : <%= (String)request.getAttribute("message") %></h1>		
	
</body>
</html>