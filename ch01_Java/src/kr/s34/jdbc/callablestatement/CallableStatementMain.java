package kr.s34.jdbc.callablestatement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;

import kr.util.DBUtil;

public class CallableStatementMain {//프로시저
	public static void main(String[] args) {
		BufferedReader br = null;
		Connection conn = null;
		CallableStatement cstmt = null;
		String sql = null;
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("이름(SMITH,PETER,JOHN):");//대문자로 적어야됨
			String name = br.readLine();
			System.out.print("급여 인상률:");
			float rate = Float.parseFloat(br.readLine());
			//JDBC 수행 1,2 단계
			conn = DBUtil.getConnection();
			
			//프로시저 호출 문장 작성
			sql = "{call adjust(?,?)}";
			
			//JDBC 수행 3단계 : CallableStatement 객체 생성
			cstmt = conn.prepareCall(sql);
			//?에 데이터 바인딩
			cstmt.setString(1, name);
			cstmt.setFloat(2, rate);
			
			//JDBC 수행 4단계 : 프로시저 호출
			cstmt.executeUpdate();
			System.out.println("급여 정보를 수정했습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(cstmt, conn);
			if(br!=null)try {br.close();}catch(IOException e) {}
		}
		
		
	}
}
