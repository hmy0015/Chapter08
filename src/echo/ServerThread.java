package echo;
import java.io.*;
import java.net.*;

public class ServerThread extends Thread {
	private Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		System.out.println("[ 클라이언트 연결 완료 ]");
		
		try {
			// 클라이언트에게 메시지 보내기용 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter ows = new OutputStreamWriter(os, "UTF-8"); // 바이트 코드를 글자로
			BufferedWriter bw = new BufferedWriter(ows); // 속도 향상을 위해 버퍼에 담기
			
			// 클라이언트로부터 메시지 받기용 스트림
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr); 	
			
			while(true) {
				// 클라이언트로부터 메시지 받기
				String msg = br.readLine();
				System.out.println("Client : [ " + msg + " ]");
				
				if(msg == null) {
					System.out.println("[ 클라이언트 접속 종료 ]");
					break;
				}
				
				// 클라이언트에게 메시지 보내기
				bw.write(msg);
				bw.newLine(); // 줄바꿈
				bw.flush(); // 버퍼 모두 채우기
				
			} // while 끝

			bw.close();
			br.close();
			
		} // try 끝

		catch(IOException e) {
			System.out.println("예외상활 발생");
		} // catch 끝
		
	} //run() 끝
}
