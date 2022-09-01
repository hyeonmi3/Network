package kr.hs.dgsw.network.class22.teacher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutPutThread extends Thread {
	private static List<OutPutThread> ClientList = Collections.synchronizedList(new ArrayList<OutPutThread>());

	Socket sc = null;

	OutputStream os = null;
	PrintWriter pw = null;
	String nickname = null;

	public OutPutThread(Socket sc) {
		this.sc = sc;
		ClientList.add(this);

		try {
			os = sc.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pw = new PrintWriter(os, true);
	}

	public void sendMessage(String msg) {
		pw.println(msg);
	}

	public void run() {
		// 클라이언트한테 데이터 받기
		InputStream is = null;
		try {
			is = sc.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		try {
			String msg = "";
			while (true) {
				msg = br.readLine();
				String cmd = msg.substring(0, 1);
				if (cmd == "1") {
					for (OutPutThread tmpOT : ClientList) {
						if (msg != null && tmpOT != this)
							System.out.println(msg);
							tmpOT.sendMessage(this.nickname + ": " + msg);
					}
				} else if (cmd == "0") {
					String fromnick = msg.substring(1, msg.indexOf(" "));
					System.out.println(fromnick);
					for (OutPutThread tmpOT : ClientList) {
						// 닉네임이 일치하는 사람에게 메세지 전송
						if (tmpOT.nickname.equals(fromnick))
							tmpOT.sendMessage("[귓속말] " + this.nickname + ": " + 
												msg.substring(msg.indexOf(" ")));
					}
				} else if (cmd == "2") {
					this.nickname = msg.substring(1);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}