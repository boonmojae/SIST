package kr.web.ch03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/todayMenu")//요청하면 container가 객체 생성
public class todayMenu extends HttpServlet{
	@Override			//정보 담겨있음
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//POST 방식으로 데이터 전송시 전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");//post 타입일땐 request한테 어떤 방식인지 알려줘야됨
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>점심 메뉴</title></head>");
		out.println("<body>");
		out.println("<h3>오늘 점심은</h3>");
		
		String[] values = request.getParameterValues("lunch");//lunch에 여러개가 매핑되어있어 중복될수 있기때문에 배열로 Parameter뒤에 Values추가됨
		if(values != null) {
			for(int i=0;i<values.length;i++) {
			out.println(values[i] + "<br>");//선택안한다음에 전송하면 request 안에 "lunch"없어서 values를 Null로 반환, NullPinterException발생=>조건체크 해야됨(배열이 만들어졌을때만 루프돌게)
			}
		}else {//전송된 데이터가 없는 경우
			out.println("선택한 점심 메뉴가 없습니다.");
		}
		
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
