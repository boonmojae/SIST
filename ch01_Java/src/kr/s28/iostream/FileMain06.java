package kr.s28.iostream;

import java.io.File;

public class FileMain06 {
	public static void main(String[] args) {
		//절대경로
		String path = "C:\\javaWork\\javaSample";
		
		File f1 = new File(path);
		System.out.println("===디렉토리 삭제===");
		if(f1.delete()) {
			System.out.println(f1.getName()+"디렉토리 삭제");
		}else {
			System.out.println("디렉토리를 삭제할 수 없습니다");
		}
		
	}
}
