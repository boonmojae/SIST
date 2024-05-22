<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>functions 라이브러리</title>
</head>
<body>
<c:set var="str1" value=" Functions <태그>를 사용합니다. "/><%-- trim사용하려고 앞뒤 공백 --%>
<c:set var="str2" value="사용"/>
<c:set var="str3" value="10,20,30-40,50,60,70,80.90,100"/>

문자열의 길이:${fn:length(str1)}<br><%-- 함수형태여서 str을 인자로 전달/접두사fn명시 --%>
대문자로 변경:${fn:toUpperCase(str1)}<br>
소문자로 변경:${fn:toLowerCase(str1)}<br>
인덱스 3 ~ 6전까지 문자열 추출:${fn:substring(str1,3,6)}<br>
문자열에서 원래 문자를 지정한 문자로 대체:${fn:replace(str1," ","-")}<br><%-- old->new --%>
문자열의 앞뒤 공백 제거:${fn:trim(str1)}<br>
지정한 문자의 인덱스 구하기:${fn:indexOf(str1,str2)}<br><%--1에서 2가 위치하고 있는지 --%>
문자열에서 지정한 문자를 구분자로 문자열을 잘라내기:${fn:split(str3,',')}<br><%-- forEach로 루프돌면서 해야됨/function라이브는 split을 여러개 사용할 수 있음(forTokens사용하는게 더 편함)--%>
<c:forEach var="array" items="${fn:split(str3,',-.')}">
	${array}<br>
</c:forEach>
</body>
</html>