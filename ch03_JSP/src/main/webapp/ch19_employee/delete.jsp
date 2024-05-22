<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.story.dao.StoryDAO" %>
<%@ page import="kr.story.vo.StoryVO" %>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");
	//전송된 데이터 반환
	int snum = Integer.parseInt(request.getParameter("snum"));
	
	StoryDAO dao = StoryDAO.getInstance();
	dao.delete(snum);
%>
	<script>
		alert('글 삭제를 완료했습니다.')
		location.replace('list.jsp');
	</script>