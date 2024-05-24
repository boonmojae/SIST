package kr.item.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class AdminWriteFormAction implements Action{

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
		//관리자로 로그인한 경우
		return "/WEB-INF/views/item/admin_writeForm.jsp";
	}

}
