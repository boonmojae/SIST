package kr.s33.jdbc.preparedstatement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

public class SelectSearchMain {
	public static void main(String[] args) {
		BufferedReader br = null;
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("제목 검색어:");
			String keyword = br.readLine();
			
			System.out.println("-----------------");
			
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "SELECT * FROM test3 WHERE title LIKE '%' || ? || '%'";//LIKE는 가변문자 사용,앞뒤에 없으면 =로 인식
			
			//JDBC 수행 3단계 :preparedStatement객체 생성
			pstmt = conn.prepareStatement(sql);
			
			//?에 데이터 바인딩
			pstmt.setString(1, keyword);
			
			//JDBC 수행 4단계 : SQL문을 테이블에 반영해서 결과집합을 ResultSet에 담아서 반환
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
			System.out.println("번호\t작성자\t등록일\t\t제목");//검색을 하면 여러개가 나올땐 while
			do {
				System.out.print(rs.getInt("num"));
				System.out.print("\t");
				System.out.print(rs.getString("name"));
				System.out.print("\t");
				System.out.print(rs.getDate("reg_date"));
				System.out.print("\t");
				System.out.println(rs.getString("title"));
				
				}while(rs.next());//실행되고 조건체크 할 수 있게 while말고 do while사용 while사용하면 if에서 조건체크하고 while에서 또 체크해서 넘어가는 행 발생함
			}else {
				System.out.println("검색된 데이터가 없습니다.");//이걸 명시하려면 if
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null,pstmt, conn);
			if(br!=null)try {br.close();} catch(IOException e) {}
		}
	}
}
