package kr.s14.object.statictest;

class StaticMethod{
	//인스턴스 변수
	String s1 = "행복";
	//static(클래스) 변수
	static String s2 = "행운";
	
	//static(클래스) 메서드
	public static String getString() {
		return s2;  //s1은 객체를 생성해야 메모리에 올라감||static 은 호출하는 순간 메모리에 올라감
	}
	
}
public class StaticMain02 {
	public static void main(String[] args) {
		//static 메서드는 객체 생성 없이 호출할 수 있고
		//클래스명.메서드명의 형태로 호출
		System.out.println(StaticMethod.getString());//메서드로 호출
		//static 변수는 클래스명.변수명의 형태로 호출
		System.out.println(StaticMethod.s2);//직접 호출
		//인스턴스 변수를 호출하려면 객체 생성 후
		//참조변수.변수명 형태를 호출
		StaticMethod sm = new StaticMethod() ;
		System.out.println(sm.s1); //StaticMethod.s1이건 안됨
	}
}
