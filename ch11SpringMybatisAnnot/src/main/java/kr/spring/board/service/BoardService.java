package kr.spring.board.service;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardVO;
//xml하고 어노테이션 또 만들면 이중작업 충돌(둘중 한개만 사용)
public interface BoardService {
	public void insertBoard(BoardVO board);
	public int selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String,Integer> map);
	public BoardVO selectBoard(int num);
	public void updateBoard(BoardVO vo);
	public void deleteBoard(int num);
}
