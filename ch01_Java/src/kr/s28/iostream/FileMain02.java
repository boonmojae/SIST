package kr.s28.iostream;

import java.io.File;
import java.io.IOException;

public class FileMain02 {
	public static void main(String[] args) {
		//절대경로
		//String path = "C:\\javaWork\\sample.txt";//\\는 폴더 구분자
		
		//상대경로
		String path = "sample.txt";//파일명만 명시
		
		File f1 = new File(path);
		System.out.println("===파일 생성===");
		try {
			/*
			 * 제공된 경로를 기반으로 파일을 생성. 파일이 생성되면
			 * true 반환, 생성되지 않으면 false 반환
			 * 경로가 잘못되면 IOException 발생
			 */
			System.out.println(f1.createNewFile());//여기까지쓰면 오류나서 try catch해야됨
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//파일이 이미 생성돼서 계속 false로 나옴
		
		System.out.println("===파일 정보===");
		System.out.println("절대경로:" + f1.getAbsolutePath());
		System.out.println("상대경로:" + f1.getPath());
		System.out.println("디렉토리명:" + f1.getParent());
		System.out.println("파일명:" + f1.getName());
	}
}
