package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class LogoutAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//session을 바로 받을수 없고 request에서 뽑아내야됨
		HttpSession session = request.getSession();
		//로그아웃 처리
		session.invalidate();
		//메인으로 리다이렉트
		return "redirect:/main/main.do";
	}
}
