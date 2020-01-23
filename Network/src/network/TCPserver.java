package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {

	public static void main(String[] args) {
		
		try{
			
			// 서버소켓 만들기
			ServerSocket ss = new ServerSocket(5555);
			while(true) {
				System.out.println("Waiting for a server"); // 클라이언트 접속 정보를 기다림
				Socket socket = ss.accept(); 
				System.out.println("Connected client : " + socket.getInetAddress()); // 접속한 클라이언트 정보 확인
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								socket.getInputStream()));
				
				String msg = br.readLine();
				System.out.println("Message : " + msg);
				
				
				// 반대로 클라이언트에게 메세지를 전송해보기
				PrintWriter printw = new PrintWriter(socket.getOutputStream());
				printw.println("Message from a server");
				printw.flush();
				br.close();
				printw.close();
				socket.close();
			}
			
		}
		catch(Exception e) {
			System.err.println("The exceptional case: " + e.getMessage());
		}

	}

}
