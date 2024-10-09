 package kr.s23.object.inter;

//인터페이스 : 형식만 있고 내용이 없음>형식을 클래스에 도입해서 만듦
interface A2{
	//추상메서드  ||객체생성못해서 메모리에 못올라감
	public abstract void abc();//원형
	void def();//public abstract생략
}//단독으로 사용못해서 클래스에 구현

class B2 implements A2{
	//인터페이스의 추상메서드를 구현
	@Override
	public void abc() {
		System.out.println("abc 메서드");//def까지 구현해야 오류없음
	}
	@Override
	public void def() {
		System.out.println("def 메서드");
	}
}
public class InterfaceMain02 {
	public static void main(String[] args) {
		B2 bp = new B2();
		bp.abc();
		bp.def();
	}
}
