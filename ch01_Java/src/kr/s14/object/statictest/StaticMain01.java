package kr.s14.object.statictest;

public class StaticMain01 {
	public static void main(String[] args) {
		StaticCount sc1 = new StaticCount();
		System.out.println("c :" + sc1.c + ", count :" + StaticCount.count); //sc1.count에러는 안나지만 정상적인 호출방법X,클래스명 붙여야됨
		
		StaticCount sc2 = new StaticCount();
		System.out.println("c :" + sc2.c + ", count :" + StaticCount.count);
		
		StaticCount sc3 = new StaticCount();
		System.out.println("c :" + sc3.c + ", count :" + StaticCount.count);//3개의 객체 생성
		
	}
}
