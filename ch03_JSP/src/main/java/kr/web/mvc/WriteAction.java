package kr.web.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//request에 데이터 저장	   속성명
		request.setAttribute("insert", "글 등록 완료");//request에 저장하면 JSP에서 가져다 사용할 수 있음
		
		//JSP 경로 반환
		return "/views/write.jsp";//모델클래스 - JSP 1:1/이 경로에서 insert 읽어가면 글 등록완료를 출력함
	}

}
