package echo;
import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException{
		Socket socket = new Socket(); // 소켓 객체 생성
		
		System.out.println("< 클라이언트 시작 >");
		System.out.println("===========================================");
		System.out.println("[ 서버에 연결 요청 ]");
		
		socket.connect(new InetSocketAddress("192.168.0.117", 10001));
		System.out.println("[ 서버에 연결 성공 ]");
		
		// 서버에게 메시지 보내기용 스트림
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter ows = new OutputStreamWriter(os, "UTF-8"); // 바이트 코드를 글자로
		BufferedWriter bw = new BufferedWriter(ows); // 속도 향상을 위해 버퍼에 담기
		
		// 서버로부터 메시지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr); 

		// 키보드 입력
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			// 메시지 입력
			String msg = sc.nextLine();
			
			if(msg.equals("/q")) {
				System.out.println("[ 접속 종료 ]");
				break;
			}

			// 서버에게 메시지 보내기
			bw.write(msg);
			bw.newLine(); // 줄바꿈
			bw.flush(); // 버퍼 모두 채우기
					
			// 서버로부터 메시지 받기
			String reMsg = br.readLine();
			System.out.println("황민영 : [ " + reMsg + " ]");
		}
		
		sc.close();
		
		System.out.println("===========================================");
		System.out.println("< 클라이언트 종료 >");
		
		bw.close();
		br.close();
		socket.close();
	}
}
