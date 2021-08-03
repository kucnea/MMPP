<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<div id="totalrank" style="position:relative; left:300px; top:100px;">
	<table style="background-color:black; color:white; width:30%;">
		<tr><th colspan="3"><h3>전체랭킹</h3></th></tr>
		<tr><th>순위</th><th>아이디</th><th>포인트</th></tr>
		
		<c:set var="cnt" value="0"/>
		<c:forEach var="list" items="${list }">
		
		<c:if  test="${list.userPoint!=temp}">
		<c:set var="cnt" value="${cnt+1 }"/>
		</c:if>
		
		
		<tr><th>${cnt }</th><th>${list.userId }</th> <th>${list.userPoint }</th></tr>
		<c:set var="temp" value="${list.userPoint}"/>
		</c:forEach>		
	
	</table>
</div>

<div id="myrank" style="position:relative; left: 750px;">
<c:if test="${userData != null }">
	<h3>${userData.userId }휴먼. 당신의 랭킹은..!!</h3>
	<h3>${myranking }등 이다..!</h3>
	<h3><c:if test="${myranking > 1 }"> 분발하라.</c:if></h3>
	<h3><c:if test="${myranking == 1 }"> 잘하고있다.</c:if></h3>
</c:if>
</div>