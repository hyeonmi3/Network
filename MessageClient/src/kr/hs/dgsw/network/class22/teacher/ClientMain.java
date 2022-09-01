package kr.hs.dgsw.network.class22.teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientMain {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		try { // 쌤 ip 10.80.162.206  황 ip 10.80.161.60
			Socket sc = new Socket("10.80.162.206", 5000);

			// 서버로 데이터 보내기
			OutputStream os = sc.getOutputStream();
			// 서버한테 데이터 받기
			InputStream is = sc.getInputStream();
			PrintWriter pw = new PrintWriter(os, true);
			BufferedReader br = 
					new BufferedReader(new InputStreamReader(is));

			Thread it = new InputThread(pw);
			Thread ot = new OutPutThread(br);
			
			it.start();
			ot.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
