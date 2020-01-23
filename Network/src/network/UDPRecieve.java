package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPRecieve {

	public static void main(String[] args) {
		
		try {
			
			// 받는 소켓을 생성함
			DatagramSocket socket = new DatagramSocket(7878);
			// 데이터를 저장할 패킷 생성
			// 반복문 안에서 계속 사용해야 하는 데이터는 반복문 안에서 초기화를 해주어야 한다.
			byte [ ] b = new byte[65536];
			DatagramPacket dp = new DatagramPacket(b, b.length);
			
			// 데이터를 전송 받아서 읽기
			while(true) {
				socket.receive(dp); //대기하고 있다가 데이터를 전송 받으면 동작
				System.out.println("보낸 곳" + dp.getAddress().getHostAddress());
				//데이터 확인
				String msg = new String(b);
				System.out.println(msg);
			}
			
		}catch(Exception e) {
			System.err.println("예외1:" + e.getMessage());
			e.printStackTrace();  //코드가 길어질 때, 예외 번호를 넣으면 어디서 문제가 생기는지 빠르게 파악 가능함!
		}
		

	}

}
