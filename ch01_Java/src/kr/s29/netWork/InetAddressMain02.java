package kr.s29.netWork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressMain02 {
	public static void main(String[] args) {
		BufferedReader br = null;//꼭 초기화해야됨
		InetAddress[] addresses;//내부적으로 만들어서 호출하는거라
		String name;//null로 초기화하든 안하든 상관없음
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("도메인명 입력:");
			name = br.readLine();
			//해당 도메인과 매핑되어 있는 모든 주소를 읽어롬
			addresses = InetAddress.getAllByName(name);//All 모든 IP주소 읽어오는것
			for(int i=0;i<addresses.length;i++) {
				System.out.println("호스트 이름 : " + addresses[i].getHostName()+ ", IP주소 : " + addresses[i].getHostAddress());
				
			}
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(br!=null)try {br.close();}catch(IOException e) {}
			}
		}
	}

