package kr.s19.object.finaltest;

class A{//final은 변수,메서드,클래스에 적용할수있음
	//멤버 필드(는 변수와 상수 존재)
	final int NUM = 10;//상수  || 객체생성 후 호출
	public static final int NUMBER = 20;//static한 상수||호출만하면 메모리에 올라감(객체생성 필요없음),제일 많이 사용
}

public class FinalMain01 {
	public static void main(String[] args) {
		A ap = new A();
		//상수는 변경 불가능
		//ap.NUM = 20;//상수는 변경이 불가||일반적으로 대문자로 지정,근데 소문자도 있음
		System.out.println(ap.NUM);
		
		//static 상수 호출
		System.out.println(A.NUMBER);
		
		final int NO = 30;
		System.out.println(NO);//지역적 상수(메서드 내에서만 사용,잘안씀)
	}
}
