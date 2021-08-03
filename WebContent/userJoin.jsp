<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
a{
 color : red;
 text-decoration : none;
 font-size : 0.5em;
}
</style>
<script type="text/javascript">
function validId(){
	var userId = document.userForm.userId.value;
	
	if(userId==""){
		alert("아이디를 입력하라. 휴먼");
		document.userForm.userId.focus();
		return false;
	}else if(userId.length <4){
		alert("아이디는 4글자 이상이다. 휴먼");
		document.userForm.userId.focus();
		return false;
	}
	
	var url="./idCheck.do?userId="+userId;
	window.open(url,"아이디 중복체크","width=300,height=150,left=900");
	
	/* var result = function(){
		alert(request.getParameter(msg));
	}
	return result; */
}

function validForm(){
	var form = document.userForm;
	
	if(form.userPw.value.length < 4) {
		alert("패스워드는 4글자 이상입니다.");
		form.userPw.focus();
		return false;
	}
	return true;
}

</script>


<c:if test="${param.check=='y' }">
	<script type="text/javascript">
			var msg = ${msg};
			var ch = ${ch};
			var userId = ${userId};
			var url = "./user/idCheck.jsp?msg="+msg+"&ch="+ch+"&userId="+userId;
			window.open(url,"아이디 중복체크","width=300,height=150,left=900");	
			alert('<c:out value="${msg}"/>');
	</script>
</c:if>

<c:if test="${param.check=='n' }">
	<script type="text/javascript">
		var msg = ${msg};
		var ch = ${ch};
		var userId = ${userId};
		var url = "./user/idCheck.jsp?msg="+msg+"&ch="+ch+"&userId="+userId;
		window.open(url,"아이디 중복체크","width=300,height=150,left=900");
	</script>
</c:if>

<c:if test="${param.fail=='y' }">
	<script type="text/javascript">
		alert("중복체크 이후에 등록가능하다 휴먼.");
	</script>
</c:if>


<div style="position: relative; left:700px; top:180px;">
<form action="idCheck.do" method="post" name="userForm" onsubmit="return validId()">
<table>
<tr><th>회원정보</th></tr>
<c:choose>
<c:when test="${ch==true }">
<tr><th colspan="2"><input type="text" placeholder="(필수)원하는 아이디를 입력하라. 휴먼" name="userId" value="${userId }" required></th><th><input type="submit" value="중복체크"></th></tr>
<tr><th colspan="2"><a>${msg }</a></th></tr>
</c:when>
<c:otherwise>
<tr><th colspan="2"><input type="text" placeholder="(필수)다른 아이디를 입력하라. 휴먼" name="userId" required></th><th><input type="submit" value="중복체크"></th></tr>
<tr><th colspan="2"><a>${msg }</a></th></tr>
</c:otherwise>
</c:choose>
</table>

</form>
<form action="join.do" method="post" name="userForm" onsubmit="return validForm()">
<input type="hidden" name="check" value="${ch }">
<input type="hidden" name="userId" value="${userId }">
<table>
<tr><th colspan="2"><input type="password" placeholder="(필수)원하는 비밀번호를 입력하라. 휴먼" name="userPw" required></th></tr>
<tr><th colspan="2">(필수)<label><input type="radio" name="userGender" value="M" checked>남자</label>
		<label><input type="radio" name="userGender" value="F" >여자</label></th></tr>
<tr><th colspan="2"><input type="text" placeholder="(필수)전화번호를 입력하라. 휴먼" name="userPhoneNumber" value="${userPhoneNumber }" required></th></tr>
<tr><th colspan="2"><input type="text" placeholder="(선택)등록 희망하는 계좌번호를 입력하라. 휴먼" name="userAcc" ></th></tr>
<tr><th colspan="2"><input type="password" placeholder="(선택)계좌의 비밀번호를 은밀히 입력하라. 휴먼" name="userAccPw" ></th></tr>
<tr><th><input type="submit" value="등록하기"></th><th><input type="button" onclick="location.href='home.do'" value="홈으로"></th></tr>
</table>
</form>
</div>
		

