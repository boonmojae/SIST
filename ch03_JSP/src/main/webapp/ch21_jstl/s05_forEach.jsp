<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forEach 태그</title>
</head>
<body>
<h4>1부터 100까지의 홀수의 합</h4>
<c:set var="sum" value="0"/><%-- el이 sum을 읽어서 연산 --%>
<c:forEach var="i" begin="1" end="100" step="2">
	<c:set var="sum" value="${sum + i}"/>
</c:forEach>
결과 = ${sum}

<h4>구구단 : 5단</h4>
<ul>
<c:forEach var="i" begin="1" end="9">
	<li>5 * ${i} = ${5 * i}</li>
</c:forEach>
</ul>

<h4>int형 배열</h4>
<c:set var="intArray" value="<%= new int[]{10,20,30,40,50} %>"/><%-- set은 배열 생성 기능X,page영역에 저장 --%>
<c:forEach var="i" items="${intArray}" begin="2" end="4" varStatus="status"><%-- 배열에 접근하는건 el /status반복과 관련/items에 배열을 읽어와 넣어줌--%>
	<%--  인덱스				반복 회차				  처음 반복일때 true    마지막 반복일때 true/처음과 마지막은,쉼표처리할때 필요--%>
	${status.index} - ${status.count} - [${i}] - ${status.first} - ${status.last}<br>
</c:forEach>

<h4>Map</h4>
<%
	HashMap<String,String> mapData = new HashMap<String,String>();
	mapData.put("name","홍길동");
	mapData.put("job","소방관");
	mapData.put("hobby","영화감상");
%>
		<%--속성명 --%>
<c:set var="map" value="<%= mapData %>"/><%-- mapData page영역에 저장 --%>
<c:forEach var="i" items="${map}"><%--속성명을 읽어옴 --%>
	${i.key} = ${i.value}<br>
</c:forEach>
</body>
</html>