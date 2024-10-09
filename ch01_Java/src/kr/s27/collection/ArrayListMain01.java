package kr.s27.collection;

import java.util.ArrayList;

class A{
	@Override
	public String toString() {
		return "A";
	}
}
class B{}

public class ArrayListMain01 {
	public static void main(String[] args) {
		ArrayList list = new ArrayList();//list참조변수
		//ArrayList에 객체 저장하기
		list.add(new A());//재정의 안하면 참조값주소
		list.add(new B());
		list.add("홍길동");
		list.add(10);//int -> Integer (auto boxing)기본자료형은 자동적으로 변환됨
		
		//저장된 요소의 목록
		System.out.println(list);
		//요소의 갯수
		System.out.println(list.size());//가변적으로 늘,줄 함
		
	}
}
