<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style.css">
<title>:: 반갑다 휴먼 MMP에 온 걸 환영한다. ::</title>
</head>
<body>
<div id="top">
<%@ include file="./top.jsp" %>
</div>
<div id="center">

<iframe name="iframe_main" src="./first.jsp" style="width:100%; height:100%;">

</iframe>



</div>
<div id="bottom">
<%@ include file="./bottom.jsp" %>
</div>
</body>
</html>