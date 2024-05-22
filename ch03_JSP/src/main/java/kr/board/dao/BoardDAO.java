package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.util.DBUtil;

public class BoardDAO {
	//싱클턴 패턴
	private static BoardDAO instance = new BoardDAO();//instance private이어서 내부에서만 호출/객체가 생성되면서 주소 갖게됨(한번 생성해서 재활용)
	
	public static BoardDAO getInstance() {//유일하게 외부 호출
		return instance;
	}
	private BoardDAO() {}
	
	//글 저장
	public void insert(BoardVO boardVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO mboard (num,title,name,passwd,content,ip) VALUES (mboard_seq.nextval,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩(자바빈에서 뽑아서 사용)
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getContent());
			pstmt.setString(3, boardVO.getName());
			pstmt.setString(4, boardVO.getContent());
			pstmt.setString(5, boardVO.getIp());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글의 총 개수(페이지)
	public int getCount()throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성(집합함수를 이용해서 정수형태로 받음/집합함수는 레코드 여러개 될수없고 하나로 합쳐서 처리해야됨)
			sql = "SELECT COUNT(*) FROM mboard";//하나의 레코드 만들어지니까 while말고 if로 처리
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {	  //컬럼인덱스
				count = rs.getInt(1);//컬럼명에 (count(*)은 기호가 있어서 알리아스 사용,근데 컬럼명 한개 있을땐 컬럼인덱스 사용하는게 젤 좋음),컬럼인덱스이기 때문에 정수 1로 사용해야됨
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	//글 목록								로넘 시작,끝
	public List<BoardVO> getList(int startRow,int endRow) throws Exception{//연산해서 받을꺼니까 rnum=?로 명시
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM mboard ORDER BY num DESC)a) WHERE rnum >=? AND rnum <=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setNum(rs.getInt("num"));
				boardVO.setTitle(rs.getString("title"));
				boardVO.setName(rs.getString("name"));
				boardVO.setReg_date(rs.getDate("reg_date"));
				//자바빈을 ArrayList에 저장
				list.add(boardVO);
				
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//글 상세(수정,비밀번호)
	public BoardVO getBoard(int num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM mboard WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			//행이 없으면 boardvo가 null반환해서 else만들필요 없음
			if(rs.next()) {
				board = new BoardVO();
				board.setNum(rs.getInt("num"));//getInt컬럼명,인덱스 사용가능(우리는 컬럼명으로 사용)
				board.setTitle(rs.getString("title"));
				board.setName(rs.getString("name"));
				board.setPasswd(rs.getString("passwd"));
				board.setContent(rs.getString("content"));
				board.setIp(rs.getString("ip"));
				board.setReg_date(rs.getDate("reg_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return board;
	}
	//글 수정
	public void update(BoardVO boardVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE mboard SET title=?,name=?,content=?,ip=? WHERE num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, boardVO.getTitle());
			pstmt.setString(2, boardVO.getName());
			pstmt.setString(3, boardVO.getContent());
			pstmt.setString(4, boardVO.getIp());
			pstmt.setInt(5, boardVO.getNum());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 삭제
	public void delete(int num)throws Exception{//자바빈으로 보낸게 아니라 num만
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM mboard WHERE num=?";
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
