package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정-post 방식으로 전송
		request.setCharacterEncoding("utf-8");
		//자바빈(VO) 생성-전송된걸 담아줌
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(Integer.parseInt(request.getParameter("num")));//num변경 못함
		boardVO.setTitle(request.getParameter("title"));
		boardVO.setName(request.getParameter("name"));
		boardVO.setPasswd(request.getParameter("passwd"));//변경할 수 없는데 전송되어져서 담아줌
		boardVO.setEmail(request.getParameter("email"));
		boardVO.setContent(request.getParameter("content"));
		boardVO.setIp(request.getRemoteAddr());
		
		//데이터를 넣으면 인증작업 해야됨
		BoardDAO dao = BoardDAO.getInstance();
		//비밀번호 인증을 위해 한 건의 레코드를 자바빈(VO)에 담아서 반환
		BoardVO db_board = dao.getBoard(boardVO.getNum());//num을 넣어줌으로썬 한건의 데이터를 반환받는데 비밀번호가 담겨있음
		boolean check = false;
		if(db_board!=null) {
			//비밀번호 일치 여부 체크
			check = db_board.isCheckedPassword(boardVO.getPasswd());
		}
		if(check) {//비밀번호 일치=true
			dao.update(boardVO);
			//상세페이지로 이동하기 위해 글번호 저장
			request.setAttribute("num", boardVO.getNum());
		}
		
		request.setAttribute("check", check);//check=false 비밀번호 틀렸을때 다시 폼으로 되돌아가는 기능을 사용하려고 저장
		
		//JSP 경로 반환
		return "/WEB-INF/views/modify.jsp";
	}

}
