<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.board.dao.BoardDAO" %>
<%@ page import="kr.board.vo.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="kr.util.PagingUtil" %>
<%
	//선택한 페이지 번호(get방식으로)
	String pageNum = request.getParameter("pageNum");//PagingUtil을 사용하고 있으면 pageNum파라미터 네임 사용함
	//최초에 list.jsp를 호출하면 pageNum를 전달할 수 없기 때문에 null이 되고 
	//연산할 때 연산이 되지 않는 문제가 있어서 최초 호출시 무조건 1페이지로 설정
	if(pageNum == null){//get방식으로 처음에는 전달못하니까 1
		pageNum = "1";
	}

	//한 화면에 몇 개의 글(행,레코드)를 보여줄 지 지정
	int rowCount = 10;
	//한 화면에 몇 개의 페이지 수를 보여줄 지 지정
	int pageCount = 10;
	//현재 선택한 페이지(String->int)
	int currentPage = Integer.parseInt(pageNum);
	
	BoardDAO dao = BoardDAO.getInstance();
	int count = dao.getCount();//읽어온 count를 pagingUtil로 전달
	
	PagingUtil util = new PagingUtil(currentPage,count,rowCount,pageCount,"list.jsp");//넣는 순서가 있음-넣으면 pagingutil이 연산해줌
	
	List<BoardVO> list = null;
	if(count>0){//0이면 데이터가 없어서 호출할 필요X
		list = dao.getList(util.getStartRow(), util.getEndRow());//start,end는 PagingUtil파일에 있는 메서드 적용
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">
</head>
<body>
<div class="page-main">
	<h1>게시판 목록</h1>
	<div class="align-right">
		<input type="button" value="글 쓰기" onclick="location.href='writeForm.jsp'">
	</div>
<%
	if(count == 0){
%>
	<div class="result-display">저장된 글이 없습니다</div>
<%
	}else{
%>
	<!-- 목록 출력 시작 -->
	<table>
		<tr>
			<th>글 번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
<%
	for(BoardVO boardVO : list){
%>
	<tr>
		<td><%= boardVO.getNum() %></td>
		<td><a href="detail.jsp?num=<%= boardVO.getNum() %>"><%= boardVO.getTitle() %></a></td>
		<td><%= boardVO.getName() %></td>
		<td><%= boardVO.getReg_date() %></td>
	</tr>
<%
	}
%>
	</table>
	<!-- 목록 출력 끝 -->
	<!-- 페이지 표시 시작 -->
	<div class="align-center">
		<%= util.getPage() %>
	</div>
	<!-- 페이지 표시 끝 -->
<%
	}
%>
</div>
</body>
</html>