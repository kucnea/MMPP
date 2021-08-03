<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% ServletContext sc = this.getServletContext(); %>   

<style>
div{
 background : black;
 color : white;
}
</style>
<div id="bottomMessage">
<b><p style="text-align : right;">2021 COPYRIGHT @ LMH ALL RIGHTS RESERVED | 문의 : <%= sc.getInitParameter("adminEmail") %></p></b>
</div>