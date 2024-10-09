package kr.s32.jdbc.preparedstatement;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

public class DeleteMain {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계
			conn=DBUtil.getConnection();
			
			//SQL문 작성
			sql = "DELETE FROM test2 WHERE id=?";
			
			//JDBC 수행 3단계 : preparedStatement 객체 생성
			pstmt=conn.prepareStatement(sql);//위의 sql이 여기에 전달
			//?에 데이터 바인딩
			pstmt.setString(1, "star");//star가 위의 ?에 전달됨
			//JDBC 수행 4단계 : SQL문을 테이블에 반영해서 행을 삭제
			int count = pstmt.executeUpdate();
			System.out.println(count + "개 행의 정보를 삭제했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null,pstmt,conn);//resultset이 없어서 null
		}
	}
}
