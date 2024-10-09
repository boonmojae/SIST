package kr.s38.jdbc.score;//사용자 정의 예외클래스

public class ScoreValueException extends Exception{//자료형이 중요 해당예외가 있을때 캐치블럭으로
	public ScoreValueException(String message) {
		super(message);
	}
}
