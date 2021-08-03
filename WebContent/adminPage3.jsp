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
		alert("상품정보수정에에 실패했다. 아이디를 확인해라 관리자.");
	</script>
</c:if>

<c:if test="${param.fail=='n' }">
	<script type="text/javascript">
		alert("상품정보수정에 성공했다. 관리자.");
	</script>
</c:if>

<c:if test="${param.del=='y' }">
	<script type="text/javascript">
		alert("상품삭제에 성공했다. 관리자.");
	</script>
</c:if>



<form action="updateGallery.do">
<h2 style="color:white;">상품 정보수정 처리</h2>
<input type="hidden" name="check" value="1">
<input type="text" name="imgIdx" placeholder="정보 수정하려는 상품번호"><input type="submit" value="검색">
</form>



<form action="updateGallery.do">
<input type="hidden" name="check" value="2">
<input type="hidden" name="imgIdx" value="${img.imgIdx }" >
<table>
<tr>
	<th colspan="2"><img src="/img_path/${img.imgName }" height="280px"></th>
</tr>
<tr>
	<th>상품 파일</th><th><input type="text" name="chImgName" value="${img.imgName }"></th>
</tr>
<tr>
	<th>상품 제목</th><th><input type="text" name="chImgSubject" value="${img.imgboardSubject }"></th>
</tr>
<tr>
	<th>상품 설명</th><th><textarea name="chImgDetail">${img.imgboardDetail }</textarea></th>
</tr>
<tr>
	<th>상품 가격</th><th><input type="text" name="chImgPoint" value="${img.imgPoint }"></th>
</tr>

<tr>
	<th colspan="2"><input type="submit" value="수정"></th>
</tr>
</table>
</form>
<form>
<input type="hidden" name="check" value="3">
<input type="hidden" value="${img.imgIdx }">
<input type="submit" value="상품 삭제">
</form>