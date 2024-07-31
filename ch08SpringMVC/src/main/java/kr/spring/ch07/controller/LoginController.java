package kr.spring.ch07.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch07.service.LoginCheckException;
import kr.spring.ch07.service.LoginService;
import kr.spring.ch07.validator.LoginValidator;
import kr.spring.ch07.vo.LoginVO;

@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	//유효성 체크를 위한 자바빈 초기화(체크 안하면 초기화 안해도됨)
	@ModelAttribute//자바빈 초기화 용도면 반드시 있어야됨
	public LoginVO initCommand() {//스프링에선 자바빈을 command객체 라고 부름 initCommand는 초기화
		return new LoginVO();//반환하면 request에 저장/속성명 지장 안하면 loginVO자동 지정
	}

	//폼 호출
	@GetMapping("/login/login.do")
	public String form() {
		return "login/form";
	}
	
	//폼에서 전송된 데이터 처리
	@PostMapping("/login/login.do")//BindingResult 유효성 검사 결과를 저장하는 객체
	public String submit(LoginVO loginVO,BindingResult result) {//@ModelAttribute 생략
		
		//유효성 체크
		new LoginValidator().validate(loginVO, result);
		//유효성 체크 결과 오류가 있으면 폼을 다시 호출
		if(result.hasErrors()) {
			return form();//위에 폼을 다시 호출
		}
		
		//유효성 체크 후 정상적으로 userId,password넘어오면
		//로그인 체크 
		try {
			loginService.checkLogin(loginVO);
			//로그인 성공(했을시 redirect하기)
			return "redirect:/index.jsp";//반환하는 뷰 경로에 redirect:있으면 forward아니고 redirect
		}catch(LoginCheckException e) {
			//로그인 실패
			//메시지 처리			에러코드
			result.reject("invalidIdOrPassword");//필드가 없기때문에 reject사용
			return form();
		}
	}
	
}
