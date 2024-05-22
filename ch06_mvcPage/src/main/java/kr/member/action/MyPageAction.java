package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class MyPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();//세션을 읽어온 후
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우
		//회원정보
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(user_num);//위에 존재하는 user_num의 한건의 데이터 읽어옴
		
		//관심 게시물 정보
		BoardDAO boardDAO = BoardDAO.getInstance();
		//list 반환
		List<BoardVO> boardList = boardDAO.getListBoardFav(1, 5, user_num);
		
		request.setAttribute("member", member);
		request.setAttribute("boardList", boardList);
		//읽어왔으니까 저장
		request.setAttribute("member", member);
		//JSP 경로 반환
		return "/WEB-INF/views/member/myPage.jsp";
	}

}
