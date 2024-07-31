package kr.spring.ch03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.ch03.service.NewArticleService;
import kr.spring.ch03.vo.NewArticleVO;

@Controller//이게 없으면 컨트롤러로 인식 못함
public class NewArticleController {
	@Autowired					//직접 여기다 넣을수 없음 ->set만들어야되는데 @Autowired를 필드에 넣으면 자동으로 만들어줘서 굳이 만들필요없음
	private NewArticleService newArticleService;//주소가 저장됨
	
	
	//폼을 호출하는 메서드
	//Get 요청이 들어올때 호출 - @GetMapping는 get,post둘다 받음
	@GetMapping("/article/newArticle.do")//요청URI
	public String form() {//호출하는 메서드 /뷰이름만 반환하는거면 ModelAndView보다 문자열 권장
					//뷰 이름 지정
		return "article/newArticleForm";//전송하면 dispatcherServlet이 뷰이름으로 생각함
	}
	
	//같은주소여도 @GET,POST하면 충돌나지 않고 호출할 수 있음
	
	/*
	 * @ModelAttribute 어노테이션을 이용해서 전송된 데이터를 자바빈에 담기
	 * [기능]
	 * 1.자바빈(VO) 생성
	 * 2.전송된 데이터를 자바빈에 저장
	 * 3.View에서 사용할 수 있도록 request에 자바빈(VO)를 저장 
	 * [형식]
	 * 1.@ModelAttribute(속성명) NewArticleVO vo 지정한 속성명으로 JSP에서 request에 접근해서 자바빈(VO) 호출 가능
	 * 	 예) ${속성명.title}
	 * 2.@ModelAttribute를 명시할 때 속성명을 생략할 수 있음
	 * 	 속성명을 생략하면 클래스명의 첫 글자를 소문자로 속성명을 자동 생성
	 * 	 예)ModelAttribute NewArticleVO vo
	 * 	 ${newArticleVO.title}
	 * 3.@ModelAttribute 생략
	 * 	 호출 메서드에 인자명만 명시
	 * 	 예)NewArticleVO vo와 같이 인자명만 명시
	 * 	 request에 저장되는 속성명은 newArticleVO로 저장됨
	 */
	
	//POST 요청이 들어올 때 호출 - 요청URI같음
	@PostMapping("/article/newArticle.do")
														   //속성명 지정해서 command로 request에 담긴 자바빈에 접근할 수 있음
	public String submit(NewArticleVO vo) {//@ModelAttribute ("command") NewArticleVO vo
		
		newArticleService.writeArticle(vo);//얘가 동작하려면 컨테이너에 넣어야됨->servlet-context로 이동
						//여기서 el이 request - command에 접근해 데이터 사용함
		return "article/newArticleSumitted";
	}
	
}
