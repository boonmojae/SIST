package kr.s32.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

public class UpdateMain {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성			//1,2,3?에 매핑
			sql = "UPDATE test2 SET name=?,age=? WHERE id=?";
			
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);//sql이미 전달돼서
			//?에 데이터 바인딩
			pstmt.setString(1,"김연아");//변경할 가능성이 있는거 다 명시
			pstmt.setInt(2,40);
			pstmt.setString(3,"she'g");
			
			//JDBC 수행 4단계 : SQL문을 실행해서 테이블의 데이터 수정
			int count = pstmt.executeUpdate();//여기서 넣으면 에러남 
			System.out.println(count + "개 행의 정보를 수정했습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
