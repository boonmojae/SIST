package kr.s03.operation;

public class BreakMain01 {//단일 반복문
	public static void main(String[] args) {
		//break는 switch블럭을 빠져나갈 때,
		//반복문에서 특정 조건일 때 반복문을 빠져나가는 용도로 사용함
		int n =1;
		while(n<=10) {
			System.out.println(n);
			n++;  //이거 안하면 무한루프 빠짐
			if(n==8) break;//밑으로 내려서 써도 됨
		}
	}
}
