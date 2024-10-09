package kr.s31.jdbc.statment;

import java.sql.Connection; //오라클 연동
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;//sql문장 실어 나름

public class AlterTableMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";//메모리에 올려야되는데 forname이 역할을 함
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String db_id = "user01";
		String db_password = "1234";
		
		Connection conn = null;//객체 생성
		Statement stmt =null;
		String sql = null;
		
		try {
			//JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);//자바와 오라클을 연동하는 다리
			//JDBC 수행 2단계 : Connection 객체 생성(오라클 접속을 위한 인증 작업)
			conn = DriverManager.getConnection(db_url,db_id,db_password);//커넥션 객체 생성됨
			
			System.out.println("test1 테이블의 컬럼을 수정합니다.");
			
			//SQL문 작성
			sql = "ALTER TABLE test1 MODIFY(id VARCHAR2(10) NOT NULL)";
			
			//JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement();
			
			//JDBC 수행 4단계 : SQL문을 수행해서 테이블에 정보를 변경하는 작업 수행
			stmt.execute(sql);
			System.out.println("테이블의 컬럼 정보 수정을 완료했습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리 필수
			if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
	}
}
