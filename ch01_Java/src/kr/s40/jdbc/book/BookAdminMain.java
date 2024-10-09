package kr.s40.jdbc.book;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import kr.util.DBUtil;

public class BookAdminMain {
	//메뉴 : 1.도서 등록,2.도서 목록,3.회원 목록,4.대출 목록,5.종료
	
	private BufferedReader br;
	private BookDAO dao;
	//생성자
	public BookAdminMain() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			dao = new BookDAO();
			//메뉴 호출
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			if(br!=null)try {br.close();} catch(IOException e) {}
		}
	}
	//메뉴
	public void callMenu()throws IOException {
		while(true) {
			System.out.print("1.도서 등록,2.도서 목록,3.회원 목록,4.대출 목록,5.종료>");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) {
					System.out.print("도서명:");
					String bk_name = br.readLine();
					System.out.print("카테고리:");
					String bk_category = br.readLine();
					
					dao.insertBook(bk_name, bk_category);
				}else if(no==2) {
					dao.selectListBook();
				}else if(no==3) {
					dao.selectListMember();
				}else if(no==4) {
					
					
					
				}else if(no==5) {
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력 가능");
			}
		}
	}
	public static void main(String[] args) {
		new BookAdminMain();
	}
}
