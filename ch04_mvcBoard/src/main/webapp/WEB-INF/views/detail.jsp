<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h2>글 상세</h2>
	<ul>
		<li>글번호 : ${boardVO.num}</li><%--자바빈에 getNum이 동작하면서 데이터 읽어옴/.num=프로퍼티 --%>
		<li>제목 : ${boardVO.title}</li>
		<li>작성자 : ${boardVO.name}</li>
		<li>이메일 : ${boardVO.email}</li>
	</ul>
	<hr size="1" noshade="noshade" width="100%">
	<p>
		${boardVO.content}
	</p>
	<hr size="1" noshade="noshade" width="100%">
	<div>
		작성일 : ${boardVO.reg_date}
		<input type="button" value="수정" onclick="location.href='modifyForm.do?num=${boardVO.num}'"><%-- 프라이머리키 보내야돼서?num(el방식) --%>
		<input type="button" value="삭제" onclick="location.href='deleteForm.do?num=${boardVO.num}'">
		<input type="button" value="목록" onclick="location.href='list.do?num=${boardVO.num}'">
	</div>
</div>
</body>
</html>