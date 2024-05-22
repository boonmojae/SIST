<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/codeMate.css" type="text/css">
</head>
<body>
<h3 class="title">나의 정보 수정</h3>
<div class="align-center">
	<form name="upForm" action="" method="post">
	<div class="align-left">
		<ul>
			<li>
				<label for="name">이름</label><br>
				<input type="text" id="name" name="name" maxlength="10" class="input-check">
			</li>
			<li>
				<label for="id">아이디</label><br>
				<input type="text" id="id" name="id" maxlength="20" class="input-check">
			</li>
			<li>
				<label for="email">이메일</label><br>
				<input type="email" id="email" name="email" maxlength="50" class="input-check">
			</li>
			<li>
				<label for="nickname">닉네임</label><br>
				<input type="text" id="nickname" name="nickname" maxlength="20" class="input-check">
			</li>
			<li>
				<label for="phone">전화번호</label><br>
				<input type="text" id="phone" name="phone" maxlength="20" class="input-check">
			</li>
		</ul>
		</div>
		<br><br><br>
		<div class="align-center">
			<input type="submit" value="저장하기" class="save_btn">
		</div>
		<div class="align-center">
		<br><br>
		<span>회원 탈퇴하기</span>
		<span>/</span>
		<span>로그아웃</span>
		</div>
	</form>
</div>

</body>
</html>