package kr.s04.array;

public class ArrayMain11 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * double형인 배열의 모든 요소의 합과 평균을 구하는 프로그램을 작성하시오.
		 * 
		 * [입력 예시]
		 * 요소의 수>3
		 * a[0]=2.2 (input.nextDouble())
		 * a[1]=2.2
		 * a[2]=3.3
		 * 
		 * [출력 예시]
		 * 모든 요소의 합은 7.7입니다. (System.out.println 사용)
		 * 모든 요소의 평균 2.57입니다. (System.out.printf로 소수점 둘째자리까지 표시)
		 * 
		 */
		java.util.Scanner input = new java.util.Scanner(System.in);
		
		System.out.print("요소 수>");
		int num = input.nextInt();
		
		double[] a= new double[num];
		
		double sum=0.0;
		
		for(int i =0;i<a.length;i++) { //a.length거나 num상관없음
			System.out.println("a["+i+"]");
			a[i] = input.nextDouble();
			
			//합 구하기
			sum += a[i];
			
		}
		
		System.out.println("모든 요소의 합은" + sum +"입니다.");
		System.out.printf("모든 요소의 평균 %.2f입니다.%n",sum/num);//a.length거나 num상관없음
		input.close();
		
	}
}

/*
int sum=0;
double avg =0.0;
for(int i =0;i<a.length;i++) {
	sum += a[num];
	avg = sum/(double)a.length;
	*/
