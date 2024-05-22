package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugudan")
public class GugudanServlet extends HttpServlet{
	@Override			//전달한 데이터는 다 HSR에 저장돼서 데이터를 뽑아내야됨
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
				 //String->int 변환			String반환(문자열로 연산을 못함->int로 바꿔야됨)
		int dan = Integer.parseInt(request.getParameter("dan"));//()은 name속성과 일치시켜야됨
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>구구단</title></head>");
		out.println("<body>");
		out.println(dan + "단<br>");
		out.println("-----------------<br>");
		
		for(int i=1;i<=9;i++) {
			out.println(dan + "*" + i + "=" + dan*i + "<br>");
		}
		out.println("</body>");
		out.println("</html>");
		out.close();//생략을 해도 동작상의 문제는 없음
	}
}
