package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

//ajax방식으로 삭제
public class DeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ajax통신으로 map에 정보 담아야됨
		Map<String,String> mapAjax = new HashMap<String,String>();
		//회원제 서비스로 로그인이 되어있어야됨
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			//전송된 데이터 반환
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			BoardDAO dao = BoardDAO.getInstance();
			//한건의 데이터 읽어옴-작성하지 않은 사람이  작성한것처럼 삭제 시도할 수 있기 떄문에
			BoardVO db_board = dao.getBoard(board_num);
			//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
			if(user_num!=db_board.getMem_num()) {
				mapAjax.put("result", "wrongAccess");
			}else {
				dao.deleteFile(board_num);//데이터베이스에서 업데이트해서 변경
				//파일 삭제
				FileUtil.removeFile(request, db_board.getFilename());
				
				mapAjax.put("result", "success");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
