package kr.s31.jdbc.statment;

import java.sql.Connection; //오라클 연동
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;//sql문장 실어 나름

public class CreateTableMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String db_id = "user01";
		String db_password = "1234";
		
		//데이터베이스 연동하면 자원정리 해줘야됨 ,안하면 점점 느려짐
		
		Connection conn = null;
		Statement stmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);
			//JDBC 수행 2단계 : Connection 객체 생성(오라클 접속을 위한 인증)
			conn = DriverManager.getConnection(db_url,db_id,db_password);
			
			System.out.println("test1 테이블을 생성합니다.");
			/*
			 * 테이블을 생성하는 SQL문
			 * 접속한 계정에 테이블명이 중복되면 에러가 발생하기 때문에 
			 * 동일 계정에서는 한 번만 수행함
			 */
			sql = "CREATE TABLE test1(id VARCHAR2(10),age NUMBER)";//NUMBER뒤에 ;붙이면 오류
			
			//JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement();
			
			//JDBC 수행 4단계 : SQL문을 실행해서 DB에 테이블을 생성
			stmt.executeUpdate(sql);
			
			System.out.println("테이블이 정상적으로 생성되었습니다.");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//자원정리
			if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		
	}
}
