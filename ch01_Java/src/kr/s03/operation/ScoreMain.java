package kr.s03.operation;

public class ScoreMain {
	public static void main(String[] args) {
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		int korean,english,math,sum;
		char grade;
		float avg;
		//단순하게 만들경우 문구 못 띄움 너무 복잡해짐
		do {  //걸러내는 역할
			System.out.print("국어 :");
			korean = input.nextInt();
		}while(korean < 0 || korean > 100);
		
		do {
			System.out.print("영어 :");
			english = input.nextInt();
		}while(english < 0 || english > 100);
		
		do {
			System.out.print("수학 :");
			math = input.nextInt();
		}while(math < 0 || math > 100);
		
		//총점 구하기
		sum = korean +english + math;
		//평균 구하기
		avg = sum/3.0f;    //3f넣으면 정수로 나와서 . 추가
		//등급 구하기
		 //캐스트 연산자,연산의 결과
		switch((int)avg/10) {
		case 10:
		case 9:
			grade ='A'; break;
		case 8:
			grade ='B'; break;
		case 7:
			grade = 'C'; break;
		case 6:
			grade = 'D'; break;
		default :
			grade = 'E';  //0~59
		}
		
		System.out.print("\n");//단순 줄바꿈
		System.out.printf("총점 = %d%n", sum);
		System.out.printf("평균 = %.2f%n", avg);
		System.out.printf("등급 = %c학점", grade);
		
		input.close();
		
	}
}
