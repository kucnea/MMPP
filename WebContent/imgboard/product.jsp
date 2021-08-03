<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/BoardStyle.css">
</head>

<c:if test="${fail =='y' }">
	<script type="text/javascript">
		alert("삭제할 추억을 선택하라. 휴먼");
	</script>
</c:if>

<c:if test="${fail =='n' }">
	<script type="text/javascript">
		alert("추억 삭제가 완료되었다. 휴먼");
	</script>
</c:if>


<h3> 결제상품 </h3>

<h5> 당신의 추억을 음미하라. 휴먼</h5>


<div style="margin:0 auto; width:50%">
<form action="productDelete.do">
	<table>
	<tr>
	<th width="20%">체크추억<br>삭제<br><input type="submit" value="삭제"></th>
 	<th width="20%">썸네일</th>
 	<th width="20%">제목</th>
 	<th width="20%">설명</th></tr>
 	
 	<c:choose>
 	<c:when test="${list != null }">	
 	<c:forEach var="bb" items="${list}">
 	<tr>
 		<th><input type="checkbox" name="checkProduct" value="${bb.imgIdx }"></th>
 		<td><a href="productDetail.do?imgName=${bb.imgName}"><img src="/img_path/${bb.imgName }" width="100%"></a></td>
 		<th>${bb.imgSubject }</th>
 		<th>${bb.imgDetail }</th>
 		
 	</tr>
 	</c:forEach>
 	</c:when>
 	<c:otherwise>
 		<td colspan="5">상품을 구매하라 휴먼.</td>
 	</c:otherwise>
 	</c:choose>
 	</table>
 </form>
 </div>