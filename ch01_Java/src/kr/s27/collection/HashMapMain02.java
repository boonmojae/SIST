package kr.s27.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class HashMapMain02 {
	public static void main(String[] args) {
		String[] msg = {"Berlin","Paris","Seoul","New York","London"};
		
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		//HashMap에 key와 value 저장
		for(int i=0;i<msg.length;i++) {
			map.put(i, msg[i]);
		}
		//HashMap의 데이터 목록 호출
		System.out.println(map);
		System.out.println("--------------------");
		
		 //Set<Integer> s = map.keySet();
		 //Iterator<Integer> keys = s.iterator();
		Iterator<Integer> keys = map.keySet().iterator();//위에처럼 한번만쓰는 변수는 안만들고 바로 메서드 만듦
		 while(keys.hasNext()) {
			 Integer key = keys.next();
			 System.out.println(key + "," + map.get(key));//map.get(key)붙이면 value추가됨
		 }
		
	}
}
