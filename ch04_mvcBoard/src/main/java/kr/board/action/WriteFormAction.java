package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;

public class WriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {//같은 패턴으로 만들어야돼서 작업한것
		//JSP 경로 반환
		return "/WEB-INF/views/writeForm.jsp";
	}

}
