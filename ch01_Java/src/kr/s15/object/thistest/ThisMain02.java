package kr.s15.object.thistest;

class ThisTest{
	//은닉화
	private int a; //a는 멤버 변수
	//캡슐화				//지역변수
	public void setA(int a) {
		//멤버 변수 =지역변수
		this.a = a;//지역변수로 인식
	}
	public int getA() {
		return a;
	}
}

public class ThisMain02 {
	public static void main(String[] args) {
		ThisTest tt = new ThisTest();
		tt.setA(10);
		System.out.println(tt.getA());
	}
}
