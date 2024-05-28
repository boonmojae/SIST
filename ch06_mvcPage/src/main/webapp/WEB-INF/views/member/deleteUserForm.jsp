<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원탈퇴</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/share.css" type="text/css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css" type="text/css">
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(function(){
	//아이디,비밀번호 유효성 체크
	$('#delete_form').submit(function(){
		const items = document.querySelectorAll('.input-check');
		for(let i=0;i<items.length;i++){
			if(items[i].value.trim()==''){
				const label = document.querySelector('label[for="'+items[i].id+'"]');
				alert(label.textContent + '항목은 필수 입력');
				items[i].value = '';
				items[i].focus();
				return false;
			}
		}//end of for
});
</script>
</head>
<body>
<div id="login_logo">
			<a href="${pageContext.request.contextPath}/main/main.do" class="logo"> 
			<img id="logo_pic_login" src="${pageContext.request.contextPath}/images/로고1.png" height="80" width="80"> CODEMATE
			</a>
		</div>
		
		<div class="login_main">
		<form id="login_form" action="login.do" method="post">
			<ul id="insert">
				<li class="floating-label">
					<input type="text" class="form-input" placeholder="아이디" 
					name="id" id="id" maxlength="12" autocomplete="off">
					<label for="id"></label>
				</li>
				<li class="floating-label">
					<input type="password" class="form-input" placeholder="비밀번호" 
					name="passwd" id="passwd" maxlength="12">
					<label for="passwd"></label>
				</li>
			</ul>
			<div class="align-center">
				<input id="login_btn" type="submit" value="회원탈퇴">
				<input id="cancel_btn" type="button" value="홈으로" onclick="window.location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</form>	
	</div>
</body>
</html>