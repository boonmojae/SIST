package kr.s20.object.lang;

public class SpringMain03 {
	public static void main(String[] args) {
		String s1 = "  aBa  ";
		String s2 = "abc";
		int a = 100;
		String msg = null;//객체를 안만들어서 주소가 없다는 뜻=null|| 지금은 초기하한거임
		
		msg = s1.toUpperCase();
		System.out.println("msg:" + msg);//대문자 처리
		
		msg = s1.toLowerCase();
		System.out.println("msg:" + msg);//소문자 처리
		
		msg = s1.replace("aB", "b");//old 문자를 new 문자로 대체(앞이 올드
		System.out.println("msg:" + msg);
		
		msg = s1.trim();//앞뒤 공백 제거
		System.out.println("msg:" + msg);//중간에 있는 공백은 제거 못함
		
		//문자열 중에 메서드의 인자로 전달된 문자열이 포함되어있는지 검증
		boolean f = s1.contains("aB");//msg =으로 하면 오류남		불리언이라 반환타입
		System.out.println("f = " + f);
		
		//메서드의 인자로 전달된 문자열로 시작하는지 검증
		f = s2.startsWith("ab");
		System.out.println("f = " + f);
		
		//메서드의 인자로 전달된 문자열로 끝나는지 검증
		f = s2.endsWith("bc");
		System.out.println("f = " + f);
		
		//int -> String
		msg = String.valueOf(a);
		msg = a + "";//메서드를 이용한 방법은 아니지만 빈문자열의 영향을 받아 연결됨|데이터값 유지하면서 문자열로 바뀜

	}
}
