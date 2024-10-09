package kr.s32.jdbc.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import kr.util.DBUtil;

public class InsertMain {
	public static void main(String[] args) {		//이미 같은 정보를 넣어서 출력하면 오류남
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			
			//SQL문 작성												?위치로 컬럼에 매핑/1,2,3순서를 가지고있음
			sql = "INSERT INTO test2 (id,name,age,reg_date) VALUES(?,?,?,SYSDATE)";//prepared는 직접 대입안하고 자리만 잡아놓음
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);//위에 만들었던 sql문장 넣어줌/메서드는 동사형으로 보기해려고 d뺌,클래스는 명사형
			//?에 데이터를 바인딩(묶)
			pstmt.setString(1,"she'g");//1번 ?에 데이터 전달
			pstmt.setString(2, "김연아");//2번 ?에 데이터 전달
			pstmt.setInt(3, 30);//3번 ?에 데이터 전달
			
			//JDBC 수행 4단계 : SQL문을 실행해서 테이블에 행을 추가
			int count = pstmt.executeUpdate();//위에서 sql문장 객체 생성할때 전달해서 밑에서는 넣으면 안됨
			System.out.println(count + "개 행을 추가했습니다.");
		}catch(Exception e) {
				e.printStackTrace();
		}finally {
				//자원정리
			DBUtil.executeClose(null,pstmt,conn);
			}
	}
}
