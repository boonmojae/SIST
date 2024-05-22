<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어 사용예제</title>
</head>
<body>
<h3>표현언어의 - 파라미터 값 처리</h3><!-- 전송된 데이터 읽기 -->
<form action="s02_el.jsp" method="post">
이름 : <input type="text" name="name"><br>
<input type="submit" value="확인"/><!-- 단독태그에서 시작과 끝을 표현함/ -->
</form>
<br/>
이름은 <%= request.getParameter("name") %><br><!-- 폼을 전송하기전 메소드가 null을 반환 -->
이름은 ${param.name}<br><!-- null이 나오더라도 빈문자열로 처리 -->
이름은 ${param["name"]} 입니다.
</body>
</html>