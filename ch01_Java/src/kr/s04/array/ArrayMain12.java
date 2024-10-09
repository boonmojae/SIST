package kr.s04.array;

public class ArrayMain12 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 단을 입력 받아서 1~9까지 곱하고 결과값을 배열에 저장.
		 * 배열에 저장된 값을 읽어서 구구단을 출력 형식(2*1=2)으로 출력하시오.
		 * 
		 * 
		 * [입력 예시]
		 * 단 입력:2
		 * 
		 * [출력 예시]
		 * 2단
		 * ----
		 * 2*1=2
		 * 2*2=4
		 * 2*3=6
		 * --
		 * 2*9=18
		 */
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		System.out.print("단 입력>");
		int dan = input.nextInt();
		
		//연산의 결과값을 저장할 배열을 선언 및 생성
		int[] array = new int[9];
		
		System.out.println(dan+"단");
		System.out.println("---------");
		for(int i=0;i<array.length;i++) {
			//연산의 결과값을 배열 저장
			array[i] = dan *(i+1);  //i+1이 핵심 그냥 i면 0됨
			System.out.println(dan +"*" + (i+1) + "=" + array[i]);
		}
		
		
		input.close();
	}
}
/*
for(int i=0;i<1;i++) {
System.out.println(dan+"단");
}
for(int i=0;i<8;i++) {
System.out.println(dan + "*" + ++dan = dan*array.length);
}
*/