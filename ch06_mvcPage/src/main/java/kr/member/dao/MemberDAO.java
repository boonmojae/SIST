package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	//싱글턴 패턴
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}

	//회원 가입
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;//하나당 sql문 하나로 해야 자원정리 따로 할 수 있음
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;//시퀀스를 읽어와야돼서 있음
		String sql = null;
		int num = 0;//시퀀스 번호 저장
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//오토 커밋 해제
			conn.setAutoCommit(false);//수작업으로 작업

			//회원 번호(mem_num) 생성
			sql ="SELECT zmember_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {//시퀀스로 행이 하나
				num = rs.getInt(1);//컬럼 인덱스 (zmember_seq.nextval)=1
			}

			sql = "INSERT INTO zmember (mem_num,id) VALUES (?,?)";//auth값은 자동으로 들어감
			pstmt2 = conn.prepareStatement(sql);//여기서부터 pstmt2로 꼭 명시
			//?에 데이터 바인딩
			pstmt2.setInt(1, num);//num=시퀀스 번호
			pstmt2.setString(2, member.getId());//아이디
			pstmt2.executeUpdate();
			//zmemeber_detail시작
			sql = "INSERT INTO zmember_detail (mem_num,name,passwd,phone,email,zipcode,address1,address2) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, num);
			pstmt3.setString(2, member.getName());
			pstmt3.setString(3, member.getPasswd());
			pstmt3.setString(4, member.getPhone());
			pstmt3.setString(5, member.getEmail());
			pstmt3.setString(6, member.getZipcode());
			pstmt3.setString(7, member.getAddress1());
			pstmt3.setString(8, member.getAddress2());
			pstmt3.executeUpdate();
			//SQL 실행시 모두 성공하면 commit
			conn.commit();

		}catch(Exception e) {
			//SQL문장이 하나라도 실패하면 rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//ID 중복 체크 및 로그인 처리(두개를 합치는건 선택사항,중복은 vo확인,로그인인 데이터 처리)
	public MemberVO checkMember(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;

		try {
			//inner조인을 하면 detail에 정보 없을때 zmember아이디 중복체크 못함
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//zmember와 zmember_detail 테이블을 조인할 때 누락된 데이터가 보여야 id 중복 체크 가능
			sql = "SELECT * FROM zmember LEFT OUTER JOIN zmember_detail USING(mem_num) WHERE id= ?";//zmember에서 누락 발생
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, id);
			//SQL문 실행
			rs = pstmt.executeQuery();

			if(rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setPasswd(rs.getString("passwd"));
				member.setPhoto(rs.getString("photo"));
				member.setEmail(rs.getString("email"));//회원 탈퇴시 필요

			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	//회원상세 정보(id(프라이머리키 말고 유니크하게만 사용)하면 join해서 정보 읽어와야되고 복잡,mem_num만들어져 있다면 이거로 하는게 더 좋음)
	public MemberVO getMember(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM zmember JOIN zmember_detail USING(mem_num) WHERE mem_num=?";//존재하는것만 가져오는거로 inner join사용
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setZipcode(rs.getString("zipcode"));
				member.setAddress1(rs.getString("address1"));
				member.setAddress2(rs.getString("address2"));
				member.setPhoto(rs.getString("photo"));
				member.setReg_date(rs.getDate("reg_date"));//가입일
				member.setModify_date(rs.getDate("modify_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	
	

	
	//=================================================
	
	//회원정보 수정
	public void updateMember(MemberVO member) throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zmember_detail SET name=?,phone=?,email=?,zipcode=?,address1=?,address2=?,modify_date=SYSDATE WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getZipcode());
			pstmt.setString(5, member.getAddress1());
			pstmt.setString(6, member.getAddress2());
			pstmt.setInt(7, member.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//비밀번호 수정
	public void updatePassword(String passwd,int mem_num)throws Exception{//mem_num에 로그인되어있는 user_num(여기에 이미mem_num있음)을 넘김
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zmember_detail SET passwd=? WHERE mem_num=?";
			//PreparedStatement 객체
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, passwd);//비밀번호 그래로 넣었으니까 passwd
			pstmt.setInt(2, mem_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//프로필 사진 수정(ajax처리)
	public void updateMyPhoto(String photo,int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zmember_detail SET photo=? WHERE mem_num=?";//인자 두개로 지금처럼,자바빈에 넣어서 처리해도 됨/photo 부분만 수정하는거니까 insert가 아니라 update함
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, photo);
			pstmt.setInt(2, mem_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);	
		}
	}

	//회원 탈퇴(회원정보 삭제) zmember-update/zmember_detail-delete
	public void deleteMember(int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		try{
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//auto commit 해체
			conn.setAutoCommit(false);
			//zmember의 auth값 변경
			sql = "UPDATE zmember SET auth=0 WHERE mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);//재가입을 못하게 하기 위해서 auth값만 변경
			pstmt.executeUpdate();
			//zmember_detail의 레코드 삭제
			sql = "DELETE FROM zmember_detail WHERE mem_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, mem_num);
			pstmt2.executeUpdate();
			//모든 SQL문의 실행이 성공하면 커밋
			conn.commit();//트랜잭션 처리 해야됨
		}catch(Exception e) {
			//SQL문이 하나라도 실패하면 롤백
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//관리자
	
	//전체 내용 개수, 검색 내용 개수
	public int getMemberCountByAdmin(String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";//검색기능으로 추가
		int count = 0;
		try {
			//zmember를 기준으로 정보 읽어옴 
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리	(link가능하게 하기 위해서 get방식 사용)/id구하는거니까 조인해서 검색해야됨(count)
				if(keyfield.equals("1")) sub_sql += "WHERE id LIKE '%' || ? || '%'";//확장성 때문에 대입연산자 사용/조건이 하나밖에 없을땐 =만해도됨
				else if(keyfield.equals("2")) sub_sql += "WHERE name LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += "WHERE email LIKE '%' || ? || '%'";
			}
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM zmember LEFT OUTER JOIN zmember_detail USING(mem_num) " + sub_sql;
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩 대신 ?에 1넣음
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, keyword);
			}
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	//목록, 검색 목록
	public List<MemberVO> getListMemberByAdmin(int start,int end,String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> list = null;
		String sql = null;
		String sub_sql = "";//검색할때 쓰는 용
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//검색과 목록 조건이 같아야됨
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리										id구하는거니까 조인해서 검색해야됨(count)
				if(keyfield.equals("1")) sub_sql += "WHERE id LIKE '%' || ? || '%'";//확장성 때문에 대입연산자 사용/조건이 하나밖에 없을땐 =만해도됨
				else if(keyfield.equals("2")) sub_sql += "WHERE name LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += "WHERE email LIKE '%' || ? || '%'";
			}
			
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM zmember LEFT OUTER JOIN zmember_detail USING(mem_num) "//ODRDER쪽이 제일먼저 실행되니까 + sql문
					+ " " + sub_sql +" ORDER BY mem_num DESC)a) WHERE rnum >= ? AND rnum <= ?";//?에 start,end가 전달
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);//1이 두개가 생기니까 변수 지정해서 사용
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			//ArrayList에 담아서 보냄
			list = new ArrayList<MemberVO>();
			while(rs.next()) {
				MemberVO member = new MemberVO();//데이터를 담아놓고 필요한것만 뽑기,필요한것만 담아서 데이터 읽어오기(지금은 이방법)
				member.setMem_num(rs.getInt("mem_num"));//링크걸때 mem_num이 필요
				member.setId(rs.getString("id"));
				member.setAuth(rs.getInt("auth"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setReg_date(rs.getDate("reg_date"));
				
				list.add(member);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//회원등급 수정
	public void updateMemberByAdim(int auth, int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zmember SET auth=? WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, auth);
			pstmt.setInt(2, mem_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
