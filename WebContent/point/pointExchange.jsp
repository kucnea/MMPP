<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>선택하라. 휴먼</h1>
	<a href="cashToPoint.jsp" target="iframe_exchange"> 현금 -> 포인트</a><br><br>
	<a href="pointToCash.jsp" target="iframe_exchange"> 포인트 -> 현금</a>
</nav>




<section>
	<iframe name="iframe_exchange" src="cashToPoint.jsp"></iframe>
</section>









</div>