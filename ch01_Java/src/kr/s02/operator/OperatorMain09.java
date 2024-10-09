package kr.s02.operator;

public class OperatorMain09 {
	public static void main(String[] args) {
		System.out.println("===조건(삼항)연산자===");
		
		int x = 10;
		int y = -10;
		
		//조건 삼항연산자
				//조건      참값    거짓값
		int absx = x >= 0 ? x : -x;
		int absy = y >= 0 ? y : -y;
		
		System.out.println("x=10일때, x의 절대값은 " + absx);
		System.out.println("y=-10일때, y의; 절대값은" + absy);
		
	}

}
