package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class DaumMain {

	public static void main(String[] args) {
		try{
			//daum의 주소를 생성해보자
			InetAddress addr = InetAddress.getByName("www.daum.net");
			// TCP 소켓을 생성
			Socket socket = new Socket(addr, 80); // 이러면 연결 완료
			
			// 요청 전송
			PrintWriter printw = new PrintWriter(socket.getOutputStream());
			printw.println("GET http://www.daum.net"); // 브라우저에 이 주소를 친 것과 동일한 것
			printw.flush(); //정리작업3 ★
			
			// 데이터를 읽기 - 문자 단위
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// 읽을 데이터가 없을 때까지 줄 단위로 읽어 오기
			while (true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				System.out.println(line);
			}
			br.close(); //정리 작업1★
			socket.close(); //정리 작업2★
		}
		catch(Exception e) {
			System.err.println("예외사항" + e.getMessage());
		}

	}

}
