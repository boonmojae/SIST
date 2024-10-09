package kr.s03.operation;

public class SwitchMain03 {
	public static void main(String[] args) {
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int score;
		char grade;   //음수값.100넘어서는거 둘다 default 못함 둘중 한개만 가능
		
		System.out.print("성적 입력:");
		score = input.nextInt();
		
		if(score<0 || score>100) {
			System.out.println("성적은 0 ~ 100만 입력 가능");
			//프로그램 종료
			System.exit(0);
		}
		
		switch(score/10) {
		case 10:
			//grade = 'A'; break;   //;줄바꿈 인식돼서 break한줄에 써도 가능 10,9 값 같으니까 결과 지워도됨
		case 9:
			grade = 'A'; break;
		case 8:
			grade = 'B'; break;
		case 7:
			grade = 'C'; break;
		case 6:
			grade = 'D'; break;
		default : 
			grade = 'F';
		}
		
		System.out.println();//단순 줄바꿈
		System.out.printf("성적 : %d%n", score);
		System.out.printf("등급 : %c%n", grade);  //%d가 아니라 c
		//switch만드면 연산작업 해야돼서 if씀
		
		input.close();
		
	}
}
