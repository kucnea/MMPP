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

<c:if test="${param.fail=='n' }">
	<script type="text/javascript">
		alert("성공적으로 출금되었다. 휴먼");
	</script>
</c:if>

<c:if test="${param.fail=='y' }">
	<script type="text/javascript">
		alert("계좌정보가 잘못되었다. 휴먼");
	</script>
</c:if>

<c:if test="${param.fail2=='y' }">
	<script type="text/javascript">
		alert("포인트가 부족하다. 휴먼");
	</script>
</c:if>



<div style="width: 100%; height: 100%; background-color: black; color: white;">
	옴닉이다.
	<br><br>
	${userData.userId }님, 포인트를 귀중한 현금으로 교환하고 싶은가?<br>
	입금 받아볼 은행를 골라라. 휴먼<br><br>
	
	<form action="../pointToCash.do">
	<input type="hidden" name="userId" value="${userData.userId }">
	<input type="hidden" name="userPrePoint" value="${userData.userPoint }">
	
	<input type="text" name="t_bankList" list="bankList">
	 <datalist id="bankList">
        <option value="한국인은행"></option>
        <option value="매울신라면은행"></option>
        <option value="하나둘은행"></option>
    </datalist>

	<br><br>
	입금을 희망하시는 계좌를 골라라 휴먼. &nbsp;&nbsp;&nbsp;&nbsp;    (등록된 계좌로만 가능하다. 휴먼)
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
	환전 희망하는 포인트를 입력해라. 휴먼. 1포인트는 0.8원으로 환전이 가능하다.
	<br><br>
	
	<input type="number" name="insertCoin" placeholder="이곳입니다." required>
	
	<br><br>
	승인 처리 버튼을 눌러라. 휴먼.
	<br><br>
	
	<input type="submit" value="승인">
	</form>
	
	
	
	
</div>