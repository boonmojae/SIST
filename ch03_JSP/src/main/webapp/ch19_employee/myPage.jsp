<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	Integer user_num = (Integer)session.getAttribute("user_num");
	if(user_num==null){//로그인 되지 않은 경우(수정,삭제,글쓰기 꼭 로그인해야될 경우에는 if조건 넣기)
		response.sendRedirect("loginForm.jsp");
	}else{//로그인 된 경우
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원상세정보</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<%
	EmployeeDAO dao = EmployeeDAO.getInstance();
	EmployeeVO vo = dao.getEmployee(user_num);
%>
<div class="page-main">
	<h1>사원정보</h1>
	<ul>
		<li>아이디 : <%= vo.getId() %></li>
		<li>이름 : <%= vo.getName() %></li>
		<li>급여: <%= String.format("%,d원",vo.getSalary()) %></li>
		<li>업무 : <%= vo.getJob() %></li>
		<li>가입일 : <%= vo.getReg_date() %></li>
	</ul>
	<hr size="1" width="100%" noshade="noshade">
	<div class="align-right">
		<input type="button" value="회원정보수정" onclick="location.href='modifyUserForm.jsp'">
		<input type="button" value="회원탈퇴" onclick="location.href='deleteUserForm.jsp'">
		<input type="button" value="홈으로" onclick="location.href='main.jsp'">
	</div>
</div>
</body>
</html>
<%
	}
%>