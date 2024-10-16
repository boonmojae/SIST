package kr.s17.object.overriding;

class Mother2{
	public String getLunch() {
		return "밥";
	}
}
//자식클래스
class Daughter2 extends Mother2{
	//메서드 오버라이딩
	@Override
	public String getLunch() {
		return "빵";
	}
	
	public String getRice() {
		//부모클래스에 있는 getLunch(밥)를 super로 가져옴
		return super.getLunch();
	}
}

public class OverridingMain03 {
	public static void main(String[] args) {
		Daughter2 d = new Daughter2();
		System.out.println("딸은 "+d.getLunch()+"을 먹습니다");
		System.out.println("딸은 오늘은 왠지 "+d.getRice()+"이 먹고 싶어졌다!");
	}
}
