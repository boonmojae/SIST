<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그아웃
	session.invalidate();//속성 지우고 갱신
	response.sendRedirect("main.jsp");
%>
