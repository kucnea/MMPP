<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/BoardStyle.css">
</head>


<c:if test="${fail=='y' }">
	<script type="text/javascript">
		alert("로그인이 필요합니다. 비회원은 조회만 가능합니다.");
	</script>
</c:if>






<h3> 갤러리 </h3>

<h5> 당신의 노고로 추억을 구매하라. 휴먼</h5>

<div style="margin:auto;">
	<table>
	<tr><th width="10%">번호</th>
 		<th width="20">썸네일</th>
 		<th width="25%">제목</th>
 		<th width="15%">휴먼아이디</th>
 		<th width="10%">가격</th>
 		<th width="20%">작성일</th></tr>	
	<c:forEach var="vo" items="${blists.list2}">
	<tr>
		<td>${vo.imgIdx }</td> 	
		<td><a href="imgboardDetail.do?imgIdx=${vo.imgIdx}&pno=${blists.currentPage}"><img src="/img_path/${vo.imgName }" width="100px" height="100px"></a></td>
 		<td><a href="imgboardDetail.do?imgIdx=${vo.imgIdx}&pno=${blists.currentPage}" class="title">${vo.imgboardSubject }</a></td>
 		<td>${vo.imgUserId }</td>
 		<td>${vo.imgPoint }</td>
 		<td>
 		<fmt:formatDate value="${vo.wdate }" pattern="yyyy-MM-dd"/>
 		</td>
 	</tr>
	</c:forEach>
	<tr></tr> 		
	<tr><td colspan="4"><a class="button" href="imgboardDataInsert.do">추억 판매하기</a>&nbsp;&nbsp;&nbsp;<a class="button" href="imgBoardData.do">첫 페이지</a></td>
 	<td>작성글 총 개수 : ${blists.totalCount}</td>
 	</tr>
 	</table>
</div>
 <div style="text-align: center;">
	<a class="pagenum" href="?pno=1">&lt;&lt;</a>   <!-- 요청url은 동일하고 파라미터만 변경됩니다. -->
	<a class="pagenum" href="?pno=${blists.currentPage-1 }">&lt;</a>  
	
	<c:forEach var="i" begin="${blists.startPage }" end="${blists.endPage }">  <!-- 페이지목록의 범위  -->
		<a class="pagenum" href="?pno=${i}">${i}</a>     <!-- 현재페이지 i값으로 변경  -->
	</c:forEach>
	
	<a class="pagenum" href="?pno=${blists.currentPage+1 }">&gt;</a> 
	<a class="pagenum" href="?pno=${blists.totalPage }">&gt;&gt;</a>
</div>