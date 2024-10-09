package kr.s25.object.inner;

class Outer{
	//Outer의 멤버 변수
	int x = 100;
	//내부클래스 (멤버 내부클래스)
	class Inner{
		//Inner의 멤버 변수
		int y = 200;
	}
}


public class MemberMain01 {
	public static void main(String[] args) {
		Outer ot = new Outer();
		//Outer 내부클래스인 Inner클래스를 객체 생성
		Outer.Inner oi = ot.new Inner();//아우터밑에 이너있다 oi = ...
		
		//Outer의 멤버변수 호출
		System.out.println(ot.x);
		//Inner의 멤버변수 호출
		System.out.println(oi.y);
		
	}
}
