<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>if 태그</title>
</head>
<body>	<%--boolean 조건체크/true는 ()역할/el을 이용해 연산 --%>
<c:if test="true"><%-- 태그여서 {}없고 시작영역-끝영역만 있음 --%>
무조건 수행<br>
</c:if>
		    	
<c:if test="${param.name == 'dragon'}"><%-- name,dragon=문자열/el에서는 == 사용/속성값을 명시할때 ""사용하는데 dragon도 ""하면 충돌나서 ''사용을 허용--%>
name 파라미터의 값이 ${param.name}입니다.<br><%-- ?name=dragon하면 출력됨 --%>

<%-- tomcat 7버전 이상부터 equals 비교 지원 --%>
<c:if test="${param.name.equals('dragon')}"/>
name 파라미터의 값이 ${param.name}입니다.<br>
</c:if>

<c:if test="${param.age >= 20}"><%-- 숫자를 자동형변환 --%>
당신의 나이는 20세 이상입니다.
</c:if>

</body>
</html>