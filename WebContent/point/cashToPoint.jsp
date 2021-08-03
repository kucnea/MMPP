<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   	

<style>
@import
	url('https://fonts.googleapis.com/css2?family=Yeon+Sung&display=swap');
div {
	font-family: 'Yeon Sung', cursive;
	font-size:1.5em;
}
</style>

<c:if test="${param.fail=='y' }">
	<script type="text/javascript">
		alert("계좌정보가 잘못되었습니다. 고객님");
	</script>
</c:if>

<c:if test="${param.fail=='n' }">
	<script type="text/javascript">
		alert("성공적으로 포인트로 전환되었습니다. 고객님");
	</script>
</c:if>



<div style="width: 100%; height: 100%; background-color: black; color: white;">
	안녕하세요 모두에게 행복을 드리고싶은 옴닉입니다.
	<br><br>
	${userData.userId }님의 귀중한 현금을 저희가 제공하는 포인트로 교환하실 수 있습니다.<br>
	출금을 희망하는 은행을 선택해주세요.<br><br>
	
	<form action="../cashToPoint.do" method="post" name="data" onsubmit="return validData()">
	<input type="hidden" name="userId" value="${userData.userId }">
	<input type="hidden" name="userPrePoint" value="${userData.userPoint }">
	
	<input type="text" name="t_bankList" list="bankList">
	 <datalist id="bankList">
        <option value="한국인은행"></option>
        <option value="매울신라면은행"></option>
        <option value="하나둘은행"></option>
    </datalist>

	<br><br>
	출금을 희망하시는 계좌를 선택해주세요. :)  &nbsp;&nbsp;&nbsp;&nbsp;    (등록된 계좌로만 가능한 점 확인 부탁드립니다.)
	<br><br>
	
	<c:choose>
	
	<c:when test="${userData.userAcc1 != null }">
	<input type="text" name="t_accList" list="accList" placeholder="계좌를 선택해주세요." >
	<datalist id="accList">
		<option value="${userData.userAcc1 }"/>
		<option value="${userData.userAcc2 }"/>
		<option value="${userData.userAcc3 }"/>
	</datalist>
	
	<input type="password" name="t_accPw" placeholder="계좌 비밀번호를 입력해주세요">
	</c:when>
	
	<c:otherwise>
	<input type="text" name="t_accList" list="accList" placeholder="등록된 계좌가 없습니다." disabled>
	</c:otherwise>
	</c:choose>
	
	
	<br><br>
	출금을 희망하는 금액을 입력해주세요. 1원당 1포인트로 환전이 가능합니다.
	<br><br>
	
	<input type="number" name="insertCoin" placeholder="이곳입니다." required>
	
	<br><br>
	감사합니다. 승인 처리 버튼을 눌러주세요.
	<br><br>
	
	<input type="submit" value="승인" style="width:360px;height:120px;font-size:1.5em;">
	</form>
	
	
	
	
</div>