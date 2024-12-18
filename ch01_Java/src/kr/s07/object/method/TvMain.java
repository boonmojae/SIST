package kr.s07.object.method;

class Tv{//부class라서public안붙임
	//멤버 필드(속성)
	boolean power; //전원상태(on/off)
	int channel; //채널
	
	//멤버 메서드(동작)
	public void isPower() { //TV를 켜거나 끄는 기능을 수행하는 메서드
		power = !power;  //!가 없으면 false로 출력
	}
	public void channelUp() {//TV의 채널을 높이는 기능을 수행하는 메서드
		++channel;
	}
	public void channelDown() {//TV의 채널을 낮추는 기능을 수행하는 메서드
		--channel;
	}
}
public class TvMain {
	public static void main(String[] args) {
		//객체 선언 및 초기화
		Tv t = new Tv();
		t.isPower(); //power의 값은 false -> true
		System.out.println("TV 실행 여부 : " + t.power);
		System.out.println("현재 채널 : " + t.channel);
		
		System.out.println("---------------------");
		
		t.channel  = 7;
		System.out.println("첫번째 변경된 채널 : " + t.channel);
		System.out.println("---------------");
		
		t.channelDown();
		System.out.println("두번째 변경된 채널 : " + t.channel);
		System.out.println("---------------");
		
		//TV 전원 끄기
		t.isPower();//true -> false
		System.out.println("TV 실행 여부 : " + t.power);
		
	}
}
