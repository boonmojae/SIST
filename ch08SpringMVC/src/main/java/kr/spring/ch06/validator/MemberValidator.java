package kr.spring.ch06.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.spring.ch06.vo.MemberVO;

//유효성 체크 하려면 인터페이스 implements
public class MemberValidator implements Validator{
	
	//Validator가 검증할 수 있는 타입인지를 검사
	//자바빈 안에 담겨있는 데이터를 유효성 체크 - 단독으로 할 경우에는 스프링프레임으론 사용 불가
	@Override	//+형식
	public boolean supports(Class<?> clazz) {
		return MemberVO.class.isAssignableFrom(clazz);//유효성 체크하는게 자바빈 타입인지/발리데이터가 검증할 수 있는 타입인지
	}
	
	//유효성 체크를 수행하는 메서드
	//target : 검증하는 자바빈(VO) 객체
	//errors : 에러 정보를 담는 객체
	@Override		//+실제
	public void validate(Object target, Errors errors) {
		//target으로 전달되면 object타입을 원래 타입으로 되돌림
		MemberVO vo = (MemberVO)target;
		if(vo.getId() ==null || vo.getId().trim().isEmpty()) {
							  //필드		에러코드
			errors.rejectValue("id", "required");
						//필드(자바빈의 멤버변수), 에러코드, 에러 문구
			//errors.rejectValue("id", null, "아이디는 필수 항목");
		}
		//에러 문구 사용할때 에러코드와 문구 지정해 직접적으로 문구 쓰지 않고 에러 코드만으로 문구 읽어오게 하는거 가능
		//별도의 파일에 에러 메세지 넣고 읽어오는 방법 씀-프로퍼티 파일에 넣어서 사용
		if(vo.getName() == null || vo.getName().trim().isEmpty()) {
			errors.rejectValue("name", "required");
			//errors.rejectValue("name", null, "이름은 필수 항목");
		}
		if(vo.getZipcode() == null || vo.getZipcode().trim().isEmpty()) {
			errors.rejectValue("zipcode", "required");
			//errors.rejectValue("zipcode", null, "우편번호는 필수 항목");
		}
		if(vo.getAddress1() == null || vo.getAddress1().trim().isEmpty()) {
			errors.rejectValue("address1", "required");
			//errors.rejectValue("address1", null, "주소는 필수 항목");
		}
		if(vo.getAddress2() == null || vo.getAddress2().trim().isEmpty()) {
			errors.rejectValue("address2", "required");
			//errors.rejectValue("address2", null, "상세주소는 필수 항목");
		}
	}

}
