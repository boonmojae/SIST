package kr.s31.jdbc.statment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * CRUD 작업
 * C Create(생성) -> INSERT문
 * R Read(읽기) -> SELECT문
 * U Update(갱신) -> UPDATE문
 * D Delete(삭제) -> DELETE문
 */

//변수선언 > try-catch>자원정리
public class DelectMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String db_id = "user01";
		String db_password = "1234";
		
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);
			//2단계 : Connection 객체 생성(오라클 접속 인증)
			conn = DriverManager.getConnection(db_url,db_id,db_password);
			
			//SQL문 작성
			sql = "DELETE FROM test1 WHERE id='SKY'";//update,delete에서 where꼭 넣기
			
			//3단계 : Statement 객체 생성
			stmt = conn.createStatement();
			//4단계 : SQL문을 실행해서 테이블에서 행을 삭제한 후 삭제한 행의 개수 반환
			int count = stmt.executeUpdate(sql);//카운트가 구해짐
			System.out.println(count + "개 행을 삭제했습니다.");//selectMain에서 확인해보기
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		
		
		
	}
}
