package kr.s06.object.instance;

public class InstansMain {
	//클래스의 기본 구조
	//멤버 필드(속성)
	int varl; //멤버 변수	
	String var2; //멤버 변수
	
	//생성자, 생략 가능하며 생략하면 컴파일시 자동 생성
	public InstansMain() {}	//객체 생성시 호출됨
	
	//멤버 메서드(동작)
	public int sum(int a, int b) { 	//동작  a,b=인자 ()함수 안으로 들어가는 입구
		return a + b; //return은 출구
	}
	public static void main(String[] args) {
		//객체 선언 및 생성
		InstansMain im = new InstansMain();//new InstansMain()호출함
		//객체의 멤버 변수에 값을 할당
		im.varl = 100;
		im.var2 = "서울";
		//객체의 멤버 변수 값 호출
		System.out.println(im.varl + "," + im.var2);
		
		//객체의 멤버 메서드 호출 |메서드는 함수를 뜻함
		int result = im.sum(10, 20);//연산된 결과가 return해 result로 들어감   13번 int값에 10,20이 들어감
		System.out.println("result =" + result);
		
	}
	
}
