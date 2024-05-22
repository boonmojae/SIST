<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int snum = Integer.parseInt(request.getParameter("snum"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 삭제</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
<script type="text/javascript">
window.onload=function(){
	const myForm = document.getElementById('delete_form');
		//이벤트 연결
		myForm.onsubmit=function(){
			alert('해당글을 삭제합니다');
		};
	};

</script>
</head>
<body>
<div class="page-main">
	<h1>글 삭제</h1>
	<form id="delete_form" action="delete.jsp" method="post">
		<input type="hidden" name="snum" value="<%= snum %>"><!-- 글번호가 보이면 수정하니까 안보이게 처리 -->
		<div class="align-center">
			<input type="submit" value="글 삭제">
			<input type="button" value="목록" onclick="location.href='list.jsp'">
		</div>
	</form>
</div>
</body>
</html>