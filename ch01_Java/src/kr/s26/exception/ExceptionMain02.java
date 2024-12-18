package kr.s26.exception;

public class ExceptionMain02 {
	public static void main(String[] args) {
		int[] array = {10,20,30};
		for(int i=0;i<=array.length;i++) {
			//예외처리
			//예외가 발생해도 정상 종료될 수 있도록 프로그램적으로 처리하는 것을 말함
			try {
				//예외가 발생할 가능성이 있는 코드를 명시
				//만약 예외가 발생하면 예외가 발생한 코드의 실행을 멈추고 catch블럭으로 이동
				System.out.println("array["+i+"]:" + array[i]);
			}catch(ArrayIndexOutOfBoundsException e) {//예외객체가 인자에 들어감
				System.out.println("없는 인덱스 호출");
			}
		}//end of for
		System.out.println("프로그램 끝");
	}
}
