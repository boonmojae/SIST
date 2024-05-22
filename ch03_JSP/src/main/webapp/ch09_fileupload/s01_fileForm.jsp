<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드</title>
</head>
<body>												
<form action="/ch03_JSP/fileUpload" method="post" enctype="multipart/form-data"><!-- get이면 파일이 있는 경로만 보내짐/파일을 올리려면 enctype명시해야됨-->
	작성자 <input type="text" name="user"><br>
	제목 <input type="text" name="title"><br>
	파일명 <input type="file" name="uploadFile"><br>
	<input type="submit" value="파일 올리기">	<!-- webapp-upload폴더 만들어서 파일 업로드(탐색기에서 보임)/src/main-kr.web.ch05에 UploadServlet 만들어서함-->
</form>
</body>
</html>