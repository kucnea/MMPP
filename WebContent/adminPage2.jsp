<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
th{
	color:white;
}
</style>

<c:if test="${param.fail=='y' }">
	<script type="text/javascript">
		alert("회원정보수정에에 실패했다. 아이디를 확인해라 관리자.");
	</script>
</c:if>

<c:if test="${param.fail=='n' }">
	<script type="text/javascript">
		alert("회원정보수정에 성공했다. 관리자.");
	</script>
</c:if>


<form action="updateUserData.do">
<h2 style="color:white;">회원 정보수정 처리</h2>
<input type="hidden" name="check" value="1">

<input type="text" name="userId" placeholder="정보 수정하려는 회원의 아이디"><input type="submit" value="검색">
</form>



<form action="updateUserData.do">
<input type="hidden" name="check" value="2">
<input type="hidden" name="userid" value="${user.userId }" >
<table>
<tr>
	<th>회원 아이디</th><th><input type="text" name="chUserId" value="${user.userId }"></th>
</tr>
<tr>
	<th>회원 성별</th><th><input type="text" name="chUserGender" value="${user.userGender }"></th>
</tr>
<tr>
	<th>회원 핸드폰번호</th><th><input type="text" name="chUserPhoneNumber" value="${user.userPhoneNumber }"></th>
</tr>
<tr>
	<th>회원 포인트</th><th><input type="text" name="chUserPoint" value="${user.userPoint }"></th>
</tr>
<c:choose>
<c:when test="${user.userAcc1 != null }">
<tr>
	<th>회원 계좌1</th><th><input type="text" name="chUserAcc1" value="${user.userAcc1 }"></th>
</tr>
</c:when>
<c:otherwise>
<tr>
	<th>회원 계좌</th><th><input type="text" name="chUserAcc1" value="" placeholder="해당고객은 등록된 계좌가 없습니다."></th>
</tr>
</c:otherwise>
</c:choose>
<c:if test="${user.userAcc2 }">
<tr>
	<th>회원 계좌2</th><th><input type="text" name="chUserAcc2" value="${userData.userAcc2 }"></th>
</tr>
</c:if>
<c:if test="${user.userAcc3 }">
<tr>
	<th>회원 계좌3</th><th><input type="text" name="chUserAcc3" value="${user.userAcc3 }"></th>
</tr>
</c:if>
<tr>
	<th colspan="2"><input type="submit" value="수정"></th>
</tr>
</table>
</form>