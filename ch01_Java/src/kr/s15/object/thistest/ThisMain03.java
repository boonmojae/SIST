package kr.s15.object.thistest;

public class ThisMain03 {
	//생성자
	public ThisMain03() {
		/*
		 * 생성자 내부에서 또다른 생성자를 호출할때 this()를 표시함
		 * 반복적인 코드를 제거하고 코드를 재사용하기 위해 또다른 생성자를 호출해서 동작시킴
		 */
		
		//this()전에 코드를 실행하면 오류 발생
		//System.out.println("-------");
		this("여름");
		System.out.println("~~~~~~~");
	}
	
	public ThisMain03(String msg) {
		System.out.println(msg);
	}
	
	public ThisMain03(int a) {
							//int ->String
		this(String.valueOf(a));//클래스명이 앞에있어서 valueOf는 static임
	}
	
	public static void main(String[] args) {
		ThisMain03 tm = new ThisMain03();
		ThisMain03 tm2 = new ThisMain03("겨울");
		ThisMain03 tm3 = new ThisMain03(50000);
	}
}
