package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Multisend {

	public static void main(String[] args) {
		try {
			
			MulticastSocket ms = new MulticastSocket(); // 보내는쪽은 항상 매개변수에 포트번호가 빠진다
			Scanner sc = new Scanner(System.in);
			System.out.print("닉네임: ");
			String nickname = sc.nextLine();
			
			while(true) {
				System.out.print("전송할 메시지 (종료는 end): ");
				String msg = sc.nextLine();
				
				//문자열은 equals로 비교해야 값을 비교한다!
				if(msg.equals("end")) {
					System.out.println("종료합니다.");
					break;
				}
				msg = nickname + ":" + msg;
				DatagramPacket dp = new DatagramPacket(msg.getBytes(), msg.getBytes().length, InetAddress.getByName("230.100.100.100"), 8888);
				ms.send(dp);
				
			}
			

		} catch (Exception e) {
			System.err.println("예외1:" + e.getMessage());
			e.printStackTrace(); // 코드가 길어질 때, 예외 번호를 넣으면 어디서 문제가 생기는지 빠르게 파악 가능함!
		}
	}
}
