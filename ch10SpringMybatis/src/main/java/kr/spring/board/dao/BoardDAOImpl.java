package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.spring.board.vo.BoardVO;

@Repository//이거 안넣으면 자동 스캔 대상이 안됨ㄴ
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSession;//얘도 메서드를 가지고 있어서 호출하면서 xml에 명시한걸 읽어오게해야됨
	
	@Override
	public void insertBoard(BoardVO board) {
		sqlSession.insert("insertBoard",board);//id,데이터
		
	}
	//id명시해서 xml에 있는 id랑 매핑시켜 sql문 읽어옴->service가 주입받고 트랜잭션 처리해 controller에 반환
	@Override
	public int selectBoardCount() {//자동변환돼서 Integer,int 상관없음
		//selectOne 객체 형태로 반환하는데 int라고 써도 큰 문제 없음(int 0,Integer null로 초기화)
		return sqlSession.selectOne("selectBoardCount");//하나의 레코드로 one/(xml태그에 있는 id=식별자)를 이용해서 sql 읽어오기 위해 selectBoardCount라고 식별자 명시
	}

	@Override
	public List<BoardVO> selectBoardList(Map<String, Integer> map) {
										//id		  데이터	로 ?에 전달함
		return sqlSession.selectList("selectBoardList",map);//selectList에 "selectBoardList",map를 전달/map은 start,end=10이런식으로 들어있고 이걸 sql문으로 전달
	}

	@Override
	public BoardVO selectBoard(int num) {
		return sqlSession.selectOne("selectBoard",num);//xml로가서 selectBoard넣고 sql작성
	}

	@Override
	public void updateBoard(BoardVO board) {
		sqlSession.update("updateBoard",board);
		
	}

	@Override
	public void deleteBoard(int num) {
		sqlSession.delete("deleteBoard",num);
	}

}
