package kr.spring.ch07.service;

import kr.spring.ch07.vo.LoginVO;

public class LoginService {					//던지는데 에러 안나게 하려면 try~catch하거나,throws LoginCheckException만들어야됨 - 메서드여서 throw함(11을 얘기하는건지 6인지)
	public void checkLogin(LoginVO vo) throws LoginCheckException{//LoginVO받고 데이터베이스 연동했다 가정
		//테스트용으로 userId와 password가 일치하면 로그인 처리 - 불일치하면 에러(던짐)
		if(!vo.getUserId().equals(vo.getPassword())) {
			//일치하지 않으면 여기로 진입!=인증실패 불일치
			System.out.println("인증 에러 - " + vo.getUserId());
			throw new LoginCheckException();
		}
	}
}
