package kr.s07.object.method;

class Worker{
	/*
	 * [실습]
	 * Woker
	 * 1)멤버 변수 : 직원 이름(name),급여(money),계좌 잔고(balance)
	 * 2)멤버 메서드 : work(실행하면 money에 1000원을 누적)
	 * 			   deposite(실행하면 money에 저장된 돈을
	 * 						balance에 누적시키고 money는 0처리)
	 * 
	 * WorkerMain의 main
	 * 1)Worker 객체 생성
	 * 2)직원의 이름 지정
	 * 3)10번 일하는데 번 돈이 3000원일때마다 계좌에 저축
	 * 4)직원 이름, 현재 계좌에 입금되지 않고 남아 있는 금여(money),
	 * 계좌 잔고(balance)를 출력하시오.
	 */
	
	String name;
	int money;
	int balance;
	
	public void work() { //일하는 메서드
		money += 1000;
	}
	public void deposite() { //저축하는 메서드
		balance += money;
		money = 0;
	}
	
}
	
public class workerMain {
	public static void main(String[] args) {
		
		Worker w = new Worker();
		w.name = "홍길동";
		
		//10번 일하는데 번 돈이 3000원이 되면 저축
		for(int i=1;i<=10;i++) {
			w.work();
			if(w.money>=3000) {//3000원마다 저축
								//w.money%3000==0
				w.deposite();
			}
		}
		
		System.out.println("직원의 이름:" + w.name);
		//System.out.printf("현재 입금되지 않고 남아있는 급여 : %,%d원%n",w.money);
		//System.out.printf("계좌 잔고 : %,%d원%n",w.balance);
	}
}
