package kr.hs.dgsw.network.class22.teacher;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ChattingServerMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(5000);  	// ���� ����(Ŭ���̾�Ʈ ���� ���)

			System.out.println("������ ���۵Ǿ����ϴ�.");
			
			while(true) {
				Socket sc = ss.accept();
				System.out.println(sc.getInetAddress() +": �����Ͽ����ϴ�.");
				
				try {
					// ������ �ְ� �޴� ��ü ����
					ClientAccept ca = new ClientAccept(sc);
					
					Thread ct = new ClientThread(sc.getInetAddress().toString(), ca);
					ct.start();
				} catch(Exception e) {
					System.out.println("���� ����");
				}
			}
		} catch(Exception e) {
		} finally {
			try {
				ss.close();
			} catch(IOException e) {}
		}
	}
}