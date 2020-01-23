package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Scanner;

public class StringDownload {
	public static void main(String[] args) {

		try {

			// 다운로드 받을 URL을 생성
			URL url = new URL("https://www.naver.com");

			// URL 연결 객체 생성
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			//캐시는 사용하지 않을게요
			con.setUseCaches(false);

			// 연결 옵션 설정하기
			con.setConnectTimeout(30000); // 30초동안 연결이 안되면 연결 시도를 종료
			
			//데이터를 읽어올 스트림을 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			//많은 양의 문자열을 읽어야 하는 경우
			StringBuilder sb = new StringBuilder();
			while(true) {
				String line = br.readLine(); // 한 줄 읽은 것
				// 읽은 데이터 없으면 반복문을 중단하자.
				if(line==null) {
					break;
				}
				//데이터가 있다면 sb에 추가
				sb.append(line +"\n");
			}
			
			//StringBuilder의 데이터를 String으로 변환
			String html = sb.toString();
			System.out.println(html);
			
		} catch (Exception e) {
			System.err.println("The exceptional case: " + e.getMessage());
		}

	}
}
