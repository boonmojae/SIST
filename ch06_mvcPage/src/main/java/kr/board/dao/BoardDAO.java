package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardFavVO;
import kr.board.vo.BoardReplyVO;
import kr.board.vo.BoardVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class BoardDAO {
	//싱글턴 패턴
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	private BoardDAO() {}
	
	//글 등록
	public void insertBoard(BoardVO board)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO zboard (board_num,title,content,filename,ip,mem_num) VALUES (zboard_seq.nextval,?,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getFilename());
			pstmt.setString(4, board.getIp());
			pstmt.setInt(5, board.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//총 글의 개수, 검색 개수
	public int getBoardCount(String keyfield, String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql += "WHERE title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "WHERE id LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += "WHERE content LIKE '%' || ? || '%'";
			}
			
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM zboard JOIN zmember USING(mem_num)" + sub_sql;
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
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

	//글 목록, 검색 글 목록
	public List<BoardVO> getListBoard(int start, int end, String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				//검색 처리
				if(keyfield.equals("1")) sub_sql += "WHERE title LIKE '%' || ? || '%'";
				else if(keyfield.equals("2")) sub_sql += "WHERE id LIKE '%' || ? || '%'";
				else if(keyfield.equals("3")) sub_sql += "WHERE content LIKE '%' || ? || '%'";
			}
			
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM zboard JOIN zmember USING(mem_num) " + sub_sql
					+ " ORDER BY board_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, keyword);
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				board.setTitle(StringUtil.useNoHTML(rs.getString("title")));//HTML태그를 허용하지 않음
				board.setHit(rs.getInt("hit"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setId(rs.getString("id"));//작성자의 아이디
				
				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//글 상세
	public BoardVO getBoard(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			//(주의)회원 탈퇴하면 zmember_detail의 레코드를 지우기 때문에 조인시 데이터 누락 방지를 위해 OUTER JOIN을 사용함
			sql = "SELECT * FROM zboard JOIN zmember USING(mem_num) LEFT OUTER JOIN zmember_detail USING(mem_num) WHERE board_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			//프라이머리키 전달해서 한건의 데이터만 나옴
			if(rs.next()) {
				board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				//수정폼에서도 동일 코드를 사용하기 때문에 StringUtil명시하지 않음(변환되지않아야돼서)
				board.setTitle(rs.getString("title"));
				//수정폼에서도 동일 코드를 사용하기 때문에 StringUtil명시하지 않음
				board.setContent(rs.getString("content"));
				board.setHit(rs.getInt("hit"));
				board.setReg_date(rs.getDate("reg_date"));
				board.setModify_date(rs.getDate("modify_date"));
				board.setFilename(rs.getString("filename"));
				//로그인한 회원번호와 조건 체크를 해야 하기 때문에 mem_num이 필요
				board.setMem_num(rs.getInt("mem_num"));
				//겉으로 식별하는건 아이디 이니까
				board.setId(rs.getString("id"));
				board.setPhoto(rs.getString("photo"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return board;
	}
	
	//조회수 증가
	public void updateReadcount(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zboard SET hit=hit+1 WHERE board_num=?";//원래 데이터에 +1하고 다시 대입(새로고침하면 숫자가 계속 올라감)/한번 진입하고 조회수를 1번으로만 하게하려면 쿠키사용,테이블에 정보 저장해서(로그인한 상태여야됨)
			//preparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1,board_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//파일 삭제(글은 그대로)
	public void deleteFile(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성(파일명만 지우면 됨)
			sql = "UPDATE zboard SET filename='' WHERE board_num=?";//파일에 빈문자열 대입하면 기존의 파일이 지워짐
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 수정
	public void updateBoard(BoardVO board)throws Exception{
		//글 수정이 title,content만 있음
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";//파일을 교체하지만 교체하지 않는 경우를 대비
		int cnt = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//filename이 있을경우에만 가변적으로 사용
			if(board.getFilename()!=null && !"".equals(board.getFilename())) {
				sub_sql += ",filename=?";
			}
			//SQL문 작성
			sql = "UPDATE zboard SET title=?,content=?,modify_date=SYSDATE,ip=?" + sub_sql + " WHERE board_num=?";//결합되는거여서 WHERE앞에 공백
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, board.getTitle());
			pstmt.setString(++cnt, board.getContent());
			pstmt.setString(++cnt, board.getIp());
			if(board.getFilename()!=null && !"".equals(board.getFilename())) {//null,비어있는거 체크
				pstmt.setString(++cnt, board.getFilename());
			}
			pstmt.setInt(++cnt, board.getBoard_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 삭제
	public void deleteBoard(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//오토커밋 해제
			conn.setAutoCommit(false);
			
			//좋아요 삭제(포링키로 자식을 먼저 삭제해야 부모글 삭제)
			//댓글 삭제
			//부모글 삭제
			sql = "DELETE FROM zboard WHERE board_num=?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, board_num);
			pstmt3.executeUpdate();
			
			//예외 발생 없이 정상적으로 SQL문 실행
			conn.commit();
		}catch(Exception e) {
			//예외 발생
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//좋아요 등록-getfav,js파일로 ajax통신함 3
	public void insertFav(BoardFavVO favVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO zboard_fav (board_num,mem_num) VALUES (?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, favVO.getBoard_num());
			pstmt.setInt(2, favVO.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();//좋아요가 등록.토글형태로 반대로 적용됨->삭제쪽으로 이동
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//좋아요 개수-제일 먼저 처리1
	public int selectFavCount(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM zboard_fav WHERE board_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
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
	
	//회원번호와 게시물 번호를 이용한 좋아요 정보
	//(회원이 게시물을 호출했을 때 졸아요 선택 여부 표시)2
	public BoardFavVO selectFav(BoardFavVO favVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardFavVO fav = null;//여기에 정보를 담아서 보냄
		String sql = null;
		try {
			//커넥션풀로부처 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM zboard_fav WHERE board_num=? AND mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, favVO.getBoard_num());
			pstmt.setInt(2, favVO.getMem_num());
			//SQL문 실행
			rs = pstmt.executeQuery();
			//토글로 동작하려면 행이 유니크하게 들어가야됨 mem_num이 한개거나 없거나 해야됨
			if(rs.next()) {
				fav = new BoardFavVO();
				fav.setBoard_num(rs.getInt("board_num"));
				fav.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return fav;
	}
	//좋아요 삭제4/정보를 담아서 보내면 삭제-좋아요 등록,삭제 동시에 해야되고 writeFavAction에서 처리
	public void deleteFav(BoardFavVO favVO)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM zboard_fav WHERE board_num=? AND mem_num=?";//두가지를 동시 체크해 삭제
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, favVO.getBoard_num());
			pstmt.setInt(2, favVO.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//내가 선택한 좋아요 목록(마이페이지에 표시)-toggle형태 5
	//페이지처리 기본적으로,회원번호를 넘겨서 검색해야돼서 rownum처리
	public List<BoardVO> getListBoardFav(int start, int end, int mem_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql =  null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성-게시판에 데이터 표시해야돼서 join해야됨
			//(주의)zboard_fav의 회원번호(좋아요 클릭한 회원번호)로 검색되어야 하기 때문에 f.mem_num으로 표기해야 함
			//mem_num=b.mem_num은 작성자/mem_num=f.mem_num좋아요 클릭
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT * FROM zboard b JOIN zmember m USING(mem_num) "
					+ "JOIN zboard_fav f USING(board_num) WHERE f.mem_num=? ORDER BY board_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoard_num(rs.getInt("board_num"));
				board.setTitle(StringUtil.useNoHTML(rs.getString("title")));
				board.setReg_date(rs.getDate("reg_date"));
				board.setId(rs.getString("id"));
				
				list.add(board);//member-myPage이동
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//댓글 등록(BoardReplyVO생성후)
	public void insertReplyBoard(BoardReplyVO boardReply)throws Exception{//자바빈을 만들어서 여기다 데이터 넣어서 보내면 됨
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성								re_num빼고 다 자바빈에 담겨있음
			sql = "INSERT INTO zboard_reply (re_num,re_content,re_ip,mem_num,board_num) VALUES (zreply_seq.nextval,?,?,?,?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, boardReply.getRe_content());
			pstmt.setString(2, boardReply.getRe_ip());
			pstmt.setInt(3, boardReply.getMem_num());
			pstmt.setInt(4, boardReply.getBoard_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//댓글 개수
	public int getReplyBoardCount(int board_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM zboard_reply WHERE board_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			//집합함수 이기때문에 한건 board_num 하나에 딸려있는 레코드 한개 읽어옴
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
	//댓글 목록
	public List<BoardReplyVO> getListReplyBoard(int start, int end, int board_num)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardReplyVO> list = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성(아이디 읽어오려고 zmember 조인)
			sql = "SELECT * FROM (SELECT a.*,rownum rnum FROM (SELECT * FROM zboard_reply JOIN zmember USING(mem_num) "
					+ "WHERE board_num=? ORDER BY re_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, board_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardReplyVO>();
			while(rs.next()) {
				BoardReplyVO reply = new BoardReplyVO();//여기에 데이터 넣어서 전달
				reply.setRe_num(rs.getInt("re_num"));
				//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
				reply.setRe_date(DurationFromNow.getTimeDiffLabel(rs.getString("re_date")));//하루전,10일전 가공해서 전달하려고 String처리
				
				if(rs.getString("re_modifydate")!=null) {//null이 전달되면 에러나서 조건체크로 빼야됨(duration할때만)
					reply.setRe_modifydate(DurationFromNow.getTimeDiffLabel(rs.getString("re_modifydate")));
				}
				reply.setRe_content(StringUtil.useBrNoHTML(rs.getString("re_content")));//줄바꿈 허용
				reply.setBoard_num(rs.getInt("board_num"));
				reply.setMem_num(rs.getInt("mem_num"));//작성자 회원번호
				reply.setId(rs.getString("id"));//작성자 아이디-조인해서 가져올 수 있음
				
				//ArrayList에 담기
				list.add(reply);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	//댓글 상세(댓글 수정,삭제시 작성자 회원번호 체크 용도로 사용)
	public BoardReplyVO getreplyBoard(int re_num)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardReplyVO reply = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM zboard_reply WHERE re_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, re_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply = new BoardReplyVO();
				reply.setRe_num(rs.getInt("re_num"));
				reply.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return reply;
	}
	//댓글 수정
	public void updateReplyBoard(BoardReplyVO reply)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE zboard_reply SET re_content=?,re_modifydate=SYSDATE,re_ip=? WHERE re_num=?";//ip자바빈에 넣어서 보낼거라 셋팅하면됨/실제로 클라이언트에서 사용자가 보내는건 re_num밖에 없음
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, reply.getRe_content());
			pstmt.setString(2, reply.getRe_ip());
			pstmt.setInt(3, reply.getRe_num());
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//댓글 삭제
	
}
