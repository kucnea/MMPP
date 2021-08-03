<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>
html,body{
	height:100%;
	margin:0;
	}
div{
	height:100%;
}
nav{
	width:20%;
	height:100%;
	background-color: black;
	float : left;
}
section{
	border:none;
	height:100%;	
}
iframe{
	width:70%;
	height:90%;
	border:none;
	padding-top:20px;
	padding-left:20px;
}
a{	
	color:white;
	text-decoration:none;
	font-size:1.5em;
}
a:hover{
	color:gray;
}

</style>
<div style="width:100%; height:100%; background-color:black; color:white;" >

<nav>
	<h1>관리페이지</h1>
	<a href="adminPage1.jsp" target="iframe_exchange"> 회원 탈퇴 처리</a><br><br>
	<a href="adminPage2.jsp" target="iframe_exchange"> 회원 정보수정 처리</a><br><br>
	<a href="adminPage3.jsp" target="iframe_exchange"> 상품 정보수정 처리</a><br><br>
</nav>




<section>
	<iframe name="iframe_exchange" src="adminPage1.jsp"></iframe>
</section>









</div>