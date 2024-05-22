package kr.member.vo;

import java.sql.Date;

//자바빈,VO(Value Object)
public class MemberVO {
	//프로퍼티(form에 있는 파라미터 네임과 동일해야 매핑됨)-내부에서 실행가능해서 외부에서 할 수 있게 public한 메서드 만듦/vo데이터를 dao로 전달
	private int num;
	private String id;
	private String name;
	private String passwd;
	private String email;
	private String phone;
	private Date reg_date;
	
	//비밀번호 체크
	public boolean isCheckedPassword(String userPasswd) {
		if(passwd.equals(userPasswd)) {//true=비밀번호 일치
			return true;
		}
		return false;//비밀번호 불일치 else만들지 않고 바로 false
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
	
}
