package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class CheckDuplicatedIdAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);//null아니면 중복,null이면 중복
		
		//json라이브러리 lib에 넣기-jackson이 가지고 있는 객체 메서드로 json문자열 만들수 있음
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		if(member == null) {//아이디 미중복
			mapAjax.put("result", "idNotFound");
		}else {//아이디 중복
			mapAjax.put("result", "idDuplicated");
		}
		
		/*
		 *  JSON형식으로 변환하기를 원하는 문자열을 HASHMAP에 key와 value의 쌍으로 저장한 후 
		 *  ObjectMapper의 writerValueAsString에 Map 객체를 전달해서 일반 문자열 데이터를 JSON 형식의 문자열 데이터로 변환 후 반환
		 */
		
		ObjectMapper mapper = new ObjectMapper();
		//JSON 문자열 반환 -메서드로 문자열 반환
		String ajaxData = mapper.writeValueAsString(mapAjax);
		//클라이언트 JSP에 전달할 문자열 저장
		request.setAttribute("ajaxData", ajaxData);//forward된 페이지에서 ajaxData호출
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
