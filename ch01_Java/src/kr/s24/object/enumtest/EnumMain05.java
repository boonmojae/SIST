package kr.s24.object.enumtest;

enum Item2{
	ADD(5),DEL(11),SEARCH(2),CANCEL(22);//메소드,생성자 만들꺼면 ;추가  (2)이런거 생성자가 됨
	
	//위에 지정한 상수 값들을 저장하기 위한 공간
	private final int var;
	
	//생성자
	Item2(int var){
		this.var = var;
	}
	
	public int getVar() {//7번 호출못하니까 getV
		return var;	//상수를 반환
	}
}

public class EnumMain05 {
	public static void main(String[] args) {
	System.out.println(Item2.ADD);
	System.out.println(Item2.DEL);
	System.out.println(Item2.SEARCH);
	System.out.println(Item2.CANCEL);
	System.out.println("---------------");
	
	System.out.println(Item2.ADD.getVar());
	System.out.println(Item2.DEL.getVar());
	System.out.println(Item2.SEARCH.getVar());
	System.out.println(Item2.CANCEL.getVar());
	}
}
