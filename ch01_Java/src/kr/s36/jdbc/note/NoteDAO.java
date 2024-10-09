package kr.s36.jdbc.note;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

/*
 * DAO : Data Access Object
 * 		데이터베이스의 데이터를 전문적으로 호출하고 제어하는 객체
 */
//데이터 베이스 연동하는 클래스
public class NoteDAO {
	//글쓰기
	public void insertInfo(String name,String passwd,String subject,String content,String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			
			//SQL문 작성                      /num에 할당/
			sql = "INSERT INTO note VALUES (note_seq.nextval,?,?,?,?,?,SYSDATE)";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, name);
			pstmt.setString(2, passwd);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.setString(5, email);
			
			//JDBC 수행 4단계 :SQL문을 실행해서 1건의 레코드를 생성
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행을 삽입했습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null,pstmt,conn);
		}
	}
	//목록 보기
	public void selectInfo() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM note ORDER BY num DESC";//목록은 정렬하는게 좋음
			//JDBC 수행 3단계 :PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계 : SQL문을 실행해서 테이블로부터 결과집합을 받고 ResultSet에 담아서 반환
			rs = pstmt.executeQuery();
			
			System.out.println("---------------");
			
			if(rs.next()) {//행안으로 진입 do while문 사용하기
				System.out.println("번호\t이름\t작성일\t\t제목");//num은 반드시 있어야됨(이거 있어야 상세정보에 연결)모든 데이터를 목록에 표시할 수 없어서 일부 선택
				do {
					System.out.print(rs.getInt("num"));
					System.out.print("\t");
					System.out.print(rs.getString("name"));
					System.out.print("\t");
					System.out.print(rs.getDate("reg_date"));
					System.out.print("\t");
					System.out.println(rs.getString("subject"));
					}while(rs.next());//위에서 조건체크해서 실행하고 조건체크 while사용하면 추출안했는데 커서 이동해서 데이터 누락됨
			}else {
				System.out.println("표시할 데이터가 없습니다.");//문구데이터 넣고 싶으면 if else하고 do while
			}
			
			System.out.println("---------------");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null,pstmt,conn);
		}
	}
	//상세글 보기					프라이키 전달해 여러데이터가 나오는게 아님 
	public void selectDetailInfo(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM note WHERE num=?";//프라이머리키여서 하나의 행이거나 잘못입력할땐 행 없음
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("글번호:" + rs.getInt("num"));//num전달받아서 정보처리
				System.out.println("이름:" + rs.getString("name"));
				System.out.println("비밀번호:" + rs.getString("passwd"));
				System.out.println("제목:" + rs.getString("subject"));
				System.out.println("내용:" + rs.getString("content"));
				
				String email = rs.getString("email");//null이면 나오지 않고 비어있게 조건체크
				if(email==null) email ="";
				
				System.out.println("이메일:" + email );
				System.out.println("작성일:" +rs.getDate("reg_date"));
				
			}else {
				System.out.println("표시할 데이터가 없습니다.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 수정
	public void updateInfo(int num,String name,String passwd,String subject,String content,String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {//try에 연동해서 작업하는거
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE note SET name=?,passwd=?,subject=?,content=?,email=? WHERE num=?";//where절 앞에 ,안하기
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);//sql넘김
			//?에 데이터 바인딩
			pstmt.setString(1, name);
			pstmt.setString(2, passwd);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			pstmt.setString(5, email);
			pstmt.setInt(6, num);
			
			//JDBC 수행 4
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행을 수정했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 삭제
	public void deleteInfo(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM note WHERE num=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, num);
			//JDBC 수행 4단계 : SQL문을 테이블에 반영해서 하나의 행을 삭제
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 행을 삭제했습니다.");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
