package kr.s38.jdbc.score;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ScoreMain {
   private BufferedReader br;
   private ScoreDAO dao;
   
   //생성자( 작업은 다 생성자에서 함),메뉴 중심으로 호출
   public ScoreMain() {
      try {
         br = new BufferedReader(new InputStreamReader(System.in));//객체 생성
         dao = new ScoreDAO();
         callMenu();
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         //자원정리
         if(br!=null)try {br.close();}catch(IOException e) {}
      }
   }
   //메뉴
   public void callMenu()throws IOException{
      while(true) {
         System.out.print("1.입력,2.목록,3.상세정보,4.수정,5.삭제,6.종료>");
         try {
            int no = Integer.parseInt(br.readLine());
            if(no==1) {//입력
               
               System.out.print("이름:");
               String name = br.readLine();
               
               int korean = parseInputData("국어:");
               int english = parseInputData("영어:");
               int math = parseInputData("수학:");
               int sum = makeSum(korean,english,math);//전에는 멤버변수로 메서드에서 받을 수 있는데 여긴 없,지역변수에 인자 넣고 연산해서 반환
               int avg = makeAvg(sum);
               String grade = makeGrade(avg);
               
               dao.insertScore(name, korean, english, math, sum, avg, grade);
               
            }else if(no==2) {//목록
               dao.selectScore();
            }else if(no==3) {//상세번호
               dao.selectScore();
               System.out.print("선택한 글의 번호 입력:");
               int num = Integer.parseInt(br.readLine());//자동으로 들어가있는 null이 아니라 br.readLine()넣어야됨
               
               System.out.println("-------------------");
               
               dao.selectDetailScore(num);;
               
            }else if(no==4) {//수정
               dao.selectScore();
               System.out.print("수정할 글의 번호:");
               int num = Integer.parseInt(br.readLine());
               
               dao.selectDetailScore(num);
               System.out.println("------------------");
               
               System.out.print("이름:");
               String name = br.readLine();
               int korean = parseInputData("국어:");
               int english = parseInputData("영어:");
               int math = parseInputData("수학:");
               //총점 구하기
               int sum = makeSum(korean,english,math);//전에는 멤버변수로 메서드에서 받을 수 있는데 여긴 없,지역변수에 인자 넣고 연산해서 반환
               //평균 구하기
               int avg = makeAvg(sum);
               
               //등급 구하기
               String grade = makeGrade(avg);
               
               dao.updateScore(num, name, korean, english, math, sum, avg, grade);
               
            }else if(no==5) {//삭제
               dao.selectScore();
               System.out.print("삭제할 글의 번호:");
               int num = Integer.parseInt(br.readLine());
               
               dao.deleteScore(num);
               
            }else if(no==6) {//종료
               System.out.println("프로그램 종료");
               break;
            }else {
               System.err.println("잘못 입력했습니다.");
            }
         }catch(NumberFormatException e) {
            System.out.println("숫자만 입력가능");
         }
      }
   }
   //총점 구하기
   public int makeSum(int korean,int english,int math) {
      return korean + english + math;//클래스 멤버 변수 없어서 메서드에서 인자 받아야됨 그리고 연산해서 반환
   }
   //평균 구하기
   public int makeAvg(int sum) {//인자 넣어서 연산>반환
      return sum/3;
   }
   //등급 구하기
   public String makeGrade(int avg) {//switch,if
      String grade;
      switch(avg/10) {
      case 10:
      case 9: grade = "A"; break;
      case 8: grade = "B"; break;
      case 7: grade = "C"; break;
      case 6: grade = "D"; break;
      default : grade = "F";
      }
      return grade;
   }
   //성적 범위 체크(0~100)
   public int parseInputData(String course) throws IOException {
      while(true) {
         System.out.print(course);//println이 아니라 print
         try {
            int num = Integer.parseInt(br.readLine());
            if(num<0 || num >100) {
               throw new ScoreValueException ("0부터 100사이만 입력 가능");
            }return num;
         }catch(NumberFormatException e) {
            System.out.println("숫자만 입력 가능");
         }catch(ScoreValueException e) {//사용자 정의 예외 클래스
            System.out.println(e.getMessage());
         }
      }
      
   }
   public static void main(String[] args) {
      new ScoreMain();//객체 생성  
   }
}