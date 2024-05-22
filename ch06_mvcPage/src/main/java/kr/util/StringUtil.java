package kr.util;

public class StringUtil {
	//HTML 태그를 허용하면서 줄바꿈
	public static String useBrHTML(String str) {
		if(str == null) return null;
		
		return str.replaceAll("\r\n", "<br>").replaceAll("\r", "<br>").replaceAll("\n", "<br>");
	}
	
	//HTML 태그를 허용하지 않으면서 줄바꿈
	public static String useBrNoHTML(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
				  .replaceAll("\r\n", "<br>").replaceAll("\r", "<br>").replaceAll("\n", "<br>");
	}
	
	//태그 등록은 그대로 하지만 읽어올때 변환해서 표시
	//HTML를 허용하지 않음
	public static String useNoHTML(String str) {//DAO글목록
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");//태그 구성요소를 겉으로 표시/replceAll후에 변수만들지 않고 바로 replaceAll
	}
	
	//큰 따옴표 처리
	public static String parseQuot(String str) {
		if(str == null) return null;
		
		return str.replaceAll("\"", "&quot;");
	}
}
