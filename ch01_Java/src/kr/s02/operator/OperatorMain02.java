package kr.s02.operator;

public class OperatorMain02 {
	public static void main(String[] args) {
		System.out.println("===산술연산자===");
		
		System.out.println(1 + 1);
		System.out.println(10 * 3);
		System.out.println(10 / 3); //몫 구하기   정수를 정수로 나누면 정수가 나옴 (실수 안나옴)
		System.out.println(10 % 3); //나머지 구하기
		
		System.out.println(8 /3); //2
		System.out.println(8 %3); //2
		
		System.out.println("---------");
					   //double  int -> double 자동적으로 형변환
		System.out.println(10.0 /3);  //10.0double로 인식
					   //double  int ->double 자동적으로 형변환
		System.out.println(2.4 * 4); //9.6
		
		
		
		
	}
}