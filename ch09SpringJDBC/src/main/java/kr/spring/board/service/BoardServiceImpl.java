package kr.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.board.dao.BoardDAO;
import kr.spring.board.vo.BoardVO;
//트랜잭션 처리 하기때문에 여기에 꼭 연결해야됨->controller에 반환도 하고
@Service//컨테이너에 들어가야 오토스캔 대상이 되는거니까
@Transactional//전체 메서드에 트랜잭션 처리 자동으로 하고싶으면//클래스,메서드에 처리할 수 있음 근데 전체에 할거니까 클래스에
public class BoardServiceImpl implements BoardService{
	//서비스에서 조합하면서 트랜잭션 처리/jdbc를 다 메서드로 만들어서 서비스에서 조합하는게 필요함(jsp는 DAO안에서 트랜잭션 처리해서 필요없음)
	//객체 생성하고 다른곳에 전달되는거면 일반적으로 인터페이스 타입으로 함
	@Autowired
	private BoardDAO boardDAO;
	
	//트랜잭션 대상
	@Override
	public void insertBoard(BoardVO board) {//메서드 여러개 불러서 sql이 여러개여도 트랜잭션 처리 가능하게
		boardDAO.insertBoard(board);//이거말고 여러 메서드가 있어도 묶어서 트랜잭션 처리 /얘도 묶어서 controller에서 호출됨(반환) 그래서 interface타입 사용	
	}

	@Override
	public int getBoardCount() {
		return boardDAO.getBoardCount();
	}

	@Override
	public List<BoardVO> getBoardList(int startRow, int endRow) {
		//변수 만들 필요없이 바로 반환
		return boardDAO.getBoardList(startRow, endRow);
	}

	@Override
	public BoardVO getBoard(int num) {
		return boardDAO.getBoard(num);
	}
	
	@Override
	public void updateBoard(BoardVO board) {
		boardDAO.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(int num) {
		boardDAO.deleteBoard(num);
	}

}
