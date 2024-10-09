package kr.s31.jdbc.statment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTableMain {
	public static void main(String[] args) {		   //sql문장 ddl로 만들어 사용할 수 있음
		String db_driver = "oracle.jdbc.OracleDriver";//메모리에 올려야되는데 forname이 역할을 함
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String db_id = "user01";
		String db_password = "1234";
		
		Connection conn=null;
		Statement stmt=null;
		String sql=null;//변수를 만들어야 try~catch 사용가능?
		
		try {
			//JSBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);
			//JDBC 수행 2단계 : Connection 객체 생성(오라클에 접속하기 위한 인증)
			conn = DriverManager.getConnection(db_url,db_id,db_password);//오라클 연동됨
			
			System.out.println("test1 테이블 제거를 시작합니다.");
			
			//SQL문 작성
			sql = "DROP TABLE test1";
			
			//JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement();
			//JDBC 수행 4단계 : SQL문을 실행해서 테이블을 삭제
			stmt.executeUpdate(sql);
			System.out.println("테이블이 정상적으로 삭제되었습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			if(stmt!=null)try {stmt.close();} catch(SQLException e) {}
			if(conn!=null)try {conn.close();} catch(SQLException e) {}
		}
		
		
	}
}
