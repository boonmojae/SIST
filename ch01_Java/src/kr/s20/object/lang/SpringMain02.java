package kr.s20.object.lang;

public class SpringMain02 {
	public static void main(String[] args) {
		String s1 = "Kwon Sun Ae"; //공백을 구분자라고 함
				   //012345678910 , 길이 = 11
		
		int index = s1.indexOf('n');
		System.out.println("맨 처음 문자 n의 위치 : " + index);
		
		index = s1.indexOf("Sun");//567반환 아니고 첫번째 인덱스인 5를 반환
		System.out.println("문자 Sun의 위치 : " + index);
		
		index = s1.lastIndexOf('n');//앞에서 찾는게 아니라 뒤에서부터 찾음
		System.out.println("마지막 문자 n의 위치 : " + index);
		
		char c = s1.charAt(index);//문자 추출
		System.out.println("지정한 인덱스의 문자 추출 : " + c);//7의 위치에 있는게 n
		
		index = s1.indexOf('S');//인덱스가 5
		String str = s1.substring(index);//문자열 추출
		System.out.println("대문자 S부터 끝까지 잘라내기 : " + str);
		
		str = s1.substring(index,index+3);//5,8
		System.out.println("인덱스5부터 인덱스8전까지 문자열 추출 : " + str);//8전까지!
		
		int length = s1.length();
		System.out.println("문자열의 길이 : " + length);
		
		//구분자를 이용해서 문자열 잘라내기
		String[] array = s1.split(" ");//공백이 구분자
		for(int i=0;i<array.length;i++) {
			System.out.println("array["+i+"]:" + array[i]);
		}
	}
}
