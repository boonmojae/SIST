package kr.s04.array;

public class ArrayMain10 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 1)10,20,30,40,50을 초기값으로 갖는 1차원 배열을 test 이름으로 선언,생성,초기화 하시오.(명시,암시 선택해서)
		 * 2)반복문을 이용해서 출력하시오.
		 * 3)확장for문을 이용해서 출력하시오.
		 * 4)인덱스 3의 데이터를 100으로 변경하시오.   (4을 100으로 바꾸한 뜻
		 * 5)마지막 요소의 값을 200으로 변경하시오.
		 * 6)반복문을 사용하여 모든 요소의 값을 0으로 초기화하시오.
		 * 7)홀수 인덱스에 10, 짝수 인덱스에 20 저장
		 * 8)모든 요소의 총합과 평균(총합을 요소의 수로 나눔) 구하고 출력하시오.(sum(총합), avg(평균))
		 */
		
		/*int sum = 0;
		int [] test = {10,20,30,40,50};
		for(int i=0;i<test.length;i++) {
			if(test.length >sum)
			sum += test[i];
			System.out.println(sum);
		}*/
		
		
		
		//1)
		//int [] test = new int[] {10,20,30,40,50};
		int [] test = {10,20,30,40,50};
		//2
		for(int i=0;i<test.length;i++) {
			System.out.println(test[i]);
		}
		System.out.println("---------");
		//3
		for(int num : test) {
			System.out.println(num);
		}
		System.out.println("---------");
		//4
		test[3] = 100;
		//5
		test[test.length-1]=200;//  length-1이 마지막 인덱스인거 기억하기
		
		for(int i=0;i<test.length;i++) {
			System.out.println(test[i]);
		}
		System.out.println("---------");
		//6
		for(int i=0;i<test.length;i++) {
			test[i] = 0;
			System.out.println(test[i]);
		}
		System.out.println("---------");
		//7
		for(int i=0;i<test.length;i++) {
			if(i%2==1) {//홀수
				test[i]=10;
			}else {//짝수
				test[i]=20;
			}
			System.out.println(test[i]);
		}
		System.out.println("---------");
		//8
		int sum=0, avg=0;
		for(int i=0;i<test.length;i++) {
			sum +=test[i];
		}
		avg= sum/test.length;
		System.out.println(sum);
		System.out.println(avg);
	}
}

/*for(int i=0;i>test.length;)
		//7
		int sum = 0;
		int avg = 0;
		for(int i =0;i<test.length;i++) {
			if(i%2==1) {
				sum = 1+10;
			}else {
				sum = 1+20;
			}
		}
		*/
