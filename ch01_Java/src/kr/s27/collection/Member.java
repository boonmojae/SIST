package kr.s27.collection;

public class Member {
	private String name;//이름
	private int age;//나이
	private String job;//직업
	private String address;//주소
	private String phone;//전화번호
	
	public Member() {}//인자없는 생성자
	
	public Member(String name, int age, String job, String address, String phone) {//인자 있음
		this.name = name;
		this.age = age;
		this.job = job;
		this.address = address;
		this.phone = phone;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
