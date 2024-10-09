package kr.s02.operator;

public class OperatorMain04 {
	public static void main(String[] args) {
		/*
		 * [숙제]
		 * pinos71@daum.net 보내주세요
		 * 
		 * 534자루의 연필을 30명의 학생들에게 똑같은 개수로 나누어 줄때 학생당 몇 개를 가질 수 있고, 최종적으로 몇 개가 남는지를 구하시오.
		 * 
		 * [출력 예시]
		 * 학생 한 명이 가지는 연필수 : 17
		 * 남은 연필수 : 24
		 * 
		 */
		
		
		//System.out.println(534 / 30); //17	
		//System.out.println(534 % 30); //24
		
		int pen = 17, cil = 24;
		System.out.printf("학생 한 명이 가지는 연필수 : %d%n", pen);
		System.out.printf("남은 연필수 : %d%n", cil);
		
		System.out.println("------------------------");
		
		
		
		System.out.println("학생 한 명이 가지는 연필수 : " + pen);
		System.out.println("남은 연필수 : " + cil);
		
		int p = 534;
		int c = 30;
		System.out.println("------------------------");
		System.out.printf("학생 한 명이 가지는 연필수 : %d%n",(p/c));
		System.out.printf("남은 연필수 : %d%n",(p%c));
		
	}
}
