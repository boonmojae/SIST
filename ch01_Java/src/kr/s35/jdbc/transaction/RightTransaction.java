package kr.s35.jdbc.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.util.DBUtil;

public class RightTransaction {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;
		try {
			
			//복수의 SQL문을 실행할 경우 오토 커밋을 해제하고 수작업으로 트랜잭션 처리함
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			
			//오토 커밋 해체
			conn.setAutoCommit(false);
			
			//SQL문 작성
			//하나의 트랜잭션 형성해 작업 단위가 됨_근데 오토커밋으로 개별적 커밋돼서 2개만 데이터가 등록됨(=데이터 왜곡)
			sql = "INSERT INTO test1 VALUES ('ANNIE',10)";//테스트 용으로 넣음/문장 3개 ?넣으면 길어져서
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.executeUpdate();
			
			sql = "INSERT INTO test1 VALUES ('MARIA',20)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.executeUpdate();
			
			//테스트용으로 잘못된 SQL문 작성
			sql = "INSERT INTO test1 VALUES ('SUZAN',30";//하나라도 예외발생하면 ROLLBACK 시킴 
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.executeUpdate();
			
			//정상적으로 작업이 완료되면 commit
			conn.commit();
			
			System.out.println("작업 완료~");
			
		}catch(Exception e) {
			e.printStackTrace();
			//예외가 발생할 경우 rollback
			try {
				conn.rollback();
			}catch(SQLException e2) {//e라는 인자가 이미있어서 오류남
				e2.printStackTrace();
			}
		}finally {
			//자원정리
			DBUtil.executeClose(null,pstmt3,null);
			DBUtil.executeClose(null,pstmt2,null);
			DBUtil.executeClose(null,pstmt1,conn);
		}
	}
}

