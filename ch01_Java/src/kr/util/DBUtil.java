package kr.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class DBUtil {  			//선택 ctrl+shift+x하면 소->대문자됨 /ctrl+shift+y 대->소
	private static final String DB_DRIVER = "oracle.jdbc.OracleDriver";//static한 상수형태로 만들어서 스태틱 메서드로 사용할 수 있게(변수는 값을 못바꿔서 상수?)
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_ID = "user01";
	private static final String DB_PASSWORD = "1234";
	
	//Connection 객체를 생성해서 반환
	public static Connection getConnection() //이렇게 만들어서 3,4만 만들면 됨/얘가 static해서 위에도 static하게 만듦 그리고 여기서만 사용하게 private붙임
						throws ClassNotFoundException,
								SQLException{
		//JDBC 수행 1단계 : 드라이버 로드
		Class.forName(DB_DRIVER);
		//JDBC 수행 2단계 : Connection 객체 생성
		return DriverManager.getConnection(DB_URL,DB_ID,DB_PASSWORD);
	}
	//자원정리
	public static void executeClose(ResultSet rs,
									PreparedStatement pstmt,
									Connection conn) {
		if(rs!=null)try {rs.close();}catch(SQLException e) {}
		if(pstmt!=null)try {conn.close();}catch(SQLException e) {}
		if(conn!=null)try {conn.close();}catch(SQLException e) {}
	}
	public static void executeClose(CallableStatement cstmt,//프로시저를 자바에서 사용하려고 만듦 위에를 오버로딩해서 만듦,동일한 메서드라 아니라 다른 메서드로 인식
									Connection conn) {
		if(cstmt!=null)try {cstmt.close();}catch(SQLException e) {}
		if(conn!=null)try {conn.close();}catch(SQLException e) {}
	}
}
