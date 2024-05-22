package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		
		//checkMember에 아이디 저장
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		if(member!=null) {//동일한 id 존재
			//비밀번호 일치 여부 체크
			check = member.isCheckedPassword(passwd);
			//정지회원의 경우 상태 표시
			request.setAttribute("auth", member.getAuth());
		}
		if(check) {//인증 성공
			//로그인 처리
			//jsp는 session만 하면 구해지는데 여기는 X
			HttpSession session = request.getSession();
			session.setAttribute("user_num", member.getMem_num());
			session.setAttribute("user_id", member.getId());
			session.setAttribute("user_auth", member.getAuth());
			session.setAttribute("user_photo", member.getPhoto());
			
			//메인으로 리다이렉트(클라이언트에서 호출할 수 있어야하는 주소)WEB-INF는 서버만 호출,클라이언트는 안됨/리다이렉트는 .으로
			return "redirect:/main/main.do";//redirect:식별자
		}
		//인증 실패
		return "/WEB-INF/views/member/login.jsp";
	}

}
