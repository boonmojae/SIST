package kr.spring.ch05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.ch05.service.SearchService;
import kr.spring.ch05.vo.SearchVO;

@Controller
public class GameSearchController {
	
	//의존관계로 프로퍼티 생성
	@Autowired		//넣어서 타입체크
	private SearchService searchService;//searchService가 주소를 가지고 있음
	
	@RequestMapping("/search/main.do")//메인에서 get방식으로 검색
	public String main() {
				 //문자열로 뷰 이름 지정
		return "search/main";
	}
	
	//serachService작성후
	@RequestMapping("/search/game.do")//타입,query가 전달되면 자바빈에 담아 여기에 전달
	public ModelAndView search(@ModelAttribute ("vo")SearchVO searchVO) {//속성명 없어됨,속성명 없으면 @도 없어도 가능
		System.out.println("검색어 = " + searchVO.getQuery());
		
		String result = searchService.search(searchVO);//result = 검색완료
		//데이터가 있어서 ModelAndView 세팅해서 같이 반환
		ModelAndView mav = new ModelAndView();
		//뷰 이름 지정
		mav.setViewName("search/game");
		//뷰에서 사용할 데이터 저장 - request에 저장
		mav.addObject("searchResult",result);
		
		return mav;
	}
	
}
