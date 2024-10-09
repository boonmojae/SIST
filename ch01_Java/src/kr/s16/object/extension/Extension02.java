package kr.s16.object.extension;

//부모클래스
class People extends Object{//오브제는 피플의 부모
	public void eat() {
		System.out.println("식사하다");
	}
}
//자식클래스
class Student extends People{
	public void study() {
		System.out.println("공부하다");
	}
}

public class Extension02 {
	public static void main(String[] args) {
		Student s = new Student();
		s.eat(); //People의 메서드를 상속 받아서 호출
		s.study();//student의 메서드
		System.out.println(s.toString());//object의 메소드를 상속받아서 호출
		}
	}

