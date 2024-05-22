<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 4개 기본객체와 영역</title>
</head>
<body>
page 영역의 msg1 = <%= pageContext.getAttribute("msg1") %><br>
request 영역의 msg2 = <%= request.getAttribute("msg2") %><br>
session 영역의 msg3 = <%= session.getAttribute("msg3") %><br><!-- 공유범위가 넓어 공유됨/두 페이지가 하나의 세션 공유 -->
<!-- 
	기본객체 pageContext	request		session		application
	영역	  page		<	request	 <  session	 <  application(웹어플리케이션당 한개만 생성)
	일반 						일반데이터저장은 session까지만, application는 일반저장X,환경설정 정보공유O
 -->
</body>
</html>