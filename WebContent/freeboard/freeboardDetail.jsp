<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>     
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/BoardStyle.css">
</head>
    <script type="text/javascript">
	function resetOk() {
		document.frmCmt.func.value = 1;   //댓글 추가
/* 		document.frm.cmd.value = '저장';
		document.frm.name.value = '';
		document.frm.content.value='';
		document.frm.password.value='';
		document.frm.content.disabled=false;
 */		document.frmCmt.name.disabled=false;      
 //댓글 수정하고 다시 올때: <body onload="resetOk()">, 댓글 수정 취소할때 reset 버튼 onclick
	}
	
	function  updateCmt(cmtidx,userId,content) {  //실제 댓글 수정은 content 컬럼만 , 화면에 값표시 함수
		var obj = document.frmCmt;
		obj.cmtidx.value=cmtidx;
		obj.userId.value = userId;  //
		//textarea 로 값 표시하기전에 <br>을 개행문자로 바꾸기 
		while(content.indexOf('<br>')!=-1){  //문자열 <br> 가 없을떄 까지 반복
			content = content.replace('<br>','\n');
		}
		obj.content.value=content;
		obj.func.value=2;    //댓글 수정  (func 1은 댓글입력, func 3은 댓글 삭제)
		document.frmCmt.userId.disabled=true;    //disabled 를 설정
	}
	
	function updateCmt2(cmtidx,userId,content) {
		var url = "./updateComment.do?cmtidx="+cmtidx+"&userId="+userId+"&content="+content;
		window.open(url,"댓글 수정","width=600,height=450,left=900");
	}
	
	function deleteCmt(cmtidx,pno,idx){
		var yn=confirm('댓글을 삭제할껀가? 휴먼');
		if(yn){
			alert('댓글을 삭제한다.');
			location.href='commentDelete.do?cmtidx='+cmtidx+'&pno='+pno+'&mref='+idx;
		}
	}
	
	//댓글 수정,삭제 - 데이터 전달
	function delSet(idx) {
		frm2.cmtidx.value=idx;	
		modal.style.display = "block";   //modal 화면에 보이기
	}
	
	//메인글 삭제 확인
	function deleteOk(idx,cpage){
		var yn= confirm( '글을 삭제할껀가 휴먼');
		
		if(yn){
			alert('글 ' + idx +'를 삭제합니다.');
			location.href='deleteAction.jsp?func=2&idx='+idx+'&pno='+cpage;
		}
	}

	function validUpdate(){
		return true;
	}
</script>

<c:if test="${param.longinState=='n' }">
	<script type="text/javascript">
		alert("로그인을 해야 댓글작성할 수 있다. 휴먼");
	</script>
</c:if>


<body onload="resetOk()">  <!-- 문서가 생성될때 실행 -->
<h3>회원게시판</h3>
<hr>
 <table style="width:80%;margin:auto;">
 	<tr><td width="20%" class="td1">제목</td>
 		<td width="40%" class="input1">${bean.subject}</td>
 		<td width="20%" class="td1">조회수</td>
 		<td class="input1">${bean.readCount}</td>
 	</tr>
 	<tr><td class="td1">작성자</td>
 		<td class="input1">${bean.userId}</td>
 		<td class="td1">작성날짜</td>
 		<td class="input1">
 		<fmt:formatDate value="${bean.wdate }" pattern="yyyy-MM-dd a hh:mm"/>
 		</td>
 	</tr>
 	<tr><td class="td1">내용</td>
 		<td colspan="3" class="input1" style="text-align: left;">
 		<div  style="height: 300px;">
 		<pre>${bean.content }</pre></div></td>   <!-- 엔터,탭,기호 등 텍스트 그대로 출력할 때 사용 -->
 	</tr>
 	<c:set var="usingUserId" value="${userData.userId }"/>
 	<c:if test = "${bean.userId == usingUserId || usingUserId == 'admin' }"> 
 	<tr><td colspan="4" align="center"><br>
 	<form action="freeBoardData.do" style="float:right;">
 	<input type="hidden" name="pno" value="${pno }">
	<input type="submit" value="목록">
	</form>
 	<form action="detailDelete.do" style="float:right;">
 	<input type="hidden" name="idx" value="${bean.idx }">
 	<input type="hidden" name="pno" value="${pno }">
	<input type="submit" value="삭제">
	</form>
 	<form action="detailUpdate.do" style="float:right;">
 	<input type="hidden" name="idx" value="${bean.idx }">
 	<input type="hidden" name="pno" value="${pno }">
	<input type="submit" value="수정">
	</form>
 	<%-- <a class="button" href="updateAction.jsp?func=view&idx=${bean.idx }&pno=${pno}">수정</a>
 	<a class="button" onclick="javascript:deleteOk(${bean.idx },${pno });">삭제</a>
 	<a class="button" href="listAction.jsp?pno=${pno }">목록</a><br><br><br> --%>
 	</td></tr>
 	</c:if>
 </table>
 <!-- 메인글 출력 끝 -->
 <!-- 댓글 시작 -->
 <form action="commentInsert.do" method="post" name="frmCmt"><!-- 댓글 입력  , 추가 : 댓글수정과 삭제 -->
 <input type="hidden" name="func" value="1">  <!-- 기본값은 댓글 입력 , 수정:2 ,삭제:3 -->
 <input type="hidden" name="cmtidx" value="0">  <!-- 댓글의 PK(기본키) 컬럼 값 -->
 <input type="hidden" name="mref" value="${bean.idx}">  <!-- 메인글의 idx -->
 <input type="hidden" name="pno" value="${pno}">   <!-- 현재글의 페이지번호 -->
 <input type="hidden" name="userId_comment" value=${userData.userId}>
 <table style="width:80%;margin: auto;">
 	<tr><td colspan="4">댓글 갯수 : ${bean.commentCount }    
 		<input type="button" onclick="window.location.reload()" value="새로고침" class="btn-small">
 		<!--  또는 window.location.reload() :history.go(0)현재페이지로 url 재요청-->
 	</td>
 	</tr>
 	<tr><td colspan="4"><hr></td></tr>
 	<!-- 댓글 입력 -->
 	<tr>
 		<td width="25%" >작성자</td><td width="25%">
 		${userData.userId}</td>
 	</tr>
 	<tr>
 		<td colspan="3">  <!-- 크기조절불가 : style="resize: none;" -->
 			<textarea rows="5" cols="80" name="content"  style="resize: none;" placeholder="댓글을 작성하세요." class="input1"></textarea>
 		</td>
 		<td width="15%" style="text-align: left;">
 			<input type="submit" value="저장" class="btn-small">
 			<input type="reset" value="취소" class="btn-small" onclick="resetOk()">
 		</td>
 	</tr>
 	<tr><td colspan="4"><hr></td></tr>
 	<!-- 댓글 출력  -->
 	<c:forEach var="cmt" items="${cmtlist}">
 		<c:set var="cr" value="${cr}"/>   <!-- 개행문자 저장한 애트리뷰트 가져오기 -->
 		<c:set var="content" value="${fn:replace(cmt.content,cr,'<br>')}" />   <!--개행문자를 <br>로 바꾸기  -->
 		<tr>
 			<td colspan="4" style="text-align: left;">
 	<div id="comment">
 			<div>
			 <span class="name">${cmt.userId}</span>				
 			 <span class="now">
 			 	<fmt:formatDate value="${cmt.wdate }" pattern="yyyy-MM-dd a hh:mm"/>
 			 </span>
 			 <c:set var="userId" value="${userData.userId }"/>
 			 <c:if test="${cmt.userId == userId || userId == 'admin'}">
 			 <span style="float: right;">  <!-- span은 부모영역의 오른쪽으로 정렬  -->
 		<!-- updateCmt() :댓글 리스트 중에   선택한 인덱스의 name,content 값을 input의 value 로 합니다.-->
	 			 <a href="javascript:updateCmt2('${cmt.cmtIdx}','${cmt.userId}','${content}')" style="cursor: pointer;text-decoration: none;">
	 			 	수정
	 			 </a>
	 	<!-- deleteCmt() : 선택한 글 index값(idx 프로퍼티)을 파라미터로 전달하는 url 요청-->
	 			 <a href="javascript:deleteCmt('${cmt.cmtIdx}','${pno}','${bean.idx}')" style="cursor: pointer;text-decoration: none;">
	 			 	삭제
	 			 </a>
 			 </span>
 			 </c:if>
 			 </div>
 			 <div style="padding-top: 10px;">  <!-- 댓글 내용 -->
 			 	<%-- <pre>${cmt.content}<br></pre> --%> 
 			 	${content}
 			 </div>
 	</div>
 			</td>
 		</tr>
 	</c:forEach>
 </table>
 </form>
 
 <!-- modal : alert,confirm 그리고 추가적인 정보를 받을 때 사용자가 만드는 입력 상자...-->
<div id="myModal" class="modal">
<!-- Modal content : 모달 입력창-->
<div class="modal-content" >
<span class="close">&times;</span><br>  
<div style="padding: 0px 20px;">
<form action="commentAction.jsp" method="post" name="frm2">
<input type="hidden" name="mref" value="${bean.idx }"> <!-- 현재 detail로 돌아오기 위한 값 전달 -->
<input type="hidden" name="pno" value="${pno }">
<input type="hidden" name="cmtidx" value="0" >
<input type="hidden" name="func" value="3" >
</form>
</div>
</div>
</div>
<script type="text/javascript">
var modal = document.getElementById('myModal');
var span = document.getElementsByClassName("close")[0];

span.onclick = function() {
modal.style.display = "none";	//modal 화면에 안보이기	닫기 기능 구현
}
</script>
 
 
</body>