package kr.s26.exception;

public class ExceptionMain07 {
	
	public void methodA(String[] n)throws Exception{
		if(n.length>0) {//데이터 입력 O
			for(String s : n) {
				System.out.println(s);
			}
		}else {//데이터 입력 X
			//예외를 인위적으로 발생시킴
			throw new Exception("배열에 요소가 없습니다."); //22번으로 던짐
		}
	}
	
	public static void main(String[] args) {
		ExceptionMain07 em = new ExceptionMain07();
		try {
			em.methodA(args);//9번의 args를 입력
		}catch(Exception e) {
			//예외문구 출력
			//System.out.println(e.toString());22,23둘다 가능 23이 더 깔끔하게 나옴
			System.out.println(e.getMessage());
		}
	}
}
