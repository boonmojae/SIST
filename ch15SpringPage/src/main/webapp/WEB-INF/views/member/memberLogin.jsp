<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- 회원로그인 시작 -->
<div class="page-main">
	<h2>로그인</h2>							<!-- memberController에서 자바빈 초기화 한거 넣어줌 -->
	<form:form action="login" id="member_login" modelAttribute="memberVO">
	<form:errors element="div" cssClass="error-color"/><!-- 필드가 없는 에러메세지(아이디,비밀번호 불일치를 위한) -->
	<ul>
		<li class="floating-label">
			<form:input path="id" placeholder="아이디" autocomplete="off" cssClass="form-input"/><!-- 힌트 기능/autocomplete자동완성 안되게 -->
			<form:label path="id">아이디</form:label><!-- path="id"-> for="id로 바뀜/실제로 아이디가 안보여서 위에 placeholder에 아이디 적음 -->
			<form:errors path="id" cssClass="error-color"/>
		</li>
		<li class="floating-label">
			<form:password path="passwd" placeholder="비밀번호" cssClass="form-input"/><!-- form-input넣어서 floating-label적용되게 -->
			<form:label path="passwd">비밀번호</form:label>
			<form:errors path="passwd" cssClass="error-color"/>
		</li>
		<li>
			<label for="auto"><input type="checkbox" name="auto" id="auto">로그인상태유지</label>
		</li>
	</ul>
	<div>
		<form:button class="login-btn default-btn">로그인</form:button>
	</div>
	</form:form>
	<p class="align-center">
		<input type="button" value="홈으로" class="default-btn" onclick="location.href='${pageContext.request.contextPath}/main/main'">
		<input type="button" value="비밀번호찾기" onclick="location.href='sendPassword'" class="default-btn">	
	</p>
</div>
<!-- 회원로그인 끝 -->