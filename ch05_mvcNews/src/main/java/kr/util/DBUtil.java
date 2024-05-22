package kr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBUtil {
	
	//Connection 객체를 생성해서 반환
	public static Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();		//식별자의 큰 범주 /식별자
		DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/xe");//jdbc/xe로 context.xml에 접근 정보를 읽어다가 커넥션풀로 할당받음
		return ds.getConnection();//커넥션 반환
	}
	//자원 정리
	public static void executeClose(ResultSet rs,PreparedStatement pstmt,Connection conn) {
		if(rs!=null)try {rs.close();}catch(SQLException e) {}
		if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
		if(conn!=null)try {conn.close();}catch(SQLException e) {}
		
	}
	
}
