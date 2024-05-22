<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 폼</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
<script type="text/javascript">

</script>
</head>
<body>
<div class="page-main">
	<h1>로그인</h1>
	<form action="login.jsp" method="post" id="login_form">
		<ul>
			<li>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" maxlength="10">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input type="password" name="passwd" id="passwd" maxlength="10">
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="로그인">
			<input type="button" value="홈으로" onclick="location.href='main.jsp'">
		</div>
	</form>
</div>
</body>
</html>