package echo;
import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(); // 소켓 객체 생성
		// .bind(new InetSocketAddress("IP", PORT)) => IP, Port 번호 설정 단계
		serverSocket.bind(new InetSocketAddress("192.168.0.129", 10001));
		
		System.out.println("< 서버시작 >");
		System.out.println("===========================================");
		
		while(true) {
			System.out.println("[ 연결 대기 중 ]");
			Socket socket = serverSocket.accept(); // 연결 요청 확인
			Thread thread = new ServerThread(socket);
			thread.start();
		}
		
		/*
		System.out.println("===========================================");
		System.out.println("< 서버 종료 >");

		serverSocket.close();
		*/
	}
}
