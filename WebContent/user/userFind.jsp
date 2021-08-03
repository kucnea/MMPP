<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    

<script type="text/javascript">
function validForm(){
	var form = document.userForm;
	
	if(form.find_radio.value.equals("id_radio")) {
		if(form.userPhoneNumber1==""){
			alert("핸드폰 번호 입력란은 공란일 수 없다. 휴먼");
			form.userPhoneNumber1.focus();
			return false;
		}
	}else{
		if(form.userId==""){
			alert("아이디 입력란은 공란일 수 없다. 휴먼");
			form.userId.focus();
			return false;
		}else if(form.userPhoneNumber2==""){
			alert("핸드폰 번호 입력란은 공란일 수 없다. 휴먼");
			form.userPhoneNumber2.focus();
			return false;
		}
	}
	return true;
}

function findId1(){
	var form = document.userForm;
	
	if(form.find_radio.value=="id_radio"){
		alert("아이디 찾기를 선택해라. 휴먼");
		return false;
	}else if(form.userPhoneNumber1.value==""){
		alert("핸드폰 번호를 입력해라. 휴먼");
		form.userPhoneNumber1.focus();
		return false;
	}
	
	window.open("./idFind.jsp","아이디 중복체크","width=300,height=150,left=900");
	
	/* var result = function(){
		alert(request.getParameter(msg));
	}
	return result; */
}
function findId2(){
	var form = document.userForm;
	
	if(form.find_radio.value=="pw_radio"){
		alert("비밀번호 찾기를 선택해라. 휴먼");
		return false;
	}else if(form.userPhoneNumber2.value==""){
		alert("핸드폰 번호를 입력해라. 휴먼");
		form.userPhoneNumber2.focus();
		return false;
	}
	
	window.open("./idFind.jsp","아이디 중복체크","width=300,height=150,left=900");
	
	/* var result = function(){
		alert(request.getParameter(msg));
	}
	return result; */
}

</script>



<div style="position: relative; left:700px; top:180px;">
<form method="post" name="userForm" onsubmit="return validForm()">

<h3>어느 것을 찾을건가. 휴먼</h3>
<label><input type="radio" name="find_radio" value="id_radio" checked>아이디 찾기</label>
<table>
<tr><th><input type="text" placeholder="핸드폰 번호를 입력하라. 휴먼" name="userPhoneNumber1"></th></tr>
<tr><th><input type="submit" value="아이디 찾기" formaction="findId.do" onsubmit="return findId1()"></th></tr>
</table>

<label><input type="radio" name="find_radio" value="pw_radio">비밀번호 찾기</label>
<table>
<tr><th><input type="text" placeholder="아이디를 입력하라. 휴먼" name="userId"></th></tr>
<tr><th><input type="text" placeholder="핸드폰 번호를 입력하라. 휴먼" name="userPhoneNumber2"></th></tr>
<tr><th><input type="submit" value="비밀번호 찾기" formaction="findId.do" onsubmit="return findId2()"></th></tr>
<tr><th><input type="button" onclick="location.href='home.do'" value="홈으로"></th></tr>
</table>
</form>
</div>
		

