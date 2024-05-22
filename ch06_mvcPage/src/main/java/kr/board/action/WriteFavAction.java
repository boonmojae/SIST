package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardFavVO;
import kr.controller.Action;

public class WriteFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ajax해서 로그인 돼 있는지 확인해야됨
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//전송된 데이터 인코딩 타입 지정
			request.setCharacterEncoding("utf-8");
			//전송된 데이터 반환-board_num값만 받으면됨/로그인 되어있으면 user_num값이 있어서
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			
			BoardFavVO favVO = new BoardFavVO();
			favVO.setBoard_num(board_num);
			favVO.setMem_num(user_num);//로그인 돼 있으면 session에서 뽑아낼수 있음
			
			BoardDAO dao = BoardDAO.getInstance();
			//좋아요 등록 여부 체크
			BoardFavVO db_fav = dao.selectFav(favVO);
			if(db_fav!=null) {//좋아요 등록 O
				//토글형태로 좋아요 삭제
				dao.deleteFav(db_fav);//자기자신 삭제
				mapAjax.put("status", "nofav");
			}else {//좋아요 등록 X
				//좋아요 등록
				dao.insertFav(favVO);//db_fav넣으면 안되고 위에 만들어놓은거 넣어줌
				mapAjax.put("status", "yesFav");
			}
			mapAjax.put("result", "success");
			mapAjax.put("count", dao.selectFavCount(board_num));
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);//mapAjax를 넘기면 문자열 반환
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
