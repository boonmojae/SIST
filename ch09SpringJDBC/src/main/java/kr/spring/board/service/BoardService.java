package kr.spring.board.service;

import java.util.List;

import kr.spring.board.vo.BoardVO;
//인터페이스는 접근제한을 줘서 데이터 베이스 사용할때 사용/다른 객체로 넘어갈때는 이 메서드만 사용할 수 있는 보증이 되어야됨
//Controller에 주입되고 호출할때 인터페이스 정의되어있는것만 사용할 수 있기때문에 우리가 보증하는것만 사용하고 그 외의 접근 제한을 하는 역할
//명칭이 달라도됨 근데 이름 바꾸면서 시간들일 필요없음 그냥 똑같이해서 사용하셈
public interface BoardService {
	public void insertBoard(BoardVO board);
	public int getBoardCount();
	public List<BoardVO> getBoardList(int startRow,int endRow);
	public BoardVO getBoard(int num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(int num);
}
