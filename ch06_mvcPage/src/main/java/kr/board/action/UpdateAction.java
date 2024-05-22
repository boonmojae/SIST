package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인이 된 경우(post방식으로 전송)
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		//수정전 데이터
		BoardVO db_board = dao.getBoard(board_num);
		//로그인한 회원 번호와 작성자 회원 번호 일치 여부 체크
		if(user_num != db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 불일치	forward방식으로 처리
			return "/WEB-INF/views/common/notice.jsp";
		}
		//로그인한 회원번호와 작성자 회원번호 일치
		BoardVO board = new BoardVO();
		board.setBoard_num(board_num);
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setIp(request.getRemoteAddr());
		board.setFilename(FileUtil.createFile(request, "filename"));
		
		dao.updateBoard(board);//수정작업 한 후 옛날 파일을 지우는 조건체크
		if(board.getFilename()!=null && !"".equals(board.getFilename())) {//꼭 데이터가 있어야됨
			//새 파일로 교체할 때 원래 파일 제거
			FileUtil.removeFile(request, db_board.getFilename());
		}
		
		return "redirect:/board/detail.do?board_num="+board_num;//="여기 사이에 공백이 있으면 board_num 값으로 인식함
	}

}
