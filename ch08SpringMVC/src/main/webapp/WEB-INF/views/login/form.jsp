<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form:form action="login.do" modelAttribute="loginVO">
	<!-- 필드가 없는 에러메시지를 처리하기 위해서 명시 -->
	<form:errors element="div"/><!-- 필드가 없어서 patt없음/span태그여도 element=div하면 줄바꿈됨-->
	아이디 : <form:input path="userId"/><%-- 자바빈에 있는 프로퍼티명 --%>
		   <form:errors path="userId"/>
	<br>
	비밀번호 : <form:password path="password"/>
			<form:errors path="password"/>
	<br>
	<form:button>전송</form:button>
</form:form>
</body>
</html>