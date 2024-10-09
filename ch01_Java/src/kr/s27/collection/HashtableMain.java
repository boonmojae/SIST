package kr.s27.collection;

import java.util.Enumeration;
import java.util.Hashtable;

public class HashtableMain {
	public static void main(String[] args) {
		Hashtable<String,Object> h = new Hashtable<String,Object>();//오브젝트 권장 X
		h.put("name", "홍길동");//자료형이 섞여있어서 오브젝트로 
		h.put("age", 27);//오브젝트말고 문자열로 하고싶으면 27에 ""
		h.put("tel", "010-1234-5678");
		h.put("job", "경찰");
		h.put("address", "서울");
		//key가 중복되면 마지막에 입력한 값이 인정
		h.put("name", "홍길순");
		//key와 value에 null 불허
		//h.put("hobby", null);//컴파일할땐 에러없음 
		//h.put(null, "프로그래머");
		
		//저장된 데이터(key와 value의 쌍)의 목록
		System.out.println(h);//h는 참조변수
		System.out.println("--------------");
		//key를 통해서 value 구하기
		String name = (String)h.get("name");//오브젝트로 들어가있어서 다운캐스팅 해야됨
		Integer age = (Integer)h.get("age");
		System.out.println(name + ", " + age);
		System.out.println("--------------");
		
		//Enumeration을 이용해서 key구하기
		Enumeration<String> en = h.keys();
		while(en.hasMoreElements()) {//hasMoreElenemts는 검증해줌
			String key = en.nextElement();
			System.out.println(key + ", " + h.get(key));//toString이 자동적으로 읽어와서 오브젝트타입으로 처리됨?
		}	
	}
}
