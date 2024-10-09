
package kr.s08.object.method;

public class MethodArgMain03 {
	/*
	 * Variavle Arguments
	 * 자료형이 일치할 때 전달하고자 하는 값의 개수를 다르게 지정할 수 있음
	 * 전달되는 데이터는 내부적으로 배열로 인식함
	 */
	
	public void argTest(int...n) { //n에 배열을 가르키는 배열명
		for(int i=0;i<n.length;i++) {
			System.out.println("n["+i+"]:" + n[i]);
		}
		System.out.println("---------------");
	}
	
	public static void main(String[] args) {
		MethodArgMain03 ma = new MethodArgMain03();
		ma.argTest();//빈배열-객체만 생성된 배열
		ma.argTest(1);
		ma.argTest(10,20,30);	
	}
}














//4번줄 인자개수에 맞춰서 보내야됨,값이 없어도 오류,자료형 동일하게 int면 정수만