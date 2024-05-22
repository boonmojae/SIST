package kr.web.ch04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lifeCycle")
public class LifeCycle extends HttpServlet{
	int initCnt = 0;
	int serviceCnt = 0;
	int destroyCnt = 0;
	
	@Override
	public void init()throws ServletException{//최초에 한번 요청
		System.out.println("init 메서드는 첫 요청만 호출됨 : " + (++initCnt));//데이터 베이스 연동
							
	}
	@Override
	public void service(HttpServletRequest request,HttpServletResponse response)//service는 get/post 둘다 받을 수 있음
		throws ServletException,IOException{
		System.out.println("service 메서드는 요청할 때마다 호출됨 : " + (++serviceCnt));//화면 읽어오고 뿌리기(HTML만드는건 여기서)
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		//HTML 출력을 위한 출력 스트림 생성
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Servlet Life Cycle</title><head>");
		out.println("<body>");
		out.println("서블릿 init() 메서드 호출 횟수 : " + initCnt + "<br>");
		out.println("서블릿 Service() 메서드 호출 횟수 : " + serviceCnt);
		out.println("</body>");
		out.println("</html>");
	}
	@Override
	public void destroy() {//소멸되기 직전으로 화면에 보여지게 할 수 없음/인자로 넘어오는게 없어서 HTML 생성할수 있는 메서드는 service만 가능(request,response)가지고 있어서
		System.out.println("destroy 메서드는 본 Servlet이 더 이상 사용되지 않을 때만 호출됨" + (++destroyCnt));//자원정리 로 응용해서 사용할 수 있음
	}
}
