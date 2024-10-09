package kr.s04.array;

public class ArrayMain03 {
	public static void main(String[] args) {
		//배열 선언 및 생성(암시적 배열 생성), 초기화
		int[] array = {10,20,30,40,50};///int[] 배열의 자료형, array를 배열명이라 칭함,array[]이렇게 써도 가능
		
		//반복문을 이용한 배열의 요소 출력
						//배열의 길이
		for(int i=0;i<array.length;i++) {//인덱스 사용해야되면 이 코드 사용
			System.out.print(array[i] + "\t");
		}
		System.out.println("\n------------------"); //위에 줄바꿈 없어서 \n
		
		//확장 for문을 이용한 배열의 요소 출력
		for(int num : array) {//num에 array가 인덱스를 주는게 아니라 데이터를 주는것,값만 필요한 경우
			System.out.print(num + "\t");
			 
		}
	}
}
