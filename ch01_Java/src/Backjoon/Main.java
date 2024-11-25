package Backjoon;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		for(int i=1;i<=9;i++) {
			System.out.println(N + " * " + i + " = " + N*i);
		}

	}
}




//https://www.acmicpc.net/step/4

/*
 * while(true){ if(0 <= h && h <=23) { break; } System.out.print("h:"+h); }
 */