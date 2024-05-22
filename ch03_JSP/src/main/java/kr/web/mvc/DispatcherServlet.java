package kr.web.mvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		requestPro(request,response);
	}
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		requestPro(request,response);
	}

	private void requestPro(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		
		Action com = null;//만들어지는 모든 클래스의 action
		String view = null;//view=jsp경로
		
		String command = request.getRequestURI();//주소를 알아옴
		System.out.println("1 : " + command);
		if(command.indexOf(request.getContextPath())==0) {
			command = command.substring(request.getContextPath().length());//substring으로 /ch03_JSP잘라냄
			System.out.println("2 : " + command);
		}
		//web.xml-*.do		식별자
		if(command.equals("/list.do")) {
			com = new ListAction();
		}else if(command.equals("/write.do")){
			com = new WriteAction();
		}else if(command.equals("/detail.do")) {
			com = new DetailAction();
		}else if(command.equals("/update.do")) {
			com = new UpdateAction();
		}else if(command.equals("/delete.do")) {
			com = new DeleteAction();
		}
		
		try {
			//모델클래스 객체 호출
			view = com.execute(request, response);//객체 생성돼서 execute호출 가능
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//forward 방식으로 view(jsp) 호출(주소는 외부에 노출안되고 내부에서만 사용)
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);//모델클래스에 따라서 주소가 다름/view=반환되는 jsp경로를 알고있음
		dispatcher.forward(request, response);
	}
}
