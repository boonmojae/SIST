package kr.s03.operation;

public class WhileMain02 {
	public static void main(String[] args) {
		int sum=0, su = 1;
			  //조건식
		while(su <= 100) {
			//누적
			sum +=su;
			//증감식
			su++;   //증감식 없으면 무한루프에 빠짐
		}
		System.out.println("1부터 100까지의 합 : " + sum);
	}
}
