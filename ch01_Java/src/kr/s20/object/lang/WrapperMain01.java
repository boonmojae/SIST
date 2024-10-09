package kr.s20.object.lang;

public class WrapperMain01 {
	public static void main(String[] args) {
		boolean b = true;//기본자료형 데이터
		Boolean wrap_b = new Boolean(b);//기본 자료형 데이터->참조 자료형 데이터||예전꺼
		//참조 자료형 데이터->기본 자료형 데이터
		boolean b2 = wrap_b.booleanValue();
		System.out.println(b2);
		
		System.out.println("------------");
		
		char c = 'A';//기본자료형
		Character wrap_c = c;//기본 자료형 데이터-> 참조 자료형 데이터
							 //auto boxing
		//참조 자료형 데이터 -> 기본 자료형 데이터
		//auto unboxing||메소드 사용없이 그냥 출력
		System.out.println(wrap_c);
	}
}
