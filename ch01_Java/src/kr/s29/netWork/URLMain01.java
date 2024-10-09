package kr.s29.netWork;

import java.net.MalformedURLException;
import java.net.URL;

public class URLMain01 {
	public static void main(String[] args) {
		try {
			URL url = new URL("http://java.sun.com/index.jsp?name=kim#content");//java.sun.com:8080명시하면 기본포트에서 읽어감
			System.out.println("프로토콜 : " + url.getProtocol());
			System.out.println("호스트 : " + url.getHost());
			System.out.println("기본 포트 : " + url.getDefaultPort());//프로토콜 통해서 읽어옴 http = 80
			//포트를 명시하지 않아서 -1 반환
			System.out.println("포트 : " + url.getPort());
			System.out.println("패스 : " + url.getPath());
			System.out.println("쿼리 : " + url.getQuery());
			System.out.println("ref : " + url.getRef());//레버런스 약자
			
		}catch(MalformedURLException e) {//형식안맞으면 mal발생
			e.printStackTrace();
		}
	}
}
