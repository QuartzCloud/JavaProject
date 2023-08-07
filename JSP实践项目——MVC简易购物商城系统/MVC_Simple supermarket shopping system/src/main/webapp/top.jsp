<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>top</title>
</head>
<body>
	<%
	String uname = (String) session.getAttribute("uname");
	%>
	MVC商城欢迎你<%=uname%>
</body>
</html>