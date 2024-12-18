package kr.s23.object.inter;

interface Inter1{
	public int getA();
}
interface Inter2{
	public int getB();
}
//인터페이스를 인터페이스에 상속
interface Inter3 extends Inter1,Inter2{//다중상속
	public int getData();//상속관계만 명시 ||getAB가 있는것처럼 ||인터페이스라 일반 클래스 가질수없음
}
interface Inter4{
	public String getMsg();
}
//인터페이스를 클래스에 다중 구현 가능
//클래스 다중 상속 불가능(단일상속만 인정)
public class InterfaceMain05 implements Inter3,Inter4{
	@Override
	public int getA() {
		return 10;
	}
	@Override
	public int getB() {
		return 20;
	}
	@Override
	public int getData() {
		return 30;
	}
	@Override
	public String getMsg() {
		return "서울";
	}
	public static void main(String[] args) {
		InterfaceMain05 it = new InterfaceMain05();
		System.out.println(it.getA());
		System.out.println(it.getB());
		System.out.println(it.getData());
		System.out.println(it.getMsg());
	}
}
