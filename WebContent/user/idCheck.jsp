<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body bgcolor="black">
	<div align="center">
		<br><a style= "color:white">${resultId }<br>
		${msg }</a> <br><br>
		
		<form action="userJoin.jsp" onsubmit="self.close()">
		<input type="submit" value="사용하기." name="checkedId">
		<input type="hidden" name="checkFlag" value="true">
		
		<% if( request.getAttribute("msg").equals("이것은 사용가능한 아이디다.")){ %>	//이 if문이 작동을 안하는 이유?
		<input type="submit" value="사용하기." name="checkedId">
		<input type="hidden" name="checkFlag" value="true">
	<% }else{ %>
	<% } %>
	
		</form>
		<a href="#" onclick="self.close()">닫기</a> //이것은 사용가능한 아이디다.
	</div>
	
	
</body>
</html>