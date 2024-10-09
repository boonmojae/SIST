package kr.s02.operator;

public class OperatorMain06 {//문자열   변수
	public static void main(String[] args) {
		System.out.println("===비교(관계)연산자===");
		boolean result;
		int a = 10;
		double b = 10.5;
		
		result = a < b; //=대입연산자 <비교연산자   int a가 자동적으로 double형변환 
		System.out.println("a < b :" +result);
		
		result = a > b;
		System.out.println("a > b :" +result);
		
		result = a >=b;  //>= 크거나 같다 or 의 의미
		System.out.println("a >=b :" +result);
		
		result = a <= b;
		System.out.println("a <= b :" +result);
		
		result = a ==b;  //=대입,==비교
		System.out.println("a ==b :" + result);
		
		result = a != b;
		System.out.println("a != b :" + result);
		
		
		
	}

}
