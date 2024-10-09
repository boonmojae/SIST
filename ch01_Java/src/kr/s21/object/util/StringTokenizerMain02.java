package kr.s21.object.util;

import java.util.StringTokenizer;

public class StringTokenizerMain02 {
	public static void main(String[] args) {
		String source = "2024-03-04 16:03:20";//하이픈,공백,콜론이 구분자
		StringTokenizer st = new StringTokenizer(source,"- :");
		while(st.hasMoreTokens()) {
								  //잘려준 문자열 반환
			System.out.println(st.nextToken());
		}
		
		
	}
}
