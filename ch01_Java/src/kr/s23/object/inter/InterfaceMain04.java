package kr.s23.object.inter;

//인터페이스			(자료형)부모 없을떄
interface I{
	//추상메서드
	public abstract void play();
}
class Pianist implements I{
	@Override
	public void play() {
		System.out.println("피아노를 연주하다");
	}
}
class Cellist implements I{
	@Override
	public void play() {
		System.out.println("첼로를 연주하다");
	}
}

class Stage{
	public void autoplay(I i) {//(i)에 피아니,첼리스트 전달되면서 I타입으로 형변환
		i.play();
	}
}

public class InterfaceMain04 {
	public static void main(String[] args) {
		Stage a = new Stage();
		a.autoplay(new Pianist());//Pianist -> I
								  //클래스타입-> 인터페이스타입 형변환 ||	반대일땐 명시적 캐스트 사용해야됨
								  //자동적으로 형변환
		a.autoplay(new Cellist());//Cellist -> I
								  //클래스타입-> 인터페이스타입 형변환
								  //자동적으로 형변환
	}
}
