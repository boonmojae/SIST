package kr.s26.exception;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ExceptionMain06 {
	/*
	 * throws 예약어의 사용
	 * 메서드에 throws 예외클래스를 명시하면 메서드내에 try~catch 블럭을 생략하고 예외가 발생하면 예외를 임시보관하고
	 * 메서드를 호출하는 곳에 try~catch블럭이 있을 경우 그 곳으로 예외를 양도함
	 */
							//임시보관하고 호출하는곳으로 보냄(25try)      throws는 메서드에만 명시
	public void printData()throws IOException,NumberFormatException{//try 없애도 thorow추가해줌  //IOE는 꼭 명시,NUM는 의무명시 X
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("단 입력:");

		//String-> int
		int dan = Integer.parseInt(br.readLine()); //한 라인에 입력한 데이터를 반환 //br.readLine()있어서 try catch해야됨
		System.out.println(dan + "단");
		System.out.println("----------");
		for(int i=0;i<=9;i++) {
			System.out.println(dan + "*" + i + "=" + dan*i);
		}

	}

	public static void main(String[] args) {
		ExceptionMain06 em = new ExceptionMain06();
		try {
			em.printData();
		}catch(IOException e) {
			System.out.println("입력시 오류 발생");
		}catch(NumberFormatException e) {
			System.out.println("숫자가 아닙니다.");
		}

	}
}
