package kr.s19.object.finaltest;
//클래스에 final을 명시하면 상속이 불가능
final class Me1{
	int var;
	public void setvar(int var) {
		this.var = var;
	}
}


//public class FinalMain03 extends Me1{
public class FinalMain03{
	public static void main(String[] args) {
		FinalMain03 fm = new FinalMain03();
		//fm.setvar(1000);
	}
}
