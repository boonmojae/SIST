package kr.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//추상메서드
	public String execute(HttpServletRequest request,HttpServletResponse response)//action타입을 사용해서 모든 모델클래스는 execute메서드만 사용할 수 있음
		throws Exception;
}
