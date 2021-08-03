<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<c:if test="${param.fail=='y' }">
	<script type="text/javascript">
		alert("회원삭제에 실패했다. 아이디를 확인해라 관리자.");
	</script>
</c:if>

<c:if test="${param.fail=='n' }">
	<script type="text/javascript">
		alert("회원삭제에 성공했다. 관리자.");
	</script>
</c:if>


<form action="deleteUserData.do">
<table>
<tr>
	<th colspan="2"><h2 style="color:white;">회원 탈퇴 처리</h2></th>
</tr>
<tr>
	<th><h2 style="color:white;">탈퇴시키려는 회원의 아이디</h2></th><th><input type="text" name="userId"></th>
</tr>
<tr>
	<th colspan="2"><h3 style="color:red;">탈퇴시 되돌릴 수 없습니다.</h3></th>
</tr>
<tr>
	<th colspan="2"><input type="submit" value="탈퇴"></th>
</tr>
</table>
</form>