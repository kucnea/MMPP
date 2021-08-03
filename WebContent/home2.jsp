<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Style.css">
<title>:: 반갑다 휴먼 MMP에 온 걸 환영한다. ::</title>
</head>
<body>
<div id="top">
<%@ include file="top.jsp" %>
</div>
<div id="center">


<c:if test="${param.loginResult=='n' }">
	<script type="text/javascript">
		alert("로그인 정보가 잘못되었다. 휴먼");
	</script>
</c:if>

<c:if test="${param.updateResult=='y' }">
	<script type="text/javascript">
		alert("정보수정이 잘 되었다. 휴먼");
	</script>
</c:if>


<c:if test="${param.longinState=='n' }">
	<script type="text/javascript">
		alert("로그인이 필요하다. 휴먼");
	</script>
</c:if>

<%-- <c:if test="${sessionScope.loginState=='n' }">
	<script type="text/javascript">
		alert("로그인이 필요하다. 휴먼");
	</script>
</c:if> --%>




<div style="position: relative; left:700px; top:180px;">

	
<table>
<c:choose>
	<c:when test="${userData == null}">
		<tr><th colspan="3"><h4>로그인</h4></th></tr>

			<form action="login.do" method="post">
			<tr><th colspan="3"><input type="text" placeholder="아이디를 입력해라. 휴먼" name="userId" required></th></tr>
			<tr><th colspan="3"><input type="password" placeholder="비밀번호를 입력해라. 휴먼" name="userPw" required></th></tr>
			<tr><th colspan="3"><input type="submit" value="로그인"></th></tr>
			<tr><th><a href="join.do">회원가입</a></th><th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th><th><a href="findId.do">아이디/비밀번호 찾기</a></th></tr>
			</form>

	</c:when>
	
	<c:otherwise>
		<tr><th>${userData.userId }님 안녕하세요!</th></tr>
		<tr><th><input type="button" onclick="location.href='logout.do'" value="로그아웃"></th></tr>
	</c:otherwise>
</c:choose>
	
<tr><th colspan="3"><input type="button" value="은행으로 서비스변경"></th></tr>

</table>
</div>
		





</div>
<div id="bottom">
<%@ include file="bottom.jsp" %>
</div>
</body>
</html>