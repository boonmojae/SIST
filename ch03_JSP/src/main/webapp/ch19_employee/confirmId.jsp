<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.employee.dao.EmployeeDAO" %>
<%@ page import="kr.employee.vo.EmployeeVO" %>
<%
	//전송된 데이터 인코딩 타입 지정
	request.setCharacterEncoding("utf-8");
	//전송된 데이터 반환
	String id = request.getParameter("id");
	
	EmployeeDAO dao = EmployeeDAO.getInstance();
	EmployeeVO vo = dao.checkEmployee(id);//vo
	if(vo!=null){//아이디 중복 -vo로 바꾸기
%>
	{"result":"idDuplicated"}
<%
	}else{//아이디 미중복
%>
	{"result":"idNotFound"}
<%
	}
%>