package kr.s22.object.abs;

//추상클래스
abstract class AbsEx{
	int a = 100;//변수
	public int getA() {//일반 메서드
		return a;
	}
	//추상메서드
	abstract public int getB();//abstract,public 뭘 먼저 쓰든지 상관없음
	abstract public int getC();
}
//추상클래스
abstract class AbsEx2 extends AbsEx{//추상클래스를 추상클래스에 상속하면 추상메서드 구현 의무 X
	String msg = "신세계";
	//추상메서드
	public abstract String getMsg();//10을 구현하려고함
	//부모클래스의 추상메서드 구현
	public int getB() {
		return 200;
	}
}

public class AbstractMain04 extends AbsEx2{//4번상속하면 오류남 11,17미구현해서
	//부모클래스의 추상메서드를 구현
	@Override
	public int getC() {
		return 300;
	}
	@Override
	public String getMsg() {
		return msg;
	}
	public static void main(String[] args) {
		AbstractMain04 am = new AbstractMain04();
		System.out.println(am.getA());
		System.out.println(am.getB());
		System.out.println(am.getC());
		System.out.println(am.getMsg());
	}
}
