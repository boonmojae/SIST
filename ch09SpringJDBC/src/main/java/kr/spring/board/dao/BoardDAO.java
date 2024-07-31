package kr.spring.board.dao;

import java.util.List;

import kr.spring.board.vo.BoardVO;
//DAO,Service는 다른 클래스에서 자원 호출되는데 이럴때는 지정한것만 호출될수 있게 구조를 잘 만들어야됨->인터페이스타입 사용하는 이유
//인터페이스를 사용하는데 없는 메서드를 만들어서 사용하면 호출안됨, 명시된것만 호출 가능(구조를 만들고 표준화시킨다고 할 수 있음)/틀이 있고 틀을 강요하는것이어서 중요함
//인터페이스(class로 만들어도 interface로 바꿔쓰면 됨)
public interface BoardDAO {//추상메서드 형태로 만들어 다른 클래스에서 implements하기
	public void insertBoard(BoardVO board);
	public int getBoardCount();
	public List<BoardVO> getBoardList(int startRow,int endRow);
	public BoardVO getBoard(int num);
	public void updateBoard(BoardVO board);
	public void deleteBoard(int num);//class말고 interface만든이유 - 얘가 구조 만들고 구조 사용하는걸 보증 BoardDAO사용하면 이 메서드만 사용할 수 있게
}
