package kr.hs.dgsw.network.class22.teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientAccept {
	public Socket sc = null;
	
	private OutputStream os = null;
	private PrintWriter pw = null;
	private BufferedReader br = null;
	
	// 생성
	public ClientAccept(Socket sc) {
		this.sc = sc;
		
		// 메시지 보내기 관련 객체 생성
		try {
			this.os = sc.getOutputStream();
			this.pw = new PrintWriter(this.os, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 메시지 받기 관련 객체 생성
		InputStream is = null;
		try {
			is = sc.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		// 클라이언트 메시지 입력 받음
		this.br = new BufferedReader(new InputStreamReader(is));
	}
	
	// 메시지 보내기 부분
	public void sendMessage(String msg) {
		pw.println(msg);
	}
	
	
	// 메시지 받기 부분
	public String readMessage() throws IOException {
		return br.readLine();
	}
}
