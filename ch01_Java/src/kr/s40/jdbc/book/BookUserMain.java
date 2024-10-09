package kr.s40.jdbc.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookUserMain {
	private BufferedReader br;
	private BookDAO dao;
	private String me_id; //로그인한 회원 아이디
	private boolean login; //로그인 여부(로그인 true,로그아웃 false)
	//생성자
	public BookUserMain() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			dao = new BookDAO();
			//메뉴 호출
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			if(br!=null)try { br.close();}catch(IOException e) {}
		}
	}
	//메뉴
	public void callMenu() throws IOException{
		//로그인 체크
		while(true) {
			System.out.print("1.로그인,2.회원가입,3.종료>");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) {//로그인
					
				
					
				}else if(no==2) {//회원가입
					System.out.print("아이디:");
					String me_id = br.readLine();
					//아이디 중복 체크
					int check = dao.checkId(me_id);
					if(check>=1) {//1:중복,2:오류
						System.out.println("아이디가 중복되었습니다.");
					}else {//0:미중복
						//비밀번호,이름,전화번호 입력
						//insertMember 호출
					}
					
					//아이디:
					//아이디가 중복되었습니다.
					//비밀번호:
					
					System.out.print("비밀번호:");
					String me_passwd = br.readLine();
					System.out.print("회원명:");
					String me_name = br.readLine();
					System.out.print("전화번호:");
					String me_phone = br.readLine();
					//가입일은 생략
					
					dao.insertMember(me_id, me_passwd, me_name, me_phone);
				}else if(no==3) {//종료
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
				
			}catch(NumberFormatException e) {
				System.out.println("숫자만 입력 가능!");
			}
		}
		//로그인 체크 후 메뉴
		while(login) {
			System.out.print("1.도서대출,2.MY대출 목록,3.대출 도서 반납,4.종료>");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) {//도서대출
					//System.out.print("");반납 or 대출중
					//int re_status = Integer.parseInt(br.readLine());
					System.out.print("도서 번호:");
					int bk_num = Integer.parseInt(br.readLine());
					System.out.print("회원 ID:");
					String me_id = br.readLine();
					
					//
					
				}else if(no==2) {//MY대출 목록
					
				}else if(no==3) {//대출 도서 반남
					
				}else if(no==4) {//종료
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
		new BookUserMain();
	}
}
