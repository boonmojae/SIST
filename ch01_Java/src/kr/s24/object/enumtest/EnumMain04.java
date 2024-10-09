package kr.s24.object.enumtest;

enum Item{
	ADD,DEL,SEARCH,CANCEL//문자열 상수개체
}


public class EnumMain04 {
	public static void main(String[] args) {
		//values() 메서드는 열거 타입의 모든 열거 객체들을 배열로 만들어 반환
		Item[] items = Item.values();
		//반목문을 이용한 열거 타입 상수 호출
		for(Item n : items) {//확장for문
			System.out.println(n + " : " + n.ordinal());
		}
	}
}
