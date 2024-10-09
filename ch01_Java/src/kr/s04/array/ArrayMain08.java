package kr.s04.array;

public class ArrayMain08 {			//인자
	public static void main(String[] args) { ///main=함수  args=인자  인자에 데이터를 전달하려고함
		//클래스를 실행할 때 외부에서 데이터 전달     
		                              //숫자를 넣으면 문자열로 인식함 "10" 이런식으로 처리 근데 출력햇을떄 "는 안나옴
		if(args.length>0) {
			for(int i=0;i<args.length;i++) {
				System.out.println(i + ":" + args[i]);
			}
		}else {//전달되는 데이터가 없음
			System.out.println("입력한 내용이 없습니다");
		}
	}
}
