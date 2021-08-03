<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${buyBasket=='y' }">
	<script type="text/javascript">
		alert("장바구니에 추가되었습니다.");
	</script>
</c:if>

<c:if test="${fail=='y' }">
	<script type="text/javascript">
		alert("이미 구매했거나 장바구니에 넣은 상품은 구매할 수 없습니다.");
	</script>
</c:if>

<c:if test="${buy =='0' }">
	<script type="text/javascript">
		alert("포인트가 부족하다. 휴먼");
	</script>
</c:if>

<c:if test="${buy =='1' }">
	<script type="text/javascript">
		alert("구매가 완료되었습니다. 좋은하루되시기 바랍니다.");
	</script>
</c:if>

<h3>회원게시판</h3>
<hr>
 <table style="width:80%;margin:auto;">
 	<tr><td width="20%">제목</td>
 		<td width="40%">${bean.imgboardSubject}</td>
 	</tr>
 	<tr><td>작성자</td>
 		<td>${bean.imgUserId}</td>
 		<td>작성날짜</td>
 		<td>
 		<fmt:formatDate value="${bean.wdate }" pattern="yyyy-MM-dd a hh:mm"/>
 		</td>
 	</tr>
 	<tr><td class="td1">내용</td>
 		<td colspan="3" class="input1" style="text-align: left;">
 		<div  style="height: 300px;">
 		<img src="/img_path/${bean.imgName }" height="280px">
 		<hr>
 		<pre style="color:white; font-size:20px;">${bean.imgboardDetail }</pre>
 		<hr>
 		<a style="color:white;">구매 포인트 : ${bean.imgPoint }</a>
 		</div></td>
 		
 	</tr>
 	<tr><br></tr>
 	<tr><br></tr>
 	<tr><br></tr>
 	
 	<c:set var="usingUserId" value="${userData.userId }"/>
 	<c:if test = "${bean.imgUserId == usingUserId || usingUserId == 'admin' }"> 
 	<tr><td colspan="4" align="center"><br>
 	<form action="imgDetailDelete.do" style="float:right;">
 	<input type="hidden" name="imgIdx" value="${bean.imgIdx }">
 	<input type="hidden" name="pno" value="${pno }">
	<input type="submit" value="삭제">
	</form>
 	<form action="imgDetailUpdate.do" style="float:right;">
 	<input type="hidden" name="imgIdx" value="${bean.imgIdx }">
 	<input type="hidden" name="pno" value="${pno }">
	<input type="submit" value="수정">
	</form>
 	</td></tr>
 	</c:if>
 	<tr><td colspan="4" align="center">
 	<form action="imgboardData.do" style="float:right;">
 	<input type="hidden" name="pno" value="${pno }">
	<input type="submit" value="목록">
	</form>
 	<form action="directBuy.do" style="float:right;">
 	<input type="hidden" name="imgName" value="${bean.imgName }">
 	<input type="hidden" name="imgSubject" value="${bean.imgboardSubject }">
 	<input type="hidden" name="imgDetail" value="${bean.imgboardDetail }">
 	<input type="hidden" name="imgUserId" value="${bean.imgUserId }">
 	<input type="hidden" name="pno" value="${pno }">
 	<input type="hidden" name="imgIdx" value="${bean.imgIdx }">
 	<input type="hidden" name="imgPoint" value="${bean.imgPoint }">
	<input type="submit" value="구매">
	</form>
	<form action="imgBuyBasketInsert.do" style="float:right;">
	<input type="hidden" name="imgIdx" value="${bean.imgIdx }">
	<input type="hidden" name="imgName" value="${bean.imgName }">
	<input type="hidden" name="imgPoint" value="${bean.imgPoint }">
	<input type="hidden" name="imgSubject" value="${bean.imgboardSubject }">
	<input type="hidden" name="imgDetail" value="${bean.imgboardDetail }">
 	<input type="hidden" name="pno" value="${pno }">
 	
	<input type="submit" value="장바구니">
	</form>
	</td></tr>
 </table>