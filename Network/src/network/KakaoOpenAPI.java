package network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class KakaoOpenAPI {

	public static void main(String[] args) {
		// 데이터를 다운로드 받는 부분
		String json = null;
		try {
			
			//검색할 도서를 입력 받아보자
			System.out.print("검색할 도서명:");
			Scanner sc = new Scanner(System.in);
			String book = sc.nextLine();
			
			//book을 한글로 입력 할 수도 있으므로 인코딩
			book = URLEncoder.encode(book, "utf-8");
			
			//다운로드 받을 URL 생성
			URL url = new URL("https://dapi.kakao.com/v3/search/book?target=title&query="+book);
			// request를 보고 원하는 내용을 ? 뒤에다가 갖다 붙이면 된다.
			// 가령 sort를 최신순으로 하려면 earch/book?sort=latest&target=title&query="+book
			// 순서가 상관이 없기때문에 필요한걸 갖다 쓰면 된다.
			
			// 연결 객체 생성
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			
			//옵션 설정
			con.setConnectTimeout(20000);
			con.setUseCaches(false);
			
			//헤더설정 (카카오의 경우 key가 authorization이고 value가 kakaoak 뒤의 본인의 REST api번호)
			con.addRequestProperty("Authorization","KakaoAK e6901febcd85d42b531c1a5e6d70d9ee");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			
			while(true) {
				String line = br.readLine();
				if(line==null) {
					break;
				}
				sb.append(line + "\n");
			}
			json=sb.toString();
			//System.out.println(json);
			
			br.close();
			con.disconnect();
			sc.close();
			
		}catch(Exception e) {
			System.err.println("에러 발생:" + e.getMessage());
			e.printStackTrace();
		}
		// 데이터를 파싱 하는 부분
		if(json ==null) {
			System.err.println("읽어 온 데이터가 없습니다.");
		}
		else {
			JSONObject root = new JSONObject(json);
			//System.out.println(root);
			
			// 카카오 api 페이지를 보고 {}로 되어있으면 객체, []로 되어있으면 배열이라고 파악하고 어떻게 가져올지 판단하자!
			JSONObject meta = root.getJSONObject("meta"); // meat키의 내용을 JSONObject로 가져오기!
			//System.out.println(meta);
			
		
			// document 키의 내용을 JSONArray로 가져오기
			JSONArray document = root.getJSONArray("document");
			//System.out.println(document);
			
			// 배열을 활용 할 때는 배열의 데이터 개수를 먼저 찾아온다.
			int len = document.length();
			for(int i =0; i<len; i=i+1) {
				JSONObject documents = document.getJSONObject(i);
				//System.out.println(documents);
				try {
				String title = documents.getString("title");
				int price = documents.getInt("price");
				
				// 인코딩 = 문자열을 메모리에 저장하는 코드로 변환한 것
				// 디코딩 = 메모리에 저장된 코드를 원래의 문자열로 복원하는 것
				String thumb = documents.getString("thumbnail");
				System.out.println(title + price +"원" + URLDecoder.decode(thumb, "utf-8")); // 코드에 %이런거 나오면 디코딩을 해주면 됨
				}
				catch(Exception e) {}
			}
		}

	}

}
