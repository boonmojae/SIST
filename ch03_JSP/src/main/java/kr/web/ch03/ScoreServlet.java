package kr.web.ch03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/score")
public class ScoreServlet extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		
		//성적 처리
		//국어,영어,수학,총점,평균,등급 출력
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//POST 방식으로 전송되 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>성적 처리</title></head>");
		out.println("<body>");
		int korean = Integer.parseInt(request.getParameter("korean"));
		out.println("국어 : " + korean + "<br>");
		int english = Integer.parseInt(request.getParameter("english"));
		out.println("영어 : " + english + "<br>");
		int math = Integer.parseInt(request.getParameter("math"));
		out.println("수학 : " + math + "<br>");
		//총점
		int sum = korean + english + math;
		out.println("총점 : " + sum + "<br>");
		//평균
		int avg = sum/3;
		out.println("평균 : " + avg + "<br>");
		//등급
		String grade;
		switch(avg/10) {
		case 10:
		case 9: grade = "A"; break;
		case 8: grade = "B"; break;
		case 7: grade = "C"; break;
		case 6: grade = "D"; break;
		default : grade = "F";
		}
		out.println("등급 : " + grade);
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
