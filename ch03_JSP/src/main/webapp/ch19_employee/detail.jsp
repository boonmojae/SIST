<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%
	int snum = Integer.parseInt(request.getParameter("snum"));
	StoryDAO dao = StoryDAO.getInstance();
	StoryVO vo = dao.getStory(snum);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글 상세</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body><!-- 글번호,작성자,제목,내용,작성일 -->
<div class="page-main">
	<h1>게시판 글 상세</h1>
	<ul>
		<li>글번호 : <%= vo.getSnum() %></li>
		<li>작성자 : <%= vo.getId() %></li>
		<li>제목 : <%= vo.getTitle() %></li>
	</ul>
	<hr size="1" width="100%" noshade="noshade">

	<p>
		<%= vo.getContent() %>
	</p>
	<div class="align-right">
		작성일 : <%= vo.getReg_date() %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	//로그인 한 사원 번호와 작성자의 사원 번호가 일치하는지 체크
	if(user_num!=null && user_num == vo.getNum()){
%>
		<input type="button" value="수정" onclick="location.href='updateForm.jsp?snum=<%= vo.getSnum() %>'">
		<input type="button" value="삭제" onclick="location.href='deleteForm.jsp?snum=<%= vo.getSnum() %>'">
<%
	}
%>
		<input type="button" value="목록" onclick="location.href='list.jsp'">
	</div>

</div>
</body>
</html>