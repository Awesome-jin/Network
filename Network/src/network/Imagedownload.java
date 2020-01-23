package network;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class Imagedownload {

	public static void main(String[] args) {
		// 스레드 만드는 제일 쉬운 방법! Thread th = new thread() > public void run() {내용} >
		// th.start();

		Thread th = new Thread() {
			public void run() {
				try {
					// 다운로드 받을 주소 생성
					String addr = "http://image.fnnews.com/resource/media/image/2018/07/30/201807301602203213_l.jpg";
					int len = addr.lastIndexOf('/');
					String filename = addr.substring(len + 1);

					// 현재 디렉도리에 위 파일이 있으면 있다고 출력
					File f = new File("./" + filename);
					if (f.exists() == true) {
						System.out.println("파일이 이미 존재합니다");
						return;
					} else {
						// 주소 객체 생성
						URL url = new URL(addr);
						HttpURLConnection con = (HttpURLConnection) url.openConnection();
						con.setUseCaches(false);

						/*
						 * 다운로드 받을 파일의 크기 가져오기 int length = con.getContentLength(); // 데이터를 저장할 바이트 배열
						 * 생성 byte [] b = new byte[length]; // 바이트 단위로 데이터를 읽어올 스트림 생성
						 * BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
						 * bis.read(b); // 데이터를 읽어서 b에 저장 PrintStream ps = new PrintStream(new
						 * FileOutputStream("./" + filename)); ps.write(b);
						 */

						BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
						PrintStream ps = new PrintStream(new FileOutputStream("./" + filename));
						while (true) {
							// 512 바이트 배열
							byte[] b = new byte[512];
							// 내용을 읽어서 b에 저장하고 읽은 개수는 r에 저장
							int r = bis.read(b);
							// 읽은게 없으면 중단시킬 것
							if (r <= 0) {
								break;
							}
							// 읽은 데이터가 있음 기록하기
							ps.write(b, 0, r);
							ps.flush(); //write나 print했으면 flush꼭 하자. >> 버퍼에 내용이 남아있어서 제대로 안 보일 수 있다.
						}
						// 사용한 스트림 닫기
						ps.close();
						bis.close();
						// 연결 끊기
						con.disconnect();
					}
				} catch (Exception e) {
					System.err.println("예외 발생 :" + e.getMessage());
					e.printStackTrace();
				}
			}
		};
		th.start();

		// thread 동작 쉬는 중 쉬는 시간이 생기면 동작시킬 것
		System.out.println("스레드와 상관없는 코드");

	}

}
