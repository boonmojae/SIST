package kr.spring.ch04.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	@RequestMapping("/cookie/make.do")//URL 호출메서드 매핑
	public String make(HttpServletResponse response) {//서블릿에서 사용하는 객체를 인자->메서드가 구동될때 전달받을수 있음
		//쿠키를 생성해서 클라이언트에 전송
		response.addCookie(new Cookie("auth","10"));
		
		return "cookie/make";
	}
	
	/*
	 * @CookieValue 어노테이션을 이용하면 쿠키 값을 파라미터로 전달받을 수 있음		+쿠키값을 1개만 읽어와야된다면 @CookieValue 사용
	 * 해당 쿠키가 존재하지 않으면 기본적으로 400에서 발생시킴 
	 * @CookieValue(value="auth",required=false)로 지정했을 경우 해당 쿠키가 존재하지 않으면 null 값으로 전달
	 * @CookieValue(value="auth",defaultValue="0")로 지정했을 경우 쿠키가 존재하지 않으면 defaultValue에 지정한 값을 사용
	 * 
	 */
	@RequestMapping("/cookie/view.do")//@CookieValue("auth")이걸 사용하면 무조건 쿠키가 있어야됨 생성안하고 view누르면 에러페이지 뜸
	public String view(@CookieValue(value="auth",defaultValue="0") String auth) {//쿠기값 읽어오고 지정된 쿠키값을 읽어옴/@CookieValue-지우면 null,인자니까 @RequestParam이 생략된것으로 인식함("auth")-지워도됨 String auth
		
		System.out.println("auth 쿠키 : " + auth);
		
		return "cookie/view";
	}
}
