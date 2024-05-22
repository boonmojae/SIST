<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="member" class="kr.web.member.MemberVO"/><!-- import할 필요없고 풀네임으로만 명시/단독태그 사용가능/member가 참조변수 역할-->
<jsp:setProperty name="member" property="*"/><!-- *=모든프로퍼티를 세팅(id,name...)/프로퍼티와 파라미터 이름이 같아야 자동매핑 할 수 있음(*사용가능)-->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
</head>
<body>
아이디 : <jsp:getProperty property="id" name="member"/><br><!-- set만 * 할 수 있고 get은 개별적으로 데이터 읽어와서 출력 -->
비밀번호 : <jsp:getProperty property="password" name="member"/><br>
이름 : <jsp:getProperty property="name" name="member"/><br>
이메일 : <jsp:getProperty property="email" name="member"/><br>
주소 : <jsp:getProperty property="address" name="member"/>
</body>
</html>