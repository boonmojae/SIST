package kr.s30.jdbc;

public class DriverMain {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.OracleDriver"); //try~catch 해야됨 forname이 oracle을 메모리에 올림
			System.out.println("오라클 드라이버가 정상적으로 로드되었습니다.");//드라이버가 메모리에 올라감
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
