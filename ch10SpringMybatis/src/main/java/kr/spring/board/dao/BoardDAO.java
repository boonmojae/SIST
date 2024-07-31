package kr.spring.board.dao;

import java.util.List;
import java.util.Map;

import kr.spring.board.vo.BoardVO;

//구조를 넣기위해서 interface
public interface BoardDAO {
	public void insertBoard(BoardVO board);
	public int selectBoardCount();
	public List<BoardVO> selectBoardList(Map<String,Integer> map);//Map이라는 객체에 담아서 보냄(start,endrow로 안하고)/마이바티스는 하나의 인자만 사용 2,3개 보낼땐 Map객체로 묶어서 보냄(인자가 하나니까 반환타입도 하나)
	public BoardVO selectBoard(int num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(int num);
}
