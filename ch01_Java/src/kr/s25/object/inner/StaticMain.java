package kr.s25.object.inner;

public class StaticMain {
	//static 내부클래스
	//static 내부클래스를 포함한 클래스를 먼저 객체 생성하는것이 아니라
	//static 내부클래스를 단독으로 객체 생성해서 사용
	public static class Inner{//static있으면 내부 객체 생성만 해도됨 StaticMain 안하고
		int iv = 100;//인스턴스 변수(는 객체를 생성해야 호출가능)
		static int cv = 200;//static 변수
		public static void make() {//static 메서드
			System.out.println("하하");
		}
	}
	public static void main(String[] args) {
		//static 내부클래스 객체 생성
		Inner i = new Inner();
		//인스턴스 변수 호출
		System.out.println(i.iv);
		//static 변수 호출
		System.out.println(Inner.cv);
		//static 메서드 호출
		Inner.make();
	}
}
