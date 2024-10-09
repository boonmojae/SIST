package kr.s11.object.constructor;

class Car2{//같은패키지에 동일한 클래스명을 만들면 저장>오류남 Car
	String color;
	String gearType;
	int door;
	
	//생성자 오버로딩
	//생성자를 여러개 정의하는데 인자의 타입과 개수, 배치 순서를 기준으로 생성자를 구분
	public Car2() {}//인자없는 생성자
	
	public Car2(String c, String g, int d) {//생성자에 인자 만들수있음
		color = c;
		gearType = g;
		door = d;
	}
}


public class CarMain02 {
	public static void main(String[] args) {
		//객체 선언 및 생성
		Car2 car = new Car2();//인자 없고 호출하는 코드,에러뜨는데 10번쓰면 사라짐
		car.color = "골드";
		car.gearType = "auto";
		car.door = 5;
		
		System.out.println(car.color+","+car.gearType+","+car.door);
		
		Car2 car2 = new Car2("검정색","manual",4);
		System.out.println(car2.color + "," + car2.gearType + "," + car2.door);
		
	}
}
