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


<h3> 회원게시판 </h3>

<h5> 이 곳에선 자유를 만끽해라. 휴먼</h5>

<div style="margin:0 auto; width:50%">
	<table>
	<tr><th width="10%">번호</th>
 		<th width="45%">제목</th>
 		<th width="15%">휴먼아이디</th>
 		<th width="10%">조회수</th>
 		<th width="20%">작성일</th></tr>	
	<c:forEach var="list" items="${blists.list}">
	<tr>
	
		<td>${list.idx }</td> 	
 		<td><a href="freeboardDetail.do?idx=${list.idx}&pno=${blists.currentPage}" class="title">${list.subject }</a>
 		...<span style="color:orange;font-size: 80%;">(${list.commentCount })
 		</span></td>
 		<td>${list.userId }</td>
 		<td>${list.readCount }</td>
 		<td>
 		<fmt:formatDate value="${list.wdate }" pattern="yyyy-MM-dd"/>
 		</td>
 	</tr>
	</c:forEach> 		
	<tr><td colspan="4"><a class="button" href="freeboard_insert.do">글쓰기</a>&nbsp;&nbsp;&nbsp;<a class="button" href="freeBoardData.do">첫 페이지</a></td>
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
<div  style="margin:0 auto; width:50%">
<form action="listSearch.do" method="post">
<table>
<tr>
	<th>
	<select name="searchList">
		<option value="subject">제목</option>
		<option value="content">내용</option>
		<option value="subject || content">제목+내용</option>
	</select>
	</th>
	<th>
	<input type="text" name="search" placeholder="검색어를 입력하세요" required><input type="submit" value="검색">
	</th>
</tr>
</table>
</form>
</div>