package kr.s33.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

public class SelectListMain {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql=null;
		
		try {
			//JDBC 수행 1,2단계
			conn=DBUtil.getConnection();
			
			//SQL문 작성
			sql="SELECT * FROM test3 ORDER BY num DESC";
			
			//JDBC 수행 3단계 :preparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계 : SQL문을 실행해서 결과집합을 ResultSet 담아서 반환
			rs = pstmt.executeQuery();
			System.out.println("번호\t작성자\t등록일\t\t제목");//모든 데이터가 나오는게 아니어서 상세페이지 만듦
			while(rs.next()) {
				System.out.print(rs.getInt("num"));
				System.out.print("\t");
				System.out.print(rs.getString("name"));
				System.out.print("\t");
				System.out.print(rs.getDate("reg_date"));
				System.out.print("\t");
				System.out.println(rs.getString("title"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs,pstmt,conn);
		}
		
	}
}
