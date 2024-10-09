package kr.s12.object.constructor;

public class AccountMain {

	String accountNo; //계좌번호
	String ownerName; //예금주
	int balance; //잔고

	//생성자가 클래스명이랑 일치해야됨
	public AccountMain(String a, String o, int b) {
		accountNo = a;
		ownerName = o;
		balance = b;
		System.out.println(ownerName +"님 계좌가 개설되었습니다.");
	}

	//예금하기
	public void deposite(int amount) {		//여기서 다중if문을 사용할지 return할지||void일때만 return사용
		if(amount <= 0) {
			System.out.println("0보다 크게 입력해야 합니다.");
			return; //void형 메서드에서 특정 조건일 때 메서드를 빠져나감(메서드 종료)  return을 사용해서 else가 필요없음
		}
		balance += amount;
		System.out.println("입금이 완료되었습니다.");
	}

	//출금하기
	public void withdraw(int amount) {
		if(amount <= 0) {
			System.out.println("0보다 크게 입력해야 합니다.");
			return;
		}
		if(balance < amount) {
			System.out.println("잔고가 부족합니다.");
			return;
		}
		balance -= amount;
		System.out.println("출금이 완료되었습니다.");
	}
	public static void main(String[] args) {
		//계좌 생성
		AccountMain account1 = new AccountMain("123-456","홍길동",10000);
		System.out.println("계좌번호 :"+account1.accountNo);
		System.out.println("예금주 :"+account1.ownerName);
		System.out.printf("잔고 : %,d원%n", account1.balance);
		System.out.println("------------------------------");
		
		//예금 하기
		account1.deposite(5000);
		//출금 하기
		account1.withdraw(10000);
		
		System.out.println("계좌번호 :"+account1.accountNo);
		System.out.println("예금주 :"+account1.ownerName);
		System.out.printf("잔고 : %,d원%n", account1.balance);
	}
}
