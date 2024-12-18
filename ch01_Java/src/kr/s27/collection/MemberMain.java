package kr.s27.collection;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MemberMain {
	/*
	 * [실습]
	 * 메뉴 : 1.회원정보 입력,2.회원정보 출력,3.종료
	 * 메서드명 : 메뉴 callMenu()
	 * 		  회원정보 입력 register()
	 * 		  회원정보 출력 printUserInfo()
	 * 입력시 조건 체크 : 나이는 1살 이상 입력 가능
	 */
	
	
	ArrayList<Member> list;//23,25여기서 초기화해도됨
	BufferedReader br;
	
	public MemberMain() {
		list = new ArrayList<Member>();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null)try {br.close();}catch(IOException e) {}
		}
	}
	
	//메뉴 호출
	public void callMenu()throws IOException{
		
		while(true) {
			System.out.print("1.회원정보 입력,2.회원정보 출력,3.종료>");
			try {
				
				int num = Integer.parseInt(br.readLine());
				if(num==1) {//회원정보 입력
					register();
				}else if(num==2) {//회원정보 출력
					printUserInfo();
				}else if(num==3) {//종료
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("숫자만 허용");
			}
		}
	}
	//회원정보 입력
	public void register()throws IOException{
		Member m = new Member();
		System.out.print("이름:");
		m.setName(br.readLine());
		
		m.setAge(parseInputData("나이:"));
		
		System.out.print("직업:");
		m.setJob(br.readLine());
		System.out.print("주소:");
		m.setAddress(br.readLine());
		System.out.print("전화번호:");
		m.setPhone(br.readLine());
		
		//Member를 ArrayList에 저장
		list.add(m);
		System.out.println("상품 정보 1건이 추가되었습니다.");
	}
	//나이 조건 체크
	public int parseInputData(String item)throws IOException{
		while(true) {
			System.out.print(item);
			try {
				int age = Integer.parseInt(br.readLine());// readLine있어서 IOException명시
				if(age<=0) {
					System.out.println("나이는 1살이상 입력 가능");
					continue;
				}
				return age;
			}catch(NumberFormatException e) {
				System.out.println("[숫자만 입력가능]");
			}
		}
	}

//회원정보 출력
public void printUserInfo() {
	System.out.println("회원목록 : 총회원수("+list.size()+")");
	System.out.println("-----------------------------");
	System.out.println("이름\t나이\t직업\t주소\t전화번호");
	System.out.println("-----------------------------");
	
	/*
	 * for(Member md : list) {
	 * System.out.print(m.getName()+"\t");
	 * System.out.println(m.getPhone());
	 * }
	 */
	for(Member md : list) {
		System.out.printf("%s\t",md.getName());
		System.out.printf("%d\t",md.getAge());
		System.out.printf("%s\t",md.getJob());
		System.out.printf("%s\t",md.getAddress());
		System.out.printf("%s\n",md.getPhone());
		
	}
	
	
}

	public static void main(String[] args) {
		new MemberMain();
		
	}
}
