package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {//멤버변수가 없어 하나만 만들어도 공유 가능함
	/*(14~20)
	 * 싱글턴 패턴은 생성자를 private으로 지정해서 외부에서 호출할 수 없도록 처리하고(변수가 없고 메서드 위주로 사용하기 때문에 싱글턴 패턴 가능)
	 * static 메서드를 호출해서 객체가 한 번만 생성되고 생성된 객체를 공유할 수 있도록 처리하는 방식을 의미함
	 */
	private static MemberDAO instance = new MemberDAO();//private 내부에서 호출가능,외부 불가능
	
	public static MemberDAO getInstance() {//get을 여러번 호출해도 한번 객체 생성되면 공유
		return instance;
	}
	
	private MemberDAO() {}
	
	//회원가입					자바빈에 데이터 담겨있어 개별적으로 안적어도됨
	public void insertMember(MemberVO member) throws Exception{//registerForm브라우저에서 작성하면 DAO로 데이터 들어옴
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO smember (num,id,name,passwd,email,phone) "
					+ "VALUES (smember_seq.nextval,?,?,?,?,?)";//reg_date는 default처리해서 명시안해도 자동으로 들어감
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);//끄집어내서 매핑
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPasswd());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getPhone());
			//SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {//e.printStackTrace();하면 예외가 발생해도 html페이지가 백지됨/그래서 에러페이지가 나오게 해야됨(예외발생한거 인지하게 던짐)
			throw new Exception(e);//예외문구 가져가 출력해야돼서 ()exception 생성자에 e전달
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//회원상세정보
	public MemberVO getMember(int num)throws Exception{//void는 콘솔에서 출력했을때/지금은jsp에서 출력으로 데이터 반환->반환한거에 데이터 꺼내서 jsp에 표시
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM smember WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setNum(rs.getInt("num"));
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setReg_date(rs.getDate("reg_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//아이디 중복 체크, 로그인 체크
	public MemberVO checkMember(String id)throws Exception{//아이디가 있다 하면 membervo객체에 데이터 담아서(로그인 체크도 처리하려고) 던지고 /중복안되어있으면 null
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM smember WHERE id=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, id);//아이디 하나라 담지않고 바로 전달
			//SQL문 실행(한개여서 while말고 if로)
			rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();//아이디가 있으면 객체 만들어서 전달
				member.setId(rs.getString("id"));
				member.setNum(rs.getInt("num"));
				member.setPasswd(rs.getString("passwd"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	//회원정보 수정
	public void updateMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE smember SET name=?,passwd=?,email=?,phone=? WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPhone());
			pstmt.setInt(5, member.getNum());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//회원탈퇴(회원정보 삭제)
	public void deleteMember(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM smember WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
