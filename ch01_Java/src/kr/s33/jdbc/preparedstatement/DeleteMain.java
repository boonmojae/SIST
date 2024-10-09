package kr.s33.jdbc.preparedstatement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;

import kr.util.DBUtil;

public class DeleteMain {
	public static void main(String[] args) {
		
		BufferedReader br = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));//프라이 키 받기위해 버퍼객체 생성
			System.out.print("번호:");
			int num = Integer.parseInt(br.readLine());
			
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "DELETE FROM test3 WHERE num=?";
			
			//JDBC 수행 3단계 :PreparedStatement객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//JDBC 수행 4단계 : SQL문을 실행해서 테이블의 행을 삭제
			int count = pstmt.executeUpdate();//삭제한 행의 개수를 반환/콘솔에서 작업해서 보기편하게 카운트값 받게한건데 안해도되긴함
			System.out.println(count + "개 행을 삭제했습니다.");//오토커밋으로 삭제한거 복원못함 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null,pstmt, conn);
			if(br!=null)try {br.close();} catch(IOException e) {}
		}
	}
}
