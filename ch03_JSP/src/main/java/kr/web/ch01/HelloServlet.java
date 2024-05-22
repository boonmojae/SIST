package kr.web.ch01;

import java.io.IOException;
import java.io.PrintWriter;//html태그를 문자열 형태로 출력

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//일반클래스를 서블릿으로 사용할 경우 HttpServlet를 상속시켜야 함
@WebServlet("/helloServlet")//보안 취약해서 기본 주소 막음
public class HelloServlet extends HttpServlet{//서블릿객체 생성한 다음에 두겟 호출
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Hello Servlet</title></head>");
		out.println("<body>");
		out.println("처음 작성하는 servlet 입니다.");
		out.println("</body>");
		out.println("</html>");
		
		//자원정리
		out.close();
	}
}
