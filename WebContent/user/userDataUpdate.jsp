<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript">
 function check(){
	var yn=confirm('탈퇴할것인가 휴먼?');
	var yn2=confirm('진짜로 탈퇴하겠나 휴먼?')
	if(yn){
		if(yn){
			alert('회원 탈퇴가 진행된다.');
			document.frm.submit();
		}
		
	}
 }
</script>


<div style="position: relative; left:700px; top:180px;">
<form action="userDataUpdate.do" method="post" name="userForm" onsubmit="return validForm()">
<table>
<tr><th>회원정보</th></tr>
<tr><th colspan="2"><input type="text" value="${bean.userId }" name="userId" placeholder="(필수)원하는 아이디를 입력하라. 휴먼" disabled="disabled"></th></tr>
<tr><th colspan="2"><input type="password" placeholder="(필수)원하는 비밀번호를 입력하라. 휴먼" name="userPw" required></th></tr>

<c:choose>
<c:when test="${bean.userGender=='M' }">
<tr><th colspan="2">(필수)<label><input type="radio" name="userGender" value="M" checked>남자</label>
		<label><input type="radio" name="userGender" value="F" >여자</label></th></tr>
</c:when>
<c:otherwise>
<tr><th colspan="2">(필수)<label><input type="radio" name="userGender" value="M" >남자</label>
		<label><input type="radio" name="userGender" value="F" checked>여자</label></th></tr>
</c:otherwise>
</c:choose>		
		
<tr><th colspan="2"><input type="text" value="${bean.userPhoneNumber }" name="userPhoneNumber" required></th></tr>
<tr><th colspan="2"><input type="text" value="${bean.userAcc1 }" placeholder="(선택)등록 희망하는 계좌번호를 입력하라. 휴먼" name="userAcc1" ></th></tr>
<tr><th colspan="2"><input type="password" placeholder="계좌를 등록하려면 비밀번호를 은밀히 입력하라. 휴먼" name="userAcc1Pw" ></th></tr>
<c:choose>
<c:when test="${not empty bean.userAcc2 }">
	<tr><th colspan="2"><input type="text" value="${bean.userAcc2 }" placeholder="(선택)등록 희망하는 계좌번호를 입력하라. 휴먼" name="userAcc2" ></th></tr>
	<tr><th colspan="2"><input type="password" placeholder="계좌를 등록하려면 비밀번호를 은밀히 입력하라. 휴먼" name="userAcc2Pw" ></th></tr>
</c:when>
<c:when test="${not empty bean.userAcc3 }">
	<tr><th colspan="2"><input type="text" value="${bean.userAcc3 }" placeholder="(선택)등록 희망하는 계좌번호를 입력하라. 휴먼" name="userAcc3" ></th></tr>
	<tr><th colspan="2"><input type="password" placeholder="계좌를 등록하려면 비밀번호를 은밀히 입력하라. 휴먼" name="userAcc3Pw" ></th></tr>
</c:when>
<c:otherwise></c:otherwise>
</c:choose>

<tr><th><input type="submit" value="등록하기"></th><th><input type="button" onclick="location.href='home.do'" value="홈으로"></th></tr>
</table>
</form>

<form action="selfDelete.do" method="post" name="frm">
<input type="hidden" value="${bean.userId }" name="userId">
<input type="button" value="아이디 삭제하기" onclick="check()">
</form>
</div>