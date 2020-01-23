package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UPDSend {

	public static void main(String[] args) {
		try {
			
			//UPD 전송을 위한 소켓 생성
			DatagramSocket ds = new DatagramSocket();
			Scanner sc = new Scanner(System.in);
			
			// 메세지 입력
			while(true) {
				System.out.print("전송할 메세지:");
				String msg = sc.nextLine();
				
				// 전송할 패킷을 생성
				DatagramPacket dp = new DatagramPacket(
						msg.getBytes(),msg.getBytes().length,
						InetAddress.getByName("211.183.7.65"),7878);
				ds.send(dp);
				
			}

		} catch (Exception e) {
			System.err.println("예외1:" + e.getMessage());
			e.printStackTrace(); // 코드가 길어질 때, 예외 번호를 넣으면 어디서 문제가 생기는지 빠르게 파악 가능함!
		}

	}

}
