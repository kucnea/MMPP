<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style>
table{
 border-color : black;
 border-style : solid;
}
th{
 color : white;
 background : black;
}
a{
 color : white;
 text-decoration : none;
}
</style>

<table id="category">
<tr>
<th><a href="./first.jsp" target="iframe_main">메인페이지</a></th>
<th><a href="selectGame.do" target="iframe_main">포인트 획득</a></th>
<th><a href="pointCheck.do" target="iframe_main">포인트 확인</a></th>
<th><a href="pointExchange.do" target="iframe_main">환전소</a></th>
<th><a href="imgboardData.do" target="iframe_main">갤러리</a></th>
<th><a href="freeBoardData.do" target="iframe_main">회원게시판</a></th>
<th><a href="imgBuyBasket.do" target="iframe_main">장바구니</a></th>
<th><a href="Product.do" target="iframe_main">결제상품</a></th>
<th><a href="userDataUpdate.do" target="iframe_main">회원정보수정</a></th>

<%
if(session.getAttribute("userId")==null){
}
else if(session.getAttribute("userId").equals("admin")){ %>
<th>관리페이지</th>
<% } %>
</tr>
</table>
