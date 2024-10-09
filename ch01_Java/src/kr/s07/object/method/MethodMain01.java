package kr.s07.object.method;

public class MethodMain01 {
	//반환하는 데이터가 있는 경우			 //add는 메서드 명  퍼블릭 뒤의 int는 밑에있는 반환하는데이터의 자료형으로 반환형이라 부름
	public int add(int a,int b) { //(int a,int b)가 입구 메서드의 소괄호 안에있는 a,b인자
		return a + b;	//return은 출구로 함수 밖으로 빠져나감 |a+b는 반환하는 데이터
	}
	//반환하는 데이터가 없는 경우
	public void print(String str) {  //반환하는 데이터가 없어서 void(비어있다) 명시, 형식을 맞추기 위해서 void 써야됨
		System.out.println(str);
	}
	
	
	public static void main(String[] args) {  //main도 return이 없어서 void 포함
		//객체 선언 및 생성
		MethodMain01 method = new MethodMain01();
		//객체의 멤버 메서드 호출
		int result = method.add(5, 8);
		System.out.println("result = " + result);
		
		method.print("오늘은 월요일");
	}
}
