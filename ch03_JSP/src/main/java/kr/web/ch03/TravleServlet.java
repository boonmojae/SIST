package kr.web.ch03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/travel")
public class TravleServlet extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		
		//여행지 출력시
		//[출력예시]서울,뉴욕,파리
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//Post 인코딩
		request.setCharacterEncoding("utf-8");
		
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>여행지 선택</title></head>");
		out.println("<body>");
		out.println("<h3>여행지 선택</h3>");
		//String name = request.getParameter("name"); 한번만 사용할경우 변수 생성 안해도됨
		out.println("이름 : " + request.getParameter("name") + "<br>");
		out.println("여행지 : ");
		
		String [] values = request.getParameterValues("city");
		if(values!=null) {//여행지를 선택한 경우
			
			for(int i=0;i<values.length;i++) {
				//if(i>0) out.print(",");//코드상에서만 줄바꿈 하라는 의미 (줄바꿈하려면 무조건 <br>필요함
				out.println(values[i]);
				if(i<values.length-1) out.print(",");
			}
		}else {//여행지를 선택하지 않은 경우
			out.println("선택한 여행지가 없습니다");
		}
		out.println("</body>");
		out.println("</html>");
		
		//자원 정리
		out.close();
	}
}
