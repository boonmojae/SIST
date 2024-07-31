<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 상단 시작 -->
<h2 class="align-center"><a href="${pageContext.request.contextPath}/main/main">SpringPage</a></h2>
<div class="align-right">
	<c:if test="${!empty user}">
	<a href="${pageContext.request.contextPath}/member/myPage">MY페이지</a>
	<img src="${pageContext.request.contextPath}/member/photoView" width="25" height="25" class="my-photo">
	<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a>
	</c:if>
	<c:if test="${empty user}"><!-- 필요한걸 자바빈에 넣고 자바빈 통채로 session에 저장 -->
	
	<a href="${pageContext.request.contextPath}/member/registerUser">회원가입</a><!-- 확장자가 없는 상태로 만듦 -->
	<a href="${pageContext.request.contextPath}/member/login">로그인</a>
	</c:if>
	
	
	<a href="${pageContext.request.contextPath}/main/main">홈으로</a>
	<a href="${pageContext.request.contextPath}/board/list">게시판</a>
</div>
<!-- 상단 끝 -->