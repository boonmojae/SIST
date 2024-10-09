package kr.s03.operation;

public class SwitchMain04 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 두 개의 정수와 연산자를 입력한 후 연산의 결과를 출력 하시오.
		 * 
		 * [입력 예시]
		 * 첫번째 수: 10
		 * 연산자:+
		 * 두번째 수:20
		 * 
		 * [출력 예시]
		 * 10 + 20 = 30
		 */
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		int out = 0;//연산의 결과값을 저장
		
		System.out.print("첫번째 수: ");
		int first = input.nextInt();
		
		System.out.print("연산자:");
		String operator = input.next();
		
		System.out.print("두번째 수: ");
		int second = input.nextInt();
		
		
		switch(operator) {
		case "+":
			out = first + second; break;
		case "-":
			out = first - second; break;
		case "*":
			out = first * second; break;
		case "/":
			if(second!=0) {
				out = first / second; break;
			}else {
				System.out.println("0으로 나눌 수 없습니다.");
				//프로그램 종료
				System.exit(0);
			}
			out = first / second; break;
		case "%":
			if(second!=0) {
				out = first % second; break;
			}else {
				System.out.println("0으로 나눌 수 없습니다.");
			}
			out = first & second; break;
		default:
			System.out.println("잘못된 연산자 입력");
			//프로그램 종료
		System.exit(0);
													//out = first + second; break;
		}
		
		System.out.println();//단순 줄바꿈
		System.out.printf("%d %s %d = %d", first, operator, second, out);
		
		input.close();
	}
}
