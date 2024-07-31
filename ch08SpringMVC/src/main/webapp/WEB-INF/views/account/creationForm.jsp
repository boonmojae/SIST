<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- 유효성체크때 사용하는 커스텀 태그 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>							<!-- 자바빈 명시해야됨 - 읽어옴 -->
<form:form action="create.do" modelAttribute="command"><!-- 일반 form은 명시안하면 get/form:form은 method 명시안하면 post로 인식 -->
<!-- 커스텀태그는 끝나는걸 명확하게 해야됨//input type="text" name="id" id="id" value="dragon"로 인식/자바빈에 담겨있는것까지 읽음,사용하는 자바빈 request에 담기는것까지 명시해야됨-->
	<!-- 읽어온 자바빈의 필드 -->
	아이디 : <form:input path="id"/><!-- id가 자바빈의 필드 -->
		   <form:errors path="id"/><!-- 에러가 발생할때 span태그가 보여짐 -->
	<br>
	이름 : <form:input path="name"/>
	      <form:errors path="name"/>
	<br>
	우편번호 : <form:input path="zipcode"/>
			<form:errors path="zipcode"/>
	<br>
	주소 : <form:input path="address1"/>
		 <form:errors path="address1"/>
	<br>
	상세주소 : <form:input path="address2"/>
			<form:errors path="address2"/>
	<br>
	<form:button>전송</form:button>
</form:form>
</body>
</html>