package kr.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {//Action저장 안되면 에러남(연동하는 파일 둘 다 저장해야됨)
		
		//request에 데이터 저장
		request.setAttribute("message", "목록 페이지입니다.");
		//JSP 경로 반환
		return "/views/list.jsp";
	}

}
