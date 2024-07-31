package kr.spring.ch09.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class MemberVO {//어노테이션을 넣었다고 다 동작되는게 아님/유효성 체크는 가능한데 Binding이랑 연계를 해야됨
	//정규표현식으로 패턴 검사
	@Pattern(regexp="^[0-9a-zA-Z]+$")//+로 1개 이상은 명시하게 표시
	private String id;
	//문자열의 길이 지정
	@Size(min=4,max=10)
	private String password;
	@NotEmpty//호출할때 위에있는거 import하기
	private String name;
	@Email//어노테이션은 때에 따라 1개 이상 사용함
	@NotEmpty//문자열에만 사용 가능,숫자X
	private String email;
	//숫자데이터의 길이 지정
	@Range(min=1,max=200)//만 명시하면 0이 기본으로 나오고 공백을 넣으면 영어 에러(NumberFormetException)글이 뜸/typeMismatch도 항상 세트로 추가해야됨
	private Integer age;//기본(0이 나옴)->참조자료형으로 (set,get)변경 ->칸에 0 나오는거 사라짐/근데 문제는 아무것도 입력안한 상태에서 전송하면 에러 메세지 안뜸(ex300예외조건에 벗어나는걸 적어야 에러메세지 뜸)
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", age=" + age
				+ "]";
	}
	
}
