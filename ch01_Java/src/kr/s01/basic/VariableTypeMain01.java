package kr.s01.basic;

public class VariableTypeMain01 {
	public static void main(String[] arge) {
		System.out.println("===논리형===");
		//논리형(true,false)
		boolean b = true;
		System.out.println("b =" + b);
		
		System.out.println("===문자형===");
		//문자형, 크기 : 2byte=16bit, 표현범위 : 0~65,535
		//다국어 처리를 위한 유니코드(unicode) 방식
		char c1 = 'A';//내부적으로 아스키코드값 65가 2byte로 변수 c1에 담기는것
		System.out.println("c1 = " + c1);
		
		char c2 = 65;//A에 해당되는 아스키코드값 65를 직접 대임
		System.out.println("c2 = " + c2);
		
		//A를 의미하는 유니코드 |u로 유니코드임을 알림, ''를 넣음
		char c3 = '\u0041';
		System.out.println("c3 = " +c3);
		
		char c4 ='자';
		System.out.println("c4 = " + c4);
		
		char c5 = '\uc790'; //자를 의미하는 유니코드
		System.out.println("c5 = " + c5);
		
		System.out.println("===정수형===");
		//byte,크기 : 1byte, 표현범위 : -128~127
		byte b1 = 127;  //입출력할때 씀
		System.out.println("b1 = " + b1);
		
		//short, 크기 : 2byte, 표현범위 : -32,768~32,767;  거의안씀
		short s1 = 32767;
		System.out.println("s1 = " + s1);
		
		//int, 크기 : 4byte, 표현범위 : -2,147,483,648~2,147,483,647
		int in =1234;
		System.out.println("in = " + in);
		
		//long, 크기 : 8byte
		long lg =1234L;//소문자l써도 되는데 숫자 1이랑 비슷해서 대문자L로 쓰기
		System.out.println("lg = " + lg);
		
		//System.out.println(23);  int로 인식함 23L로해야 long으로 인식
		//int=정수표현의 기본
		
		System.out.println("===실수형===");
		//float, 크기 : 4byte
		float f1 = 9.1F;//F,f를 안적으면 double데이터로 인식
		System.out.println("f1 = " + f1);
		//double, 크기 : 8byte
		double du = 9.8;
		System.out.println("du = " + du);
		
		//System.out.println(3.14); -> 3.14f해야 float로 인식 아니면 기본 double로 인식됨
		//정수는 int   실수는 double
		
		System.out.println("===문자열 표시===");
		//문자열 표시(기본자료형이 아님, 참조자료형)
		String str = "Hello World!!";  //String S무조건 대문자
		System.out.println("str = " + str);
		
		
	}
}
