package kr.s22.object.abs;

//추상클래스
//미완성된 개념으로 단독으로 객체 생성이 불가능하고 일반적으로 자식클래스에 상속되어져서 사용함.

abstract class A{
	private int x;
	public void setX(int x) {
		this.x = x;
	}
	public int getx() {
		return x;
	}
}
//자식클래스
class B extends A{
	int b = 200;
}
public class AbstractMain01 {
	public static void main(String[] args) {
		//추상클래스는 객체 생성 불가능 ||미완성이라 단독 X
		//A ap = new A(); ||abstractd이거 지우면 에러 안남
		
		B bp = new B();
		bp.setX(100);
		System.out.println(bp.getx());
		System.out.println(bp.b);
		
	}
}
