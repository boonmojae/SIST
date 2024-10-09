package kr.s18.object.poly;

//부모클래스
class Parent{
	int a = 100;
}
//자식클래스
class child extends Parent{
	int b = 200;
}

public class PolyMain01 {
	public static void main(String[] args) {
		child ch = new child();//Child를 참조자료형이라 부름
		System.out.println(ch.a);
		System.out.println(ch.b);
		//ch는 객체를 참조하는 주소 있음 ||주소가 p에 들어감 
		Parent p = ch;//자식클래스타임>부모클래스 타입 형변환
					  //업캐스팅, 자동적으로 형변환
		System.out.println(p.a);
		//호출 범위를 벗어나서 호출 불가능
		//System.out.println(p.b);
		
		child ch2 = (child)p; //부모클래스타임>자식클래스타입 형변환
								//다운캐스팅, 명시적으로 형변환
		System.out.println(ch2.a);
		System.out.println(ch2.b);
	}
}
