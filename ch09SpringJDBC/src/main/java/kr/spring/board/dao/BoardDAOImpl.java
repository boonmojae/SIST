package kr.spring.board.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

//여기서 호출하면 보증되어있는 BoardDAO의 메서드만 활용함,여기에 다른거 추가해도 사용못함
//인터페이스가 구현될 클래스
@Repository//servlet-context.xml의 자동스캔 대상이 되려면 @Repository=@component와 같은 기능+인데 dao에선 @Repository사용함?
public class BoardDAOImpl implements BoardDAO{
	//static한 상수 만들기/마이바티스는 xml에서 정의함
	private static final String INSERT_SQL = "INSERT INTO aboard (num,writer,title,passwd,content,reg_date) VALUES (aboard_seq.nextval,?,?,?,?,SYSDATE)";//미리 sql문 정의함
	
	//페이지 처리(동작되면서 총카운트 보냄)
	private static final String SELECT_COUNT_SQL = "SELECT COUNT(*) FROM aboard";
	
	//sql을 메서드를 넣어서 한번에 목록처리 못함/한건의 레코드를 읽어와서 자바빈에 매핑해야됨 -> 목록으로 활용
	private static final String SELECT_LIST_SQL = "SELECT * FROM (SELECT a.*, rownum rnum FROM("
			+ "SELECT * FROM aboard ORDER BY reg_date DESC)a) WHERE rnum >= ? AND rnum <= ?";
	
	//상세보기
	private static final String SELECT_DETAIL_SQL = "SELECT * FROM aboard WHERE num=?";
	
	//수정
	private static final String UPDATE_SQL = "UPDATE aboard SET writer=?,title=?,content=? WHERE num=?";
	
	//삭제
	private static final String DELETE_SQL = "DELETE FROM aboard WHERE num=?";
	
	//하나의 레코드 데이터를 자바빈에 매핑(목록처리할땐 얘를 여러번 돌림)
	private RowMapper<BoardVO> rowMapper = new RowMapper<BoardVO>() {
		public BoardVO mapRow(ResultSet rs, int rowNum)throws SQLException{//ResultSet에 있는 데이터를 받아 mapRow에 담음/rowNum은 레코드 번호가 오면 식별할 수 있게 해줌
			
			BoardVO board = new BoardVO();
			board.setNum(rs.getInt("num"));
			board.setWriter(rs.getString("writer"));
			board.setTitle(rs.getString("title"));
			board.setPasswd(rs.getString("passwd"));
			board.setContent(rs.getString("content"));
			board.setReg_date(rs.getDate("reg_date"));
			
			return board;
		}
	};	
	
	@Autowired//가 setter 자동 생성해서 만들필요X
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertBoard(BoardVO board) {//데이터는 BoardVO에 담겨오고 그걸 Template이 처리	/insert,update,delete때 이 작업 사용,그 외에는 수작업으로 작성해야됨
		jdbcTemplate.update(INSERT_SQL,new Object[] {board.getWriter(),board.getTitle(),board.getPasswd(),board.getContent()});//두번째 인자가 오브젝트 배열,로 데이터 담음
		
	}

	@Override
	public int getBoardCount() {//Integer로 해도 자동변환됨/정수면 상관X,값이 없어 0,null중에서 사용할때
		//한건의 데이터 읽어와서 정수값 반환(하나의 값,컬럼을 받음)
		return jdbcTemplate.queryForObject(SELECT_COUNT_SQL, Integer.class);//반환할때 Integer로 반환(뒤에는 반환 타입)
	}

	@Override
	public List<BoardVO> getBoardList(int startRow, int endRow) {//query내부에서 list형태로 만들어서 반환
		List<BoardVO> list = jdbcTemplate.query(SELECT_LIST_SQL,new Object[] {startRow,endRow},rowMapper);//rowMapper객체/sql에 전달할 문자를 배열로 처리
		return list;//반환하면 service쪽에서 받음
	}

	@Override
	public BoardVO getBoard(int num) {									//num 하나의 데이터 ?
		return jdbcTemplate.queryForObject(SELECT_DETAIL_SQL, new Object[] {num},rowMapper);//num은 기본값, 자바빈 한건의 데이터 ResultSet에서 받아서 rowMapper추가?
	}

	@Override
	public void updateBoard(BoardVO board) {
		jdbcTemplate.update(UPDATE_SQL,new Object[] {board.getWriter(),board.getTitle(),board.getContent(),board.getNum()});//Object배열 만들어서 넘김
		
	}

	@Override
	public void deleteBoard(int num) {				//하나의 데이터 ?
		jdbcTemplate.update(DELETE_SQL,new Object[] {num});//이건 DAO작업->service로 이동해서 작업하기
		
	}

}
