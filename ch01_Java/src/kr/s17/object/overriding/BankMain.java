package kr.s17.object.overriding;

import java.util.Scanner;

public class BankMain {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		//일반계좌 생성
		BankAccount ba = new BankAccount("123-456","1234","김연아",10000L);//L붙여도되고 안붙여도 됨 근데 자료형을 붙여서 인지하고 가는게 좋음
		
		//계좌정보 출력
		ba.printAccount();
		//입금하기
		System.out.print("입금:");
		long money = input.nextLong();
		ba.deposite(money);
		ba.printAccount();
		
		//출금하기
		System.out.print("출금:");
		money = input.nextLong();
		ba.withdraw(money);
		ba.printAccount();
		
		input.close();
	}
}
