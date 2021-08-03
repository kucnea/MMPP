<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/BoardStyle.css">
</head>


<form name="frm" method="post" action="../imgboardDataInsert.do" enctype="multipart/form-data">
 <table>
 	<tr><td width="25%" class="td1">제목</td>
 		<td><input type="text" name="imgboardSubject" size="70" class="input1" required="required"></td>
 	</tr>
 	<tr><td class="td1">작성자</td>
 		<td>${userData.userId }</td>
 	</tr>
 	<tr><td class="td1">내용</td>  <!-- textarea 의 크기 : rows="20" cols="80" -->
 		<td><textarea  rows="20" cols="80" name="imgboardDetail" class="input1" required="required"></textarea></td>
 	</tr>
 	<tr>
 		<td>추억의 이미지 파일</td><td><input type="file" name="pic" required="required"></td>
 		<td>판매할 가격 (Point)</td><td><input type="text" name="imgPoint" required="required"></td>
 	</tr>
 	<tr><td colspan="2" align="center">
 	<input type="submit" value="저장" class="btn" >
 	<!-- <a class="button" href="javascript:post_data();">저장</a> -->
 	<input type="reset"  value="초기화" class="btn">
 	<input type="button" value="목록" onclick="location.href='imgboardData.do'" class="btn">
 <!-- 	<a class="button" href="#">목록</a>  -->
 	</td></tr>
 </table>
 </form>