package kr.s28.iostream;

import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

class UserInfo implements Serializable{
	private String name;
	private int age;
	private String address;
	
	public UserInfo(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;	
	}

	@Override
	public String toString() {
		return "이름 : " + name + ", 나이 : " + age + ", 주소 : " + address;
	}
}

public class SerialMain03 {
	public static void main(String[] args) {
		//직렬화할 객체 생성
		UserInfo u1 = new UserInfo("John",20,"서울시");
		UserInfo u2 = new UserInfo("Sunny",18,"부산시");
		
		ArrayList<UserInfo> list = new ArrayList<UserInfo>();
		list.add(u1);
		list.add(u2);
		
		FileOutputStream fos = null;//객체 파일만들고
		ObjectOutputStream oos = null;//직렬화
		
		try {
			fos = new FileOutputStream("userInfo.ser");//ser은 확장자
			oos = new ObjectOutputStream(fos);
			//객체 직렬화 하기
			oos.writeObject(list);
			
			System.out.println("직렬화가 성공적으로 완료되었습니다.");
		}catch(IOException e) {//try밑에 넣기전까지 오류로 떠있음
			e.printStackTrace();
		}finally {
			if(oos!=null)try {oos.close();}catch(IOException e) {}//close할때 IOEx발생할수있으니까 try~catch해야됨
			if(fos!=null)try {fos.close();}catch(IOException e) {}//{}는 끝나는 부분이라 무시하고 진행
		}
		
	}
}
