package kr.s31.jdbc.statment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet; //결과집합-select하고 결과를 읽어옴
import java.sql.SQLException;
import java.sql.Statement;

public class SelectMain {
	public static void main(String[] args) {
		String db_driver = "oracle.jdbc.OracleDriver";
		String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
		String db_id = "user01";
		String db_password = "1234";
		
		Connection conn=null;//만들어지는 개체 순1
		Statement stmt=null;//2
		ResultSet rs = null;//3 select하면 데이터를 테이블에 반영하고 결과집합이 만들어지는데 그걸 읽어옴
		String sql = null;
		
		try {
			//JDBC 수행 1단계 : 드라이버 로드
			Class.forName(db_driver);
			//JDBC 수행 2단계 : Connection 객체 생성(오라클 접속 인증)
			conn = DriverManager.getConnection(db_url,db_id,db_password);
			
			//SQL문 작성
			sql = "SELECT * FROM test1";
			
			//JDBC 수행 3단계 : Statement 객체 생성
			stmt = conn.createStatement();
			//JDBC 수행 4단계 : SQL문을 실행해서 테이블로부터 레코드(행)을 전달받아서 결과집합을 만들고
						   //ResultSet객체에 담아서 반환
			rs = stmt.executeQuery(sql);//resultset으로 반환한다
			
			System.out.println("ID\t나이");
			//ResultSet에 보관된 결과집합에 접근해서 행 단위로 데이터를 추출
			while(rs.next()) {//next 행 밖에 있는 커서를 행 안으로 들어올 수 있게 함
				/*
				//컬럼명을 통해서 데이터를 반환
				System.out.print(rs.getString("id"));//컬럼명을 넣어서 데이터 읽어오게함
				System.out.print("\t");
				System.out.println(rs.getInt("age"));//컬럼과 매핑되어있는 데이터를 문자열,숫자로 반환해 가져옴/숫자는 getString,getInt다 가능함
				*/
				//컬럼 인덱스를 통해서 데이터 반환
				System.out.print(rs.getString(1));//id
				System.out.print("\t");
				System.out.println(rs.getInt(2));//age
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원 정리
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(stmt!=null)try {stmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}

	}
}
