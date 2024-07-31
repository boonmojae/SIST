package kr.spring.ch02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	/*
	 * @RequestParam 어노테이션은 HTTP 요청 파라미터를 메서드의 파라미터로 전달
	 * [형식] 
	 * 1.@RequestParam(요청파라미터네임) 메서드의 인자(파라미터)		+<query=서울>여기의 query가 20"query"와 같아야됨
	 * 2.요청파라미터명과 호출메서드의 인자명이 같으면 요청파라미터명 생략 가능
	 * 	 @RequestParam 메서드의 인자명
	 *   요청파라미터를 필수적으로 사용하지 않으면 오류 발생		+ex)?query=서울
	 *   아래와 같이 required는 false로 지정하면 요청파라미터가 없어도 오류가 발생하지 않음
	 *   @RequestParam(value="query",required=false)	+필수가 아니라면 false
	 *   @RequestParam(required=false)
	 * 3.@RequestParam 생략 가능
	 *   요청파라미터명과 호출메서드의 인자명을 동일하게 표시
	 *   요청파라미터를 필수적으로 사용하지 않아도 오류가 발생하지 않음 	+?query=부산없다면 query = null로 출력
	 */
	
	//요청 URL과 실행 메서드 연결
	//요청할때 사용되는 annotation이
	@RequestMapping("/search/internal.do")//파라미터 데이터 전달오는걸 annotation으로 처리
	public ModelAndView searchInternal(String query) {
		
		System.out.println("query = " + query);//콘솔에 출력
		
		/*
		 * ModelAndView mav = new ModelAndView(); //뷰 이름 지정
		 * mav.setViewName("search/internal"); //전송하는 데이터 X
		 */		
			//new붙여서 객체 생성			//뷰 이름 지정
		return new ModelAndView("search/internal");
	}
	
	//int사용하면 defaultValue지정하는게 좋음
	@RequestMapping("/search/external.do")//기본자료형이 아닌 null값을 받을수 있게하려면 참조자료형 Integer pageNumber로 처리/값이 없을때 null로 에러가 안남,근데 defaultValue사용하는게 좋음
	public ModelAndView searchExternal(String query,@RequestParam(value="p",defaultValue="1")int pageNumber) {//파라미터,인자명 다르면 써야됨
		System.out.println("query = " + query);
		System.out.println("p = " + pageNumber);
									//뷰 이름
		return new ModelAndView("search/external");
	}
}
