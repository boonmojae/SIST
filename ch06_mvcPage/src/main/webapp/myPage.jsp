<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 정보 수정</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/codeMate.css" type="text/css">
</head>
<body>
<div class="float-A">
 	<div class="myCount">
   		<a href="a">나의 정보</a><br><br><br>
   		<a>My 코메</a><br><br>
   		<a>참여중인 팀</a><br><br>
   		<a>나의 코메 신청</a><br><br>
   		<a>나의 코메 모집</a><br><br>
   		<a>북마크</a><br><br><br>
   		<hr style="border-color: #8f8f8f; border-width: 1px;"><br><br>
   		<a>나의 활동</a><br><br>
   		<a>쪽지</a><br><br>
   		<a>내가 쓴 글</a><br><br>
   		<a>내가 쓴 댓글</a>
	</div>
</div>		
		
		
		
<br><br><br>
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
</body>
</html>