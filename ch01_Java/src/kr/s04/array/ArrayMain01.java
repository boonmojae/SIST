package kr.s04.array;

public class ArrayMain01 {
	public static void main(String[] args) {
		//배열 선언
		char[] ch;
		//배열 생성
		ch = new char[4];//4는 배열의 길이
		//배열 초기화
		ch[0] = 'J';
		ch[1] = 'a';
		ch[2] = 'v';
		ch[3] = 'a';
		
		//배열의 요소 출력
		System.out.println(ch[0]);
		System.out.println(ch[1]);
		System.out.println(ch[2]);
		System.out.println(ch[3]);
		
		//반복문을 이용한 배열의 요소 출력
		for(int i=0;i<ch.length;i++) {  //4는 배열의 길이, 길이 모를때 4 자리에 ch.length대체  <=안넣게 
			System.out.println("ch["+i+"]:" + ch[i]);
		}
		//배열의 선언 및 생성
		int[] it = new int[6];
		
		//배열의 선언 및 생성(명시적 배열 생성),초기화
		char[]ch2 = new char[] {'J','a','v','a'}; //[]에 4를 적고 java적으면 오류가 뜸
		//배열의 선언 및 생성(암시적 배열 생성),초기화
		char[]ch3 = {'자','바'}; //new char 지우면 암시적 배열
		
	}
}
