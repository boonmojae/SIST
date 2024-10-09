package kr.s02.operator;

public class OperatorMain01 {
	public static void main(String[] args) {
		System.out.println("===증감연산자===");
		//증가연산자
		int i =5;
		//증가연산자를 변수명 앞에 명시하면 변수값을 1 증가시킨 후 증가된 값을 읽어옴
		System.out.println(++i);
		System.out.println("----------");
		
		//증가연산자를 변수명 뒤에 명시하면 변수값을 우선 읽어온 후에 변수의 값을 1 증가시킴
		System.out.println(i++); //증가시키기전에 i를 호출해 6이 나옴 ++은 7데이터를 갖고있어서 한번 더 출력해야됨
		System.out.println(i); //7
		
		System.out.println("----------");
		//감소연산자
		int j = 10;
		
		//감소연산자를 변수명 앞에 명시하면 변수값을 1 감소시킨 후 감소된 값을 읽어옴
		System.out.println(--j); //9
		
		System.out.println("----------");
		//감소연산자를 변수명 뒤에 명시하면 변수값을 우선 읽어온 후 변수의 값을 1 감소시킴
		System.out.println(j--); //9
		System.out.println(j); //8
		
		
		
	}

}
