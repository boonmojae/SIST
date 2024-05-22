package kr.web.ch02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
	
		/*
		 * [실습]
		 * 두 개의 숫자 전송되면 전송된 두 개의 숫자를 더해서 결과를 구하시오.
		 * [출력예시]
		 * 5 + 2 = 7
		 */
		
		
		//전송된 데이터 반환
		int num1 = Integer.parseInt(request.getParameter("num1"));//(실수)더블로 변환하고 싶으면 double.parseDouble
		int num2 = Integer.parseInt(request.getParameter("num2"));
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>덧셈하기</title></head>");
		out.println("<body>");
		//out.println(num1 + " + " + num2 + " = " + (num1+num2) );//이미 숫자로 바꿔줘서 Number안붙여도됨 ()만/최우선 연산자로 먼저 계산되게
		out.printf("%d + %d = %d",num1,num2,num1+num2);
		out.println("</body>");
		out.println("</html>");
		//자원 정리
		out.close();
	}
}
