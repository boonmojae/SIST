package kr.spring.ch09.controller;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.spring.ch09.vo.MemberVO;

@Controller
public class MemberWriteController {
	//유효성 체크를 위한 자바빈(VO) 초기화
	//자바빈 초기화는 새로운 자바빈 객체를 폼 호출 전에 미리 생성하여 뷰에 전달하는 것
	//이렇게 하면 폼을 처음 호출할 때 빈 객체를 뷰에 전달하여 입력 양식을 채울 수 있음
	@ModelAttribute("command")
	public MemberVO initCommand() {
		return new MemberVO();
		
	}
	//폼 호출
	@GetMapping("/member/write.do")
	public String form() {
		return "member/write";
	}
	//폼에서 전송된 데이터 처리
	@PostMapping("/member/write.do")					//에러정보를 Binding에 담아야되는데 @Valid가 없으면 VO와 BindingResult 연계안돼서 에러 인식 못함.추가하기
	public String submit(@ModelAttribute("command") @Valid MemberVO vo,BindingResult result) {
		
		System.out.println("전송된 데이터 : " + vo);
		//유효성 체크 결과 오류가 있으면 폼을 호출
		if(result.hasErrors()) {
			return "member/write";
		}
		
		return "redirect:/index.jsp";//유효성 체크에 걸리지 않았다면(정상적)
	}
	//입력필수가 아니면 없어도됨/필수면 넣기
	//숫자일 경우 Integer로 변환했을때 사용
	//null인 경우 빈 문자열로 변환시키는 작업 typeMismatch가 동작되게
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		//true이면 요청 파라미터의 값이 null이거나 빈 문자열("")일 때 변환 처리를 하지 않고 null 값으로 할당.
		//false이면 변환 과정에서 에러가 발생하고 에러코드로 "typeMismatch"가 추가 됨
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, false));
	}
	
}
