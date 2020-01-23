package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

	public static void main(String[] args) {
		
		try {
			
			Socket socket = new Socket(InetAddress.getByName("211.183.7.63"),5555);
			
			// 메세지 전송하기 (입력을 받아서 보내는 방식으로)
			Scanner sc = new Scanner(System.in);
			System.out.print("Message that you want to send : ");
			String msg = sc.nextLine();
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			pw.println(msg);
			pw.flush();
			sc.close();
			
			
			// 메세지를 읽기
			BufferedReader br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String str = br.readLine();
			System.out.println(str);
			
			br.close();
			pw.close();
			socket.close();
			
		}catch(Exception e) {
			System.err.println("The exceptional case: " + e.getMessage());
		}

	}

}
