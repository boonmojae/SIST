package kr.s02.operator;

public class OperatorMain07 {
	public static void main(String[] args) {
		System.out.println("==논리연산자===");
		boolean a,b,result;
		a = true;
		b = false;
		
		//논리곱(&&)
		//주어진 조건들이 모두 true 일때 결과값이 true
		result = a && b;
		System.out.println("a && b : " +result);
		
		//논리합(||)
		//주어진 조건 중 하나라도 true 이면 결과값이 true
		result = a || b;
		System.out.println("a || b : " + result);
		
		//부정(!)
		//true를 부정하면 false, false를 부정하면 true
		result = !a;
		System.out.println("!a : " +result);
		
		
		
		
		
	}

}
