package kr.web.ch05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;//업로드할때 사용

@MultipartConfig(
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50
		)
@WebServlet("/fileMultiUpload")
public class UploadServlet2 extends HttpServlet{
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException{
		
		//컨텍스트 경로상의 파일 업로드 절대 경로 구하기
		String realFolder = request.getServletContext().getRealPath("/upload");
		
		//문서 타입 및 캐릭터셋 지정
		response.setContentType("text/html;charset=utf-8");
		
		//POST 방식으로 전송된 데이터 인코딩 타입 지정
		request.setCharacterEncoding("utf-8");
		
		//HTML 출력을 위한 출력 스트림 작성
		PrintWriter out = response.getWriter();
		try {
			out.println("작성자 : " + request.getParameter("name") + "<br>");
			out.println("제목 : " + request.getParameter("title") + "<br>");
			out.println("-------------<br>");
			
			/*
			개별적으로 처리(parameter name있어서 할 수 있음)
			Part part1 = request.getPart("uploadFile1");
			String fileName = part1.getSubmittedFileName();
			if(!fileName.isEmpty()) {
				part1.write(realFolder+"/"+fileName);			
				out.println("<img src=\"/ch03_JSP/upload/" + fileName + "\">");//앞에 \붙이면 특수문자를 일반문자로 사용할수 있음//fileName을 part1.getSubmittedFileName()로 사용할수있음
			}
			
			Part part2 = request.getPart("uploadFile2");
			String fileName2 = part2.getSubmittedFileName();
			if(!fileName2.isEmpty()) {
				part2.write(realFolder+"/"+fileName2);			
				out.println("<img src=\"/ch03_JSP/upload/" + fileName2 + "\">");
			}
			*/
			
			//복수의 정보를 묶어서
			Collection<Part> parts = request.getParts();
			for(Part p : parts) {
				String collecFileName = p.getSubmittedFileName();
				if(collecFileName!=null && !collecFileName.isEmpty()) {//null이 아니고 비어있지 않을때 진입(오류없단뜻)
					p.write(realFolder+"/"+collecFileName);//원하는 경로에 저장
					out.println("<img src=\"/ch03_JSP/upload/" + collecFileName + "\"><br>");
				}
			}
		}catch(IllegalStateException e) {
			out.println("용량 초과 : " + e.toString());
		}
		
	}
}
