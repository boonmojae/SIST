package kr.s26.exception;

public class ExceptionMain03 {
	public static void main(String[] args) {
		int var = 50;
		//예외처리
		//다중 catch문
		//예외가 발생하면 예외객체가 전달되는 catch블럭으로 이동해서 수행문을 실행
		try {				//String->int변환
			int data = Integer.parseInt(args[0]);
			System.out.println(var/data);
			/*
			 * (주의)다중catct문을 사용할 떄 Exception과 하위 예외클래스를 동시에 명시할 때 하위 예외클래스를 먼저 명시하고 가장 뒤에
			 * Exception을 명시해야 동작상의 문제가 발생하지 않음
			 */
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("입력한 데이터가 없습니다.");
		}catch(NumberFormatException e) {//argument에 오십
			System.out.println("숫자가 아닙니다.");
		}catch(ArithmeticException e) {
			System.out.println("0으로 나눌 수 없습니다.");
		}catch(Exception e) {//Exception을 맨앞에 넣으면 예외발생 ||Array,Num,Arit가 e에 들어가서 나머지 필요없다고 알려주는거
			System.out.println("나머지 예외는 여기로");
		}
		System.out.println("프로그램 종료");
		
	}
}
