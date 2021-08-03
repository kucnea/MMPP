<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/BoardStyle.css">
</head>

<script>
function ejection(userId, imgIdx){
	var yn=confirm('추억을 구매하지 않을건가? 휴먼');
	if(yn){
		alert('상품을 장바구니에서 삭제했다. 휴먼');
		location.href='buyBasketDelete.do?userId='+userId+'&imgIdx='+imgIdx;
	}
}
</script>

<c:if test="${buy =='0' }">
	<script type="text/javascript">
		alert("포인트가 부족하다. 휴먼");
	</script>
</c:if>

<c:if test="${buy =='1' }">
	<script type="text/javascript">
		alert("구매가 완료되었습니다. 좋은하루되시기 바랍니다.");
	</script>
</c:if>

<c:if test="${fail =='y' }">
	<script type="text/javascript">
		alert("구매할 상품을 선택하세요.");
	</script>
</c:if>

<c:if test="${goneProduct =='y' }">
	<script type="text/javascript">
		alert("삭제된 상품은 구매할 수 없다.");
	</script>
</c:if>

<c:if test="${buy =='1' }">
	<script type="text/javascript">
	var totalPr = ${totalProduct};
	var userP = ${userPoint};
	var totalP = ${totalPoint};
		alert("총"+totalPr+"개의 상품을"+uerP+"에서"+totalP+"만큼 구매하셨습니다.");
	</script>
</c:if>

<h3> 장바구니 </h3>

<h5> 당신의 노고로 추억을 구매하라. 휴먼</h5>


<div style="margin:0 auto; width:50%">
<form action="payment.do">
	<table>
	<tr>
	<th width="20%">체크</th>
	<th width="20%">주문번호</th>
 	<th width="20%">썸네일</th>
 	<th width="20%">가격</th>
 	<th width="20%">옵션</th></tr>
 	
 	<c:choose>
 	<c:when test="${list != null }">	
 	<c:forEach var="bb" items="${list}">
 	<tr>
 		<td><input type="checkbox" name="checkBuyBasket" value="${bb.imgIdx }" checked="checked"></td>
 		<td>${bb.buyIdx }</td>
 		<td><a href="imgboardDetail.do?imgIdx=${bb.imgIdx}"><img src="/img_path/${bb.imgName }" width="100px" height="100px"></a></td>
 		<td>${bb.imgPoint }</td>
 		<td><a href="javascript:ejection('${bb.userId}','${bb.imgIdx }')" style="cursor: pointer;text-decoration: none;">제외</a></td>
 	</tr>
 	</c:forEach>
 	</c:when>
 	<c:otherwise>
 		<td colspan="5">상품을 구매하라 휴먼.</td>
 	</c:otherwise>
 	</c:choose>
 	<tr>
 	<th>${totalcountByUser }개의 상품.</th><td colspan="3">휴먼이 보유한 Point : ${userPoint} 상품 총 합 Point : ${totalPoint }</td><th><input type="submit" value="결제" style="float:right;"></th>
 	</tr>
 	</table>
 </form>
 </div>