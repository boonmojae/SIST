package kr.s27.collection;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ScoreMain {
	/*
	 * [실습]
	 * 메뉴 : 1.성적입력,2.성적출력,3.종료
	 * 메서드명 : 메뉴 callMenu(),
	 * 		   성적입력 inputScore(),
	 * 		   성적출력 printScore(),
	 * 입력시 조건 체크 : 0부터100까지만 입력 가능
	 * 
	 */
	
	ArrayList<Score> list;
	BufferedReader br;
	
	public ScoreMain() {
		list = new ArrayList<Score>();
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
			System.out.print("1.성적입력,2.성적출력,3.종료");
			try {
				int num = Integer.parseInt(br.readLine());
				if(num==1) {//성적입력
					inputScore();
				}else if(num==2) {//성적출력
					printScore();
				}else if(num==3) {//종료
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못입력했습니다");
				}
	
			}catch(NumberFormatException e) {
				System.out.println("숫자만 허용");
			}
		}
	}
	
	//성적 입력
	public void inputScore() throws IOException {
		Score s = new Score();
		System.out.print("이름:");
		s.setName(br.readLine());
		
		s.setKorean(parseInputData("국어:"));
		s.setEnglish(parseInputData("영어:"));
		s.setMath(parseInputData("수학:"));
		
		list.add(s);
		System.out.println("");
	}
	//성적입력 조건 체크
	public int parseInputData(String course)throws IOException{
		while(true) {
			System.out.print(course);//과목 표시
			try {
				int num = Integer.parseInt(br.readLine());
				//성적 유효 범위(0~100) 체크
				if(num<0 || num>100) {
					throw new ScoreValueExceprion("0부터100까지만 입력 가능");
				}
				return num;//정상 값 반환
			}catch(NumberFormatException e) {
				System.out.println("숫자만 허용");
			}catch(ScoreValueExceprion e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//성적 출력
	public void printScore() {
		System.out.println("---------------------------");
		System.out.println("이름\t국어\t영어\t수학\t총점\t평균\t등급");
		System.out.println("---------------------------");

		for(Score sc : list) {
			System.out.print(sc.getName()+"\t");
			System.out.print(sc.getKorean()+"\t");
			System.out.print(sc.getEnglish()+"\t");
			System.out.print(sc.getMath()+"\t");
			System.out.print(sc.makeSum()+"\t");
			System.out.printf("%.2f\t", sc.makeAvg());
			System.out.println(sc.makeGrade());
		}
	}
	
	
	public static void main(String[] args) {
		new ScoreMain();
	}
}
