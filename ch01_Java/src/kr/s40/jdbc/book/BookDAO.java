package kr.s40.jdbc.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.util.DBUtil;

public class BookDAO {
	//관리자 도서 등록
	public void insertBook(String bk_name,String bk_category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			//bk_num,name,category,regdate
			sql = "INSERT INTO sbook (bk_num,bk_name,bk_category)VALUES (sbook_seq.nextval,?,?)";
			//JDBC 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, bk_name);
			pstmt.setString(2, bk_category);
			//JDBC 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 도서를 등록했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//관리자 도서 목록 보기
	public void selectListBook() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM sbook ORDER BY bk_num DESC";
			//JDBC 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 4단계
			rs = pstmt.executeQuery();
			
			System.out.println("----------------------");
			if(rs.next()) {
				System.out.println("번호\t도서명\t카테고리\t등록일");
				do {
					System.out.print(rs.getInt("bk_num"));
					System.out.print("\t");
					System.out.print(rs.getString("bk_name"));
					System.out.print("\t");
					System.out.print(rs.getString("bk_category"));
					System.out.print("\t");
					System.out.println(rs.getDate("bk_regdate"));
					
				}while(rs.next());
			}else {
				System.out.println("표시할 도서가 없습니다.");
			}
			System.out.println("----------------------");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//관리자가 회원 목록 보기
	public void selectListMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member ORDER BY me_id DESC";
			//JDBC 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 4단계
			rs = pstmt.executeQuery();
			
			System.out.println("----------------------");
			
			if(rs.next()) {
				System.out.println("아이디\t비밀번호\t회원명\t전화번호\t가입일");
				do {
					System.out.print(rs.getString("me_id"));
					System.out.print("\t");
					System.out.print(rs.getString("me_passwd"));
					System.out.print("\t");
					System.out.print(rs.getString("me_name"));
					System.out.print("\t");
					System.out.print(rs.getString("me_phone"));
					System.out.print("\t");
					System.out.println(rs.getDate("me_regdate"));
					
				}while(rs.next());
			}else {
				System.out.println("표시할 회원 정보가 없습니다.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//관리자 대출 목록 보기(대출 및 반납의 모든 데이터 표시=대출 이력)//오류있나 확인해보기
	public void selectListReservation() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM reservation JOIN sbook USING(bk_num)"
					+ "JOIN member USING(me_id)"
					+ "ORDER BY re_num DESC";
			//JDBC 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 4단계
			rs = pstmt.executeQuery();
			
			System.out.println("----------------------");
			
			if(rs.next()) {
				System.out.println("번호\t반납상태\t도서번호\t회원ID\t대출날짜\t반납날짜");
				do {
					System.out.print(rs.getInt("re_num"));
					System.out.print("\t");
					System.out.print(rs.getInt("re_status"));
					System.out.print("\t");
					System.out.print(rs.getInt("bk_num"));
					System.out.print("\t");
					System.out.print(rs.getString("me_id"));
					System.out.print("\t");
					System.out.print(rs.getDate("re_regdate"));
					System.out.print("\t");
					System.out.println(rs.getDate("re_modifydate"));
					
				}while(rs.next());
			}else {
				System.out.println("표시할 목록이 없습니다.");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	//사용자 아이디 중복 체크(count가 0이면 미중복,count가 1이면 중복)--반환형
	public int checkId(String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT me_id FROM member WHERE me_id=?";//*,컬럼명이 중요하지 않음|행이 있는지만 중요
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = 1;//행이 있으면
			}//진입안하면 기본값이 0이어서 else를 안써도 됨
		}catch(Exception e) {
			count = 2;//오류 발생 |없으면 return이 0으로 뜸
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;//반환 하기전까지 오류
	}
	
	//사용자 회원 가입
	public void insertMember(String me_id,String me_passwd,String me_name,String me_phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO member (me_id,me_passwd,me_name,me_phone) VALUES (?,?,?,?)";//date는 디폴트라 제외,하니까 앞에 date를 제외한 컬럼명 명시
			//JDBC 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			pstmt.setString(2, me_passwd);
			pstmt.setString(3, me_name);
			pstmt.setString(4, me_phone);
			//JDBC 4단계
			pstmt.executeUpdate();
			System.out.println("회원 가입이 완료되었습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	//사용자 로그인 체크
	public boolean loginCheck(String me_id,String me_passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean flag = false;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT me_id FROM member WHERE me_id=? AND me_passwd=?";//me_id는 행있는지 식별로 의미X
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			pstmt.setString(2, me_passwd);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				flag = true;//인 경우에만 로그인
			}
			
		}catch(Exception e) {
			e.printStackTrace();//false반환해서 예외 추가X
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return flag;
	}
	
	//사용자 도서 대출 여부 확인(도서번호(bk_num)로 검색해서 re_status의 값이 0이면 대출 가능,1이면 대출 불가능) /reservation에서 검색
	
	//사용자 도서 대출 등록
	public void insertReservation(int bk_num,String me_id) {
		
	}
	//사용자 MY대출 목록 보기(현재 대출한 목록만 표시)
	public void selectMyList(String me_id) {
		
	}
	//사용자 반납 가능 여부(대출번호(re_num)와 회원아이디(me_id)를 함께 조회해서 re_status가 1인것은 반납 가능 re_status가 0이면 반납 불가능|이미 된거니까)
	
	//반납 처리
	public void ipdateReserVation(int re_num) {
		
	}
}
