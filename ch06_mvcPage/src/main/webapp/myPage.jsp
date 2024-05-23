<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/codeMate.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/side.css" type="text/css">
</head>
<body>
<!-- 헤더 -->
<!-- 사이드바 -->
<div class="float-A">
 	<div class="myCount">
   		<a href="a" class="sideB_font">나의 정보</a>
   		<a class="sideB_font">My 코메</a>
   		<a href="a">참여중인 팀</a>
   		<a href="a">나의 코메 신청</a>
   		<a href="a">나의 코메 모집</a>
   		<a href="a">북마크</a>
   		<a class="sideB_font">나의 활동</a>
   		<a href="a">쪽지</a>
   		<a href="a">내가 쓴 글</a>
   		<a href="a">내가 쓴 댓글</a>
	</div>
</div>	
<!-- 사이드바 끝 -->
<!-- 메인 정보 수정 -->
<br><br><br>
<div class="float-B">
<div class="align-center">
    <form name="upForm" action="" method="post" class="upForm">
    <h3 class="title">나의 정보 수정</h3>
	<br><br>
        <div>
            <ul>
                <li>
                    <label for="name" class="red-text">이름</label><br>
                    <input type="text" id="name" name="name" maxlength="10" class="input-check">
                </li>
                <li>
                    <label for="id" class="blue-text">아이디</label><br>
                    <input type="text" id="id" name="id" maxlength="20" class="input-check">
                </li>
                <li>
                    <label for="email" class="green-text">이메일</label><br>
                    <input type="email" id="email" name="email" maxlength="50" class="input-check">
                </li>
                <li>
                    <label for="nickname" class="gray-text">닉네임</label><br>
                    <input type="text" id="nickname" name="nickname" maxlength="20" class="input-check">
                </li>
                <li>
                    <label for="phone" class="default-text">전화번호</label><br>
                    <input type="text" id="phone" name="phone" maxlength="20" class="input-check">
                </li>
            </ul>
        </div>
        <br>
        <div class="align-center margin-bottom-large">
            <input type="submit" value="저장하기" class="save_btn">
        </div>
        <div class="small_font">
            <br><br>
            <span>회원 탈퇴하기</span>
            <span>/</span>
            <span>로그아웃</span>
        </div>
    </form>
</div>
</div>
<!-- 메인 정보 수정 끝 -->
</body>
</html>