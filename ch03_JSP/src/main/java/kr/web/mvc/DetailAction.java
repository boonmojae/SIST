package kr.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request에 데이터 저장		속성명			속성값
		request.setAttribute("detail", "글 상세페이지입니다.");
		
		//JSP경로 반환
		return "/views/detail.jsp";
	}

}
