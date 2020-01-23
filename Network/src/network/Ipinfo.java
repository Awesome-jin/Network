package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ipinfo {

	public static void main(String[] args) {
		
		// 자신의 ip 정보를 확인하기 - 자신의 컴퓨터 이름(도메인)과 ip 주소
		InetAddress local;
		try {
			local = InetAddress.getLocalHost();
			System.out.println(local);
			
			// 네이버의 ip 정보를 확인해보기
			InetAddress [ ] naver = InetAddress.getAllByName("www.naver.com");
			for(InetAddress imsi : naver) {
				System.out.println(imsi); // 2개가 나온다
			}
			
		} catch (UnknownHostException e) {
			System.err.println("예외 발생 :" + e.getMessage());
		}

	}

}
