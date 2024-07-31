package kr.spring.ch01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
//annotation기반으로 동작
@Controller//추가해야 모델클래스로 인식
public class HelloController {
	//요청URL과 실행 메서드 연결
	@RequestMapping("/hello.do")//"요청URL
	public ModelAndView hello() {//실행 메서드/annotation을 이용해서 hello메서드 명칭 다르게 사용해도 됨,고정X
		  //데이터		뷰
		ModelAndView mav = new ModelAndView();//데이터와 뷰에대한 정보 저장할 수 있음
		//뷰 이름 지정					prefix			suffix
		mav.setViewName("hello");// /WEB-INF/views/hello.jsp/확장자".jsp"까지 붙이면 경로에 두번 반복됨
		//뷰에서 사용할 데이터 셋팅/속성명		속성값
		mav.addObject("greeting","안녕하세요!");//디스패쳐서블릿이 request에 저장,jsp에서 el이 뽑아오면됨
		
		return mav;
	}
}
