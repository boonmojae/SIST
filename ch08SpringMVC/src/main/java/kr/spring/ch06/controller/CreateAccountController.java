package kr.spring.ch06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch06.validator.MemberValidator;
import kr.spring.ch06.vo.MemberVO;

@Controller
public class CreateAccountController {
	
	//유효성 체크할 경우 form:form 태그에 자바빈을 지정해야 하기 때문에 
	//폼이 호출되기전에 자바빈을 생성해서 전달 -> 자바빈 초기화 작업이라고함->request에 저장 /유효성 체크 안하면 필요없음
	//자바빈 초기화
	@ModelAttribute("command")//얘를 통해서 만들어진 자바빈을 command라는 이름으로 request에 저장
	public MemberVO initCommand() {//command속성명으로 MemberVO자바빈을 request에 저장함
		return new MemberVO();
	}
	
	//폼 호출 - 처음 폼 호출할땐 자바빈 없는데 커스텀 태그에서는 자바빈 호출하면 null됨 - 폼 호출 전에 @ModelAttribute로 자바빈 생성해야됨 
	@GetMapping("/account/create.do")//얘가 동작되면서 form에 creationForm에 있는 데이터가 전달?
	public String form() {
		return "account/creationForm";
	}
	//자바빈 생성 후 
	//폼에서 전송된 데이터 처리 - 데이터가 여기로 와서 유효성 체크
	@PostMapping("/account/create.do")
						  //속성명이 같아야돼서 @,속성명 추가  자바빈 타입/변수 /에러 정보 저장 하는 역할
	public String submit(@ModelAttribute("command")MemberVO vo,BindingResult result) {//호출되는 메서드 인자에 넣으면 전달받을 수 있음
		
		System.out.println(vo);
		
		//전송된 데이터 유효성 체크
		new MemberValidator().validate(vo,result);
		//BindingResult에 유효성 체크 결과 오류에 대한 내용이 저장되어 있으면 폼을 다시 호출함
		if(result.hasErrors()) {
			return "account/creationForm";
		}
		//에러가 없다면 호출
		return "account/created";
		
	}
}
