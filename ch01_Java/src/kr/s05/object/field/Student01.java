package kr.s05.object.field;

public class Student01 {
		//멤버 필드(속성)
		String name;//main위는 객체를 위한 영역이 아니엇나?
		int age;
		
		public static void main(String[] args) {
			//객체 선언
			Student01 student;  //Student01는 자료형 뒤에 student는 객체의 주소
			//객체 생성
			student = new Student01();//Student01 이때는 생성자
			
			//객체의 멤버 변수에 값 할당
			student.name = "홍길동";//student.빼도 변수 호출하는것처럼 안됨
			student.age = 21;//student객체의 주소 가방 안으로 들어가려면.찍고 하위에 있는 name,age에 접근
			
			//객체의 멤버 변수에 저장된 값을 출력
			System.out.println("학생의 이름 :" + student.name);
			System.out.println("학생의 나이 :" + student.age);
	}
}
