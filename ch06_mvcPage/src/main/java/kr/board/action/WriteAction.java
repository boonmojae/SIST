package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//페이지 처리로 로그인O
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우
		//전송된 데이터 인코딩 타입 지정(post방식으로 전송)
		request.setCharacterEncoding("utf-8");
		//자바빈(VO)을 생성한 후 전송된 데이터를 저장
		BoardVO board = new BoardVO();
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setIp(request.getRemoteAddr());
		board.setFilename(FileUtil.createFile(request, "filename"));//업로드를 하면 이름을 바꿔서 set에 보내줌,업로드 안되면 빈문자열 대입됨 ->디비에선 null로 인식됨/상황에 따라 데이터를 받는게 다름
		board.setMem_num(user_num);//작성자의 회원번호
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insertBoard(board);
		
		request.setAttribute("notice_msg", "글 쓰기 완료");
		request.setAttribute("notice_url", request.getContextPath()+"/board/list.do" );//모델클래스에서 전송하는 방식으로 uri가 가장 안전
		//JSP 경로 반환
		return "/WEB-INF/views/common/alert_view.jsp";
	}

}
