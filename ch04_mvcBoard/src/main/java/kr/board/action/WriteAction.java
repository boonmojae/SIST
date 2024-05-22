package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class WriteAction implements Action{

	@Override				//전송된 데이터 담겨있음-전송된 데이터 꺼낼때 인코딩 지정 먼저 해야됨
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//자바빈(VO) 객체 생성(jsp아니고 class라 usebean사용불가)
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle(request.getParameter("title"));
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd"));
		boardVO.setEmail(request.getParameter("email"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setIp(request.getRemoteAddr());
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insert(boardVO);
		//JSP 경로 반환
		return "/WEB-INF/views/write.jsp";//jsp는 데이터 처리X ui작업만(데이터는 모델클래스인 Action에서 처리함)/경로를 반환하면 forward로 write.jsp
	}

}
