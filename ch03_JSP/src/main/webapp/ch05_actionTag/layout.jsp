<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 모듈화</title>
<style type="text/css">
table{
	width:500px;
	margin:0 auto;
	border:solid 1px #000;
	border-collapse:collapse;
}
td{
	border:solid 1px #000;
	text-align:center;
	vertical-align:middle;
	height:40px;
}
td.td-middle{
	text-align:left;
	vertical-align:top;
	height:200px;
}
td.td-width{
	width:100px;
}
</style>
</head>
<body>
<!-- html공유하지만 변수공유 못함 -->
<table>
	<tr>
		<td colspan="2">
		<%-- include 태그의 내용으로 html 주석, jsp 주석 전부 사용 불가 --%>
		<!-- 최초의 access전에 인코딩 타입 지정해야됨 -->
		<jsp:include page="/ch05_actionTag/module/top.jsp">
			<jsp:param value="국제상사" name="company"/>
		</jsp:include>
		</td>
	</tr>
	<tr>
		<td class="td-middle td-width">
			<jsp:include page="/ch05_actionTag/module/left.jsp"/>
		</td>
		<td class="td-middle">
		<!-- 내용 시작 -->
		레이아웃1
		<!-- 내용 끝 -->
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<jsp:include page="/ch05_actionTag/module/bottom.jsp"/><!-- 단독태그형식으로 쓰고싶으면 "> 사이에 /넣기 -->
		</td>
	</tr>
</table>
</body>
</html>