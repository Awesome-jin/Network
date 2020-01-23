package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastRecieve {

	public static void main(String[] args) {
	
		try {	
			
			MulticastSocket ms = new MulticastSocket(8888); // 받는쪽은 항상포트 번호가 있어야 함
			// 멀티캐스트에 참여하기
			ms.joinGroup(InetAddress.getByName("230.100.100.100"));
			System.out.println("멀티캐스트 시작!"); 
			
			while(true) { // 반복문 안에는 Multicast, UDP, TCP 모두 동일
				//전송 받은 데이터를 저장할 바이트 배열 : 크기는 8의 배수로 설정하는 경우가 많음
				byte[ ]b = new byte[65536];
				
				//패킷을 생성
				DatagramPacket dp = new DatagramPacket(b, b.length);
				// 데이터를 받을 수 있도록 대기
				ms.receive(dp);
				
				// 데이터 읽기
				String msg = new String(dp.getData());
				System.out.println(msg);
				
			}
			
		}catch(Exception e) {
			System.err.println("예외1:" + e.getMessage());
			e.printStackTrace();  //코드가 길어질 때, 예외 번호를 넣으면 어디서 문제가 생기는지 빠르게 파악 가능함!
		}
		

	}

	}


