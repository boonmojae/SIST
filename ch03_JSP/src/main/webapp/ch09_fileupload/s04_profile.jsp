<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로필 사진 업로드 처리</title>
</head>
<body>
<h2>저장된 프로필 사진</h2>		
<img src="/ch03_JSP/upload/<%=request.getAttribute("fileName") %>" width="500"><%-- /<%사이에 공백 있으면 주소로 인식해서 안됨--%>
</body>
</html>