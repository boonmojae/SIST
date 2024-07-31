package kr.spring.ch07.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch07.vo.LoginVO;

public class LoginValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginVO.class.isAssignableFrom(clazz);//유효성 체크 대상이 자바빈인지 체크
	}

	@Override//여기에 정규표현식 추가해서 할 수 있음
	public void validate(Object target, Errors errors) {//Object target의 Object에 LoginVO
		LoginVO vo = (LoginVO)target;
		if(vo.getUserId() == null || vo.getUserId().trim().isEmpty()) {
								//필드		에러코드    BindingResult이 정보 가지고 있다가 커스텀 태그에 전달
			errors.rejectValue("userId", "required");
		}
		if(vo.getPassword() == null || vo.getPassword().trim().isEmpty()) {
			errors.rejectValue("password", "required");
		}
			
	}

}
