package kr.s21.object.util;

import java.util.Calendar;

public class CalendarMain01 {
	public static void main(String[] args) {
		Calendar today = Calendar.getInstance();
		System.out.println(today);
		
		int year = today.get(Calendar.YEAR);//연도|| static한 상수
		int month = today.get(Calendar.MONTH)+1;//월 0~11로 나와서 +1해야됨
		int date = today.get(Calendar.DATE);//일
		
		System.out.printf("%d년%d월%d일 ", year,month,date);
		
		int day = today.get(Calendar.DAY_OF_WEEK);//요일 1~7반환 
		String[] days = {"일","월","화","수","목","금","토"};//1차감안하면 "일"을 못부름
		System.out.print(days[day-1]+"요일");
		
		int amPm = today.get(Calendar.AM_PM);//오전 0, 오후 1
		String str = amPm == Calendar.AM ? "오전" : "오후";		//삼항연산자 사용
		
		int hour = today.get(Calendar.HOUR);//시 HOUR_OF_DAY(24시 표시)쓰면 오전오후 명시 필요 없음
		int min = today.get(Calendar.MINUTE);//분
		int sec = today.get(Calendar.SECOND);//초
		System.out.printf(" %s %d시%d분%d초", str,hour,min,sec);
		
	}
}
