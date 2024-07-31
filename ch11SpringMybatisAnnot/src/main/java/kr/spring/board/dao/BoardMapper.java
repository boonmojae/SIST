package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.board.vo.BoardVO;
//annotation은 mapper인터페이스만 생성하고/클래스 만들어 객체 생성하는건 자동으로됨
//짧은건 어노테이션,복잡한건(서브쿼리,조인)xml에 명시
public interface BoardMapper {
	public void insertBoard(BoardVO board);
	@Select("SELECT COUNT(*) FROM aboard")//이 메서드 만들때 sql문장 가져가서 처리/xml은 반환타입 명시해야되는데 메서드 위에 명시하면 반환타입 바로 알 수 있음+인자도
	public int selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String,Integer> map);//xml하고 어노테이션 또 만들면 이중작업 충돌(둘중 한개만 사용)
	@Select("SELECT * FROM aboard WHERE num=#{num}")//#{num}=num(17)
	public BoardVO selectBoard(int num);
	@Update("UPDATE aboard SET writer=#{writer},title=#{title},content=#{content} WHERE num=#{num}")//#{num}자바빈에 있는 프로퍼티를 의미
	public void updateBoard(BoardVO vo);
	@Delete("DELETE FROM aboard WHERE num=#{num}")
	public void deleteBoard(int num);
}
