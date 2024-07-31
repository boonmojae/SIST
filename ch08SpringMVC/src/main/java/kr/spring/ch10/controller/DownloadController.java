package kr.spring.ch10.controller;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DownloadController {
	@RequestMapping("/file.do")//컨텍스트(프로그램의 시작) 경로상의 절대경로를 알아내야됨
	public ModelAndView download(HttpSession session) {//Session을 읽어온 이유= servletConext읽어오려고
		///file.txt의 컨텍스트 경로상의 절대 경로를 구하기
		String path = session.getServletContext().getRealPath("/WEB-INF/file.txt");//session.getServletContext()하면 servletContext경로 알려줌
		//뷰에 전달해 해당 뷰에서 stream으로 바꿔 클라이언트로 보내야됨
		File downloadFile = new File(path);//경로가 담겨진 path를 파일객체 만들어 보냄
							//뷰 이름(호출하기 위해)/속성명(파일객체를 알아내기 위해?)/속성값
		return new ModelAndView("download","downloadFile",downloadFile);//ModelAndView 객체를 생성할 때 사용된 뷰 이름이 servlet-context.xml 파일에서 정의된 id 값과 일치해야됨
	}
}
