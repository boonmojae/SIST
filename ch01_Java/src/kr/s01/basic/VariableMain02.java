package kr.s01.basic;

public class VariableMain02 {
	public static void main(String[] arge) {
		/*
		 * [실습]
		 * 정수를 담을 수 있는 변수를 3개 지정한다.
		 * 변수명은 각각 a,b,c,라고 지정하고 원하는 정수로 초기화 한다.
		 * a + b 연신 후 출력할때
		 * 
		 * [출력 예시]
		 * 결과 = 50 형식으로 출력한다.
		 * 
		 * c에 저장된 데이터를 80으로 변경해서
		 * 
		 * c = 80형식으로 출력한다.
		 */
		
		int a = 20;   //int a =20, b =30, c =50;
		int b = 30;
		int c = 50;
		System.out.println("결과 = "+(a+b));
		System.out.printf("결과 = %d%n", a+b);   //printf 일땐 소괄호 안해도 계산됨
		
		c =80;
		System.out.println("c = " +c);
		
		System.out.printf("c = %d%n", c);
		
		
		
	}
}
