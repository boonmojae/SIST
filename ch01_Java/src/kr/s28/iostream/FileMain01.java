package kr.s28.iostream;

import java.io.File;

public class FileMain01 {
	public static void main(String[] args) {//	\=디렉토리 구분자 \\두개 넣어서 문자열로 만들어줌
		String path = "C:\\";
		
		File f = new File(path);
			//존재하면서 디렉토리면 12번 진입안함 13
		if(!f.exists() || !f.isDirectory()) {
			System.out.println("유효하지 않은 디렉토리입니다.");
			System.out.println(0);//프로그램 종료
		}
		
		//지정한 디렉토리의 하위 디렉토리와 파일 정보 반환
		File[] files = f.listFiles();
		for(int i=0;i<files.length;i++) {
			File f2 = files[i];
			if(f2.isDirectory()) {//디렉토리인 경우
				System.out.println("[" + f2.getName());
			}else {//파일인 경우
				System.out.print(f2.getName());
				System.out.printf("(%,dbyte)%n",f2.length());
			}
		}
		
	}
}
