package kr.s24.object.enumtest;

public class EnumMain01 {
	//문자열 상수
	public static final String JAVA = "JAVA";
	public static final String XML = "XML";
	public static final String JSP = "JSP";
	
	public static void main(String[] args) {
		//문자열 상수 호출
		System.out.println(JAVA);//main도 enummain에 있어서 변수명 안붙임 ||근데 붙여도 상관없음
		System.out.println(XML);
		System.out.println(JSP);
		
	}
}
