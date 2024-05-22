package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class AdminUserFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//페이지 처리로 로그인O,관리자여야됨
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//관리자인지 체크
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_auth != 9) {//관리자로 로그인 하지 않은 경우
			return "/WEB-INF/views/common/notice.jsp";//forward방식으로 호출
		}
		
		//전송된 데이터 받아 get에
		int mem_num = Integer.parseInt(request.getParameter("mem_num"));
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.getMember(mem_num);
		
		request.setAttribute("member", member);
		//JSP 경로 반환
		return "/WEB-INF/views/member/detailUserForm.jsp";
	}

}
