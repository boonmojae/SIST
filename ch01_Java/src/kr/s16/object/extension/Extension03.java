package kr.s16.object.extension;

//부모클래스
class A{
	int x = 100;
	private int y = 200;
	
	public int getY() {
		return y;
	}
}
//자식클래스
class B extends A{
	int z = 300;
}


public class Extension03 {
	public static void main(String[] args) {
		B bp = new B();
		System.out.println(bp.x);
		System.out.println(bp.getY());//상속을 받았지만 사용할수 없음
		System.out.println(bp.z);
	}
}
