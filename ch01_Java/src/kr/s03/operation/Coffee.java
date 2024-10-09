package kr.s03.operation;

public class Coffee {
	public static void main(String[] args) {
		/*[실습]
		 * 동전을 넣고 커피를 주문하면 커피를 판매하는 자판기 프로그램
		 * 자판기는 자판기 보유 동전(1000), 커피(10), 프림(10), 설탕(10)
		 * 을 보유하고 있고 커피를 주문할 때 마다 자판기 보유 동전-거스름돈,
		 * 커피 5, 프림 3, 설탕 1 씩 차감되고 0이 되면 판매를 중단함.
		 * 
		 * [입력 및 출력 예시]
		 * 동전을 넣으세요(커피값:400):500
		 * 거스름돈 : 100
		 * 맛 좋은 커피가 준비되었습니다.
		 * 
		 * or
		 * 
		 * 동전을 넣으세요(커피값:400):300
		 * (고객이 투입한 동전이 커피값보다 작으면) 투입한 동전이 부족합니다.
		 * 
		 * or
		 * 
		 * 동전을 넣으세요(커피값:400):2000
		 * (자판기 보유 동전이 부족하면) 거스름돈이 부족합니다.
		 * 
		 * 동전을 넣으세요(커피값:400):500
		 * (커피가 부족하면) 커피가 부족합니다.
		 * (프림이 부족하면) 프림이 부족합니다.
		 * (설탕이 부족하면) 설탕이 부족합니다.
		 * 
		 * ====현재 자판기 정보====
		 * 커피 : 5
		 * 프림 : 7
		 * 설탕 : 1
		 * 자판기 보유 동전 금액 : 900원
		 * 투입한 동전 금액 : 500원
		 * ===================
		 */
		
		java.util.Scanner input = new java.util.Scanner(System.in);
		//커피 정보
		int price = 400;
		//커피 한 잔에 들어갈 양
		int content_coffee =5;
		int content_cream =3;
		int content_sugar = 1;
		
		//커피에 들어갈 재료의 현재 보유량
		int coffee = 10;
		int cream =10;
		int sugar = 10;
		
		//투입한 금액이 누적
		int amount = 0;
		//자판기 보유 동전
		int coin = 1000;
		/*
		 * if(balance>coin) {
		coffee-=content_coffee;
		
		}
		 */
		while(true) {
			System.out.print("1:커피마시기.2:종료>");
			int num = input.nextInt();
			if(num==1) {
			System.out.print("동전을 넣으세요(커피값" + price + "원):");
			int payment = input.nextInt();
			//거스름돈 계산
			int change = payment - price;
			
			//커피 주문이 가능한지 조건 체크
			if(coffee < content_coffee) {
				System.out.println("커피가 부족합니다.");
				continue;
			}
			if(cream < content_cream) {
				System.out.println("프림이 부족합니다.");
				continue;
			}
			if(sugar < content_sugar) {
				System.out.println("설탕이 부족합니다.");
				continue;
			}
			if(payment < price) {
				System.out.println("투입한 동전 부족");
				continue;
			}
			if(coin < change) {
				System.out.println("거스름돈 부족");
				continue;
			}
			
			//커피 구매가 가능하기 때문에 연산
			coffee -= content_coffee;//커피 차감;
			cream -= content_cream;
			sugar -= content_sugar;
			amount += payment;//투입한 금액 누적
			
			System.out.printf("거스름돈 : %,d원%n", change);
			System.out.println("맛 좋은 커피가 준비되었습니다.");
			
			System.out.println("====현재 자판기 정보======");
			System.out.printf("커피 : %d%n", coffee);
			System.out.printf("프림 : %d%n", cream);
			System.out.printf("설탕 : %d%n", sugar);
			System.out.printf("자판기 보유 동전 금액 : %,d원%n", coin);
			System.out.printf("투입한 동전 금액 : %,d원%n", amount);
			System.out.println("==============");
			
			//coin-= change;//거스름돈 차감
			}else if(num==2) {
				System.out.println("자판기 안녕~~~");
				break;
			}else {
				System.out.println("잘못 입력했습니다.");
			}
		}
		
		
		input.close();
	}
}