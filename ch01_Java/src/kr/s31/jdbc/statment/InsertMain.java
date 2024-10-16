package kr.s31.jdbc.statment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String db_id = "user01";
		String db_password = "1234";
		
		Connection conn=null;
		Statement stmt=null;
		String sql=null;
		
		try {
			//JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);
			//JDBC 수행 2단계 : Connection 객체 생성(오라클 접속 인증)
			conn = DriverManager.getConnection(db_url,db_id,db_password);
			//SQL문 작성
			sql = "INSERT INTO test1 (id,age) VALUES ('she''g',50)";//'she''g'의 가운데''를 일반문자로 인식 '한개면 특수문자로 인식
			//JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement();
			//JDBC 수행 4단계 : SQL문을 실행해서 하나의 행을 삽입
			//			   : 삽입 작업 후 삽입한 행의 개수를 반환
			int count = stmt.executeUpdate(sql);//행이 생기면 1 반환/sql문이 하나일땐 오토커밋 사용해도됨
			System.out.println(count + "개의 행을 추가했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}

		
	}
}
