package kr.s25.object.inner;

class Inner7{
	public void disp() {
		System.out.println("부모클래스의 disp");
	}
}

public class AnonymousMain {
	public void make() {
		//익명 내부클래스 : 클래스를 정의하는 부분과 객체를 생성하는 부분이 합쳐진 형태
		//Inner7 클래스가 상속된 이름없는 클래스를 객체 생성
		Inner7 i = new Inner7() {//13{~18}=클래스 정의   Inner7 i = new Inner7()는 객체 생성
			@Override
			public void disp() {
				System.out.println("익명 내부클래스의 disp");
			}
		};//()생성자 뒤에 ;말고 {} 있다면 익명 내부클래스 뜻함
		i.disp();//메서드 호출
	}
	public static void main(String[] args) {
		AnonymousMain am = new AnonymousMain();
		am.make();
		
	}
}

