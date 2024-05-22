package kr.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class FileUtil {
	//업로드 상대경로 
	private static final String UPLOAD_PATH = "/upload";
	//파일 생성
	public static String createFile(HttpServletRequest request,String param)//param = 파라미터 네임(input에 명시된)
			throws IllegalStateException,IOException,ServletException{
		//업로드 절대 경로
		String upload = request.getServletContext().getRealPath(UPLOAD_PATH);
		//파일 정보 얻기(서블릿이 파트로 반환)
		Part part = request.getPart(param);
		//파일명 구하기
		String filename = part.getSubmittedFileName();//파일이 업로드 안되면 빈문자열 반환함 그래서 DAO에서 (수정)filename.isEmpty로 조건체크
		if(!filename.isEmpty()) {
			//파일 중복 방지를 위해 임의의 값_원래 파일명 형식으로 변경
			filename = UUID.randomUUID()+"_"+filename;//중복 덮어씌우는거 방지해서 유니크 하게
			//원하는 경로에 넣기
			part.write(upload+"/"+filename);
		}
		return filename;
	}
	
	//파일 삭제
	public static void removeFile(HttpServletRequest request,String filename) {
		
		if(filename!=null) {
			//업로드 절대경로
			String upload = request.getServletContext().getRealPath(UPLOAD_PATH);//컨텍스트 경로상의 절대경로
			//삭제하려면 파일 객체로 만들어야됨
			File file = new File(upload+"/"+filename);
			//존재 여부 확인후 삭제
			if(file.exists()) file.delete();
		}
	}
}
