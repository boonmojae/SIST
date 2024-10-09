package kr.s04.array;

public class ArrayMain04 {
	public static void main(String[] args) {
		int sum = 0;//총점을 저장하는 변수
		float average = 0.0f;//평균을 저장하는 변수
		
		//배열 선언 , 생성, 초기화
		int[] score = {100,88,88,100,90}; //length=5
		
		//총점 구하기      //과목수
		for(int i=0;i<score.length;i++) {
			//누적
			sum += score[i];
		}
		//평균 구하기       5말고 이렇게 적으면 바뀔때마다 코드 고칠 필요없음
		average = sum/(float)score.length;
		
		System.out.printf("총점 : %d%n", sum);
		System.out.printf("평균 : %.2f", average);
	}
}
