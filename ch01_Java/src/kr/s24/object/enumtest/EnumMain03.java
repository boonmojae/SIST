package kr.s24.object.enumtest;

enum Gender{
	MALE,FEMALE;//;넣어도되고 안넣어도됨|재정의할땐 ;붙여야됨 ||문자열 상수
	
	//메서드 재정의
	@Override
	public String toString() {//값을 변경하고싶을때 toString 재정의
		switch(this) {//4번 객체로 인식해함
		case MALE:
			return "남자";
		default :
			return "여자";
		}
	}
}


public class EnumMain03 {
	public static void main(String[] args) {
		System.out.println(Gender.MALE);
		System.out.println(Gender.FEMALE);
		System.out.println("----------------");
		
		System.out.println(Gender.MALE.toString());
		System.out.println(Gender.FEMALE.toString());
		
		
	}
}
