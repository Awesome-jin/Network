package network;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParsing {

	public static void main(String[] args) {
		
		//문자열을 다운로드 받는 부분
		
		// 다운로드 받은 문자열을 저장할 변수
		String xml = null; // 처음엔 null을 주는 것으로 만들자. 다운로드 여부를 null이냐 아니냐로 판단하기 때문이다.
		
		try {
			URL url = new URL("http://www.hani.co.kr/rss/sports/");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setConnectTimeout(20000);
			con.setUseCaches(false);
		
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			StringBuilder sb = new StringBuilder();
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				sb.append(line + "\n");
			}
			
			// 데이터를 문자열로 변환
			xml = sb.toString();
			
		}
		catch(Exception e) {
			System.err.println("다운로드 예외 발생 : " + e.getMessage());
			e.printStackTrace();
		}
		
		// xml파싱을 해서 출력하는 부분
		//xml이 null이라면 다운로드를 못받은것.
		if (xml == null) {
			System.err.println("다운로드를 받지 못했습니다.");
		}
		else{
			try {
				
				//String > InputStream으로 변환
				InputStream is = new ByteArrayInputStream(xml.getBytes());
				
				// 파싱 객체를 생성
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				
				// 메모리에 펼치기
				Document document = builder.parse(is);
				
				// 루트를 찾아오기 
				Element root = document.getDocumentElement();
				
				// 원하는 태그 찾아오기 - title과 link
				NodeList titlelist = root.getElementsByTagName("title");
				NodeList linklist = root.getElementsByTagName("link");
				
				// 위에서 찾은 list를 가지고 반복문 수행
				int n = titlelist.getLength();
				for(int i =0; i<n; i=i+1){
				Node title = titlelist.item(i);  //노드는 자료형임
				Node link = linklist.item(i);
				
				Node imsi = title.getFirstChild();
				System.out.print(imsi.getNodeValue());
				Node imsi2 = link.getFirstChild();
				System.out.print(imsi2.getNodeValue());
				
				System.out.print("\n");
			}
			}
			catch(Exception e){
				System.err.println("에러케이스 :" + e.getMessage());
			}
		}
	}
}
