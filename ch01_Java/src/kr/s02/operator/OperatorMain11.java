package kr.s02.operator;

public class OperatorMain11 {
	public static void main(String[] args) {
		char ch = 'M';
		String str;//대문자인지 판달한 결과를 저장할 문자열 변수 선언
				 //A        Z(아스키 코드)
		//str = ch>=65 && ch<=90 ? "대문자임" : "대문자가 아님";
		str = ch>='A' && ch<='Z' ? "대문자임" : "대문자가 아님";
		
		System.out.println(ch + "=>" + str);
		
	}
}
