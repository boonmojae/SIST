package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class DetailAction implements Action{

	@Override				//여기에 정보가 담겨있어서 getparmeter로 데이터 뽑
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//get방식으로 프라이머리키 넘어감/get은 자동인코딩
		int num = Integer.parseInt(request.getParameter("num"));//프라이머리키 데이터 뽑아냄/String으로 반환해서 integer해야됨
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO boardVO = dao.getBoard(num);//반환받아서
		//JSP공유하기 위해서 request에 저장
		request.setAttribute("boardVO", boardVO);
		//JSP 경로 반환
		return "/WEB-INF/views/detail.jsp";
	}

}
