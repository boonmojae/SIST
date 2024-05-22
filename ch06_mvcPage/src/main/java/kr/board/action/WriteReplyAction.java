package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardReplyVO;
import kr.controller.Action;

public class WriteReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//JSON문자열을 담기위해 MAP을 만듦
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인이 된 경우-post타입으로 전송
			request.setCharacterEncoding("utf-8");
			//전송된 데이터를 자바빈에 담아줌->자바빈 생성
			//자바빈(VO)를 생성해서 전송된 데이터 저장
			BoardReplyVO reply = new BoardReplyVO();
			reply.setMem_num(user_num);//댓글 작성자 회원번호 세션에서 뽑아냄
			reply.setRe_content(request.getParameter("re_content"));
			reply.setRe_ip(request.getRemoteAddr());
			reply.setBoard_num(Integer.parseInt(request.getParameter("board_num")));//댓글의 부모 글번호
			
			BoardDAO dao = BoardDAO.getInstance();
			dao.insertReplyBoard(reply);
			
			mapAjax.put("result", "success");
		}
		
		//JSON 데이터로 변환
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);//문자열이 만들어짐
		//문자열 사용할수 있게 저장
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";//DAO->Action->detail.jsp(ajax하러)
	}

}
