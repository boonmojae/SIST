<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%
Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){//로그인이 되지 않은 경우
		response.sendRedirect("loginForm.jsp");
	}else{
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");

%>
<jsp:useBean id="vo" class="kr.story.vo.StoryVO"></jsp:useBean>
<jsp:setProperty property="*" name="vo"/>
<%
	//작성자
	vo.setNum(user_num);
	//클라이언트의 ip 주소 저장
	vo.setIp(request.getRemoteAddr());
	
	StoryDAO dao = StoryDAO.getInstance();
	dao.insert(vo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 쓰기 완료</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h1>글 쓰기 완료</h1>
	<div class="result-display">
		<div class="align-center">
			게시판에 글을 등록했습니다.<p>
			<button onclick="location.href='list.jsp'">목록</button>
		</div>
	</div>
</div>
</body>
</html>
<%
	}
%>