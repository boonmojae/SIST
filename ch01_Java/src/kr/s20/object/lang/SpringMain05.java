package kr.s20.object.lang;

public class SpringMain05 {
	public static void main(String[] args) {
		/*
		 * [실습]
		 * 아래 문자열의 대문자->소문자, 소문자->대문자
		 */
		String str = "abcMDye-4W?EWzz";
		String result = "";
		
		for(int i=0;i<str.length();i++) {
			char c = str.charAt(i);
			if(c>=65 && c<=90) {//대문자
						   //char->String	 소문자로 변환
				result += String.valueOf(c).toLowerCase();
			}else if(c>=97 && c<= 122) {
							//char->String	 대문자로 변환
				result += String.valueOf(c).toUpperCase();
			}else {//대문자,소문자가 아닌 문자
				result += c;
			}
		}
		System.out.println(result);
		System.out.println("------------------");
		
		String result2 = "";
						//string->char[]로 만들어줌
		for(char c : str.toCharArray()) {
			//대문자이면 true 반환
			if(Character.isUpperCase(c)) {
							//소문자로 변환
				result2 += Character.toLowerCase(c);
			//소문자이면 true 반환
			}else if(Character.isLowerCase(c)) {
							//대문자로 변환
				result2 += Character.toUpperCase(c);
			}else {
				result2 += c;
			}
		}
		System.out.println(result2);
		System.out.println("------------------");
		
		String result3 = "";
		
		for(int i=0;i<str.length();i++) {
		char c = str.charAt(i);
		if(c>= 65 && c<=90) {//대문자
						//소문자로 변환
			result3 += (char)(c +32);
		}else if(c >= 97 && c <= 122) {
						//대문자로 변환
			result3 += (char)(c -32);//(char)()형변환시켜야 문자로 적용
		}else {
			result3 += c;
			}
		}
		System.out.println(result3);
	}
}
	
