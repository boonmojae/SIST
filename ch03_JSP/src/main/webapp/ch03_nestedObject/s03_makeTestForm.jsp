<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>폼</title>
</head>
<body>
<form action="s04_viewParameter.jsp" method="post">
	이름 : <input type="text" name="name" size="10"><br><!-- 입력안하고 전송하면 text는 빈문자열 /전송 자체가 안돼야 null-->
	주소 : <input type="text" name="address" size="30"><br>
	좋아하는 동물 : 
	<input type="checkbox" name="pet" value="dog">강아지<!-- checkbox는 null(+select,radio) -->
	<input type="checkbox" name="pet" value="cat">고양이
	<input type="checkbox" name="pet" value="pig">돼지
	<br>
	<input type="submit" value="전송">
</form>
</body>
</html>