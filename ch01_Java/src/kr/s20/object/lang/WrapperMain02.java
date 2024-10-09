package kr.s20.object.lang;

public class WrapperMain02 {
	public static void main(String[] args) {
		//기본자료형> 참조 자료 데이터 boxing
		Integer obj1 = new Integer(12);
		Integer obj2 = new Integer(7);
		
		//참조>기본 자료형 unboxing
		int result = obj1.intValue() + obj2.intValue();
		System.out.println("result = " + result);
		
		//auto boxing/unboxing
		Integer obj3 = 10;
		Integer obj4 = 20;
		
		Integer result2 = obj3 + obj4;
		System.out.println("result2 = " + result2);
		
		
	}
}
