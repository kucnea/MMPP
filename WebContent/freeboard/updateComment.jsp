<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <form action="updateComment.do">
    <input type="hidden" name="cmtidx" value="${cmtidx }">
    <input type="hidden" name="userId" value="${userId }">
    <table>
    <tr>
    <th width="20%"></th><th width="80%">수정 칸</th>
    </tr>
    <tr>
    <th>내용</th><td><textarea name="newContent" cols="30" rows="10">${content }</textarea></td>
    </tr>
    <tr>
    <th colspan="2"><input type="submit" value="저장"><input type="button" value="닫기" onclick="self.close()"></th>
    </tr>
    </table>
    </form>