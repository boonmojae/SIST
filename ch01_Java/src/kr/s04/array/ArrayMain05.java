package kr.s04.array;

public class ArrayMain05 {
	public static void main(String[] args) {
		int[]score = {79,88,91,33,100,55,95};//79 부터 0번 88 1번
		int max = score[0];//최대값이 저장되는 변수   기준이 되는 값
		int min = score[0];//최소값이 저장되는 변수
		
		for(int i =1;i<score.length;i++) {//인덱스 0으로 시작해야되는데 이미 기준값으로 사용해서 1,0해도 상관없는데 무의미함
			//최대값 구하기
			if(score[i] > max) {
				max = score[i];
			}
			//최소값 구하기
			if(score[i]< min) {
				min = score[i];
			}
		}//end of for
		
		//최대값, 최소값 출력
		System.out.println("최대값 :" + max);
		System.out.println("최소값 :" + min);
		
		
	}//end of main
}
