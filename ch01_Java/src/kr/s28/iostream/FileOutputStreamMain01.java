package kr.s28.iostream;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamMain01 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		try {
			//파일 생성(덮어쓰기)
			fos = new FileOutputStream("filout.txt");
			//파일 생성(이어쓰기)
			//fos = new FileOutputStream("filout.txt",true);//전에있던 데이터를 보존하면서 이어쓰기
			
			String message = "Hello File! 파일에 내용 기술";
			
			//파일에 데이터 기록
					//String -> byte[]
			fos.write(message.getBytes());
			
			System.out.println("파일을 생성하고 내용을 기술했습니다.");
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!=null)try {fos.close();}catch(IOException e) {}
		}
		
		
	}
}
