package kr.s02.operator;

public class OperatorMain15 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 3개의 정수를 입력받아서 최대값,최소값을 출력하는 프로그램을 작성하시오.
		 * 
		 * [입력 예시]
		 * 첫번째 정수:10
		 * 두번째 정수:5
		 * 세번째 정수:7
		 * 
		 * [출력 예시]
		 * 최대값: 10
		 * 최소값: 5
		 * 
		 */
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int max; //최대값 변수
		int min;
		 
		System.out.print("첫번째 정수:");
		int a = input.nextInt();
		
		System.out.print("두번째 정수:");
		int b = input.nextInt();
		
		System.out.print("세번째 정수:");
		int c = input.nextInt();
		
		//최대값 구하기
		max = a > b ? a : b;
		max = max > c ? max :c;
		System.out.printf("최대값 : %d%n", max);
		
		//최소값 구하기
		min = a < b ? a : b;
		min = min < c ? min : c;
		System.out.printf("최소값 : %d%n", min);
		
		
		/*System.out.println("최대값:" + max);
		System.out.println("최소값:" + small);

		int b =10,s =5;
		
		int max;
		int small;
		
		max = a > b ? a : b;
		small = b < s ? b : s;*/
		input.close();
		
		//집가서 최소값 5 나오게 해보기
	}

}
