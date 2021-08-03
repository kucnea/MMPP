<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="black">
	<div align="center">
		<br><a style= "color:white">${resultId } ${resultPw }<br>
		${msg }</a> <br><br>
		<a href="#" onClick="location.href='home.do'">닫기</a>
	</div>
</body>
</html>