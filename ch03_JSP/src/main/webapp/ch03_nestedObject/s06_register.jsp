<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//POST방식으로 전송시 전송된 데이터의 인코딩 타입 지정(body에서 해도됨)
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
이름 : <%=request.getParameter("name") %><br>
아이디 : <%=request.getParameter("id") %><br>
비밀번호 : <%=request.getParameter("password") %><br>
전화번호 : <%=request.getParameter("phone") %><br>

<%
	String [] hobbies = request.getParameterValues("hobby");
	if(hobbies != null){//취미 선택을 안하면 출력이 안되게
		
%>
취미 :		
<%
	for(int i=0;i<hobbies.length;i++){
		if(i>0) out.print(",");
%>
			<%= hobbies[i]%>
<%
		}
		out.println("<br>");
	}
%>


자기소개 : <br><%=request.getParameter("content") %>
</body>
</html>