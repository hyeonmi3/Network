package kr.hs.dgsw.network.class22.teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class InputThread extends Thread {
	PrintWriter pw = null;
	Thread ot = null;
	public InputThread(PrintWriter pw) {
		this.pw = pw;
	}

	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			String msg = "";
			while ((msg=br.readLine())!=null) {
				pw.println(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			
			System.out.println("클라이언트 접속이 종료되었습니다. inputThread를 종료합니다.");
		}

	}
}
