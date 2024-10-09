package kr.s28.iostream;

import java.io.IOException;

public class InputStreamMain01 {
	public static void main(String[] args) {
		System.out.print("영문자 1개 입력:");
		try {	
			//문자 하나를 입력 받아서 아스키 코드로 반환
			int a = System.in.read(); //InputStream읽어옴
			System.out.println(a + ", " + (char)a);
			
			System.in.read();//enter 처리 \r 13		엔터를 흡수해 없애는 작업
			System.in.read();//enter 처리 \n 10  입력받은다음에 아무것도 안하면 사라짐
			
			System.out.print("숫자 1개 입력:");//문자열로 인식해 아스키 코드값 나옴
			int b = System.in.read()-48;
			System.out.println(b);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
