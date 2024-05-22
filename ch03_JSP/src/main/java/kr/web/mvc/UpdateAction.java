package kr.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//request에 데이터 저장
		request.setAttribute("update", "글 수정을 완료했습니다");//모델클래스에서 request에 데이터 저장->jsp에서 데이터 사용
		//JSP 경로 반환
		return "/views/update.jsp";
	}

}
