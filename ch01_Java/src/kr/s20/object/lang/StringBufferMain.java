package kr.s20.object.lang;

public class StringBufferMain {//객체를 하나만 만들어서 관리
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("여름 덥다!!");//재정의가돼잇음
		System.out.println("1: " + sb);	  //012 
		
		//지정한 인덱스에 문자 삽입=insert
		sb.insert(2, '이');
		System.out.println("2: " + sb);
		
		//문자열 뒤에 문자열을 추가
		sb.append("가을은 ");//공백있음
		System.out.println("3: " + sb);
		
		sb.append("시원하다");
		System.out.println("4: " + sb);
		
		//시작 인덱스부터 끝 인덱스 전까지 문자열 대체
		sb.replace(0, 3, "여행가자!!");//공백빼고 여름이를 여행가자로 대체
		System.out.println("5: " + sb);
		
		//지정한 인덱스의 문자를 삭제
		sb.deleteCharAt(0);//'여'를 삭제
		System.out.println("6: " + sb);
		
		//시작 인덱스부터 끝 인덱스 전까지 문자열 삭제
		sb.delete(0, 3);//3은 포함 안됨
		System.out.println("7: " + sb);
		
		//StringBuffer -> String 변환
		String str = sb.toString();
		System.out.println("str = " + str);
		
		
		
	}
}
