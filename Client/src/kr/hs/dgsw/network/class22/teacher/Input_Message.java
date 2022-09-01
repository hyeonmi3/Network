package kr.hs.dgsw.network.class22.teacher;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Input_Message extends Thread {
	private Socket sc = null;

	
	public Input_Message(Socket sc) {
		this.sc = sc;
	}
	
	public void run() {
		try {
			InputStream is = sc.getInputStream();		// Ŭ���̾�Ʈ �޽��� �Է� ����
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			boolean loginboolean=true;
			while(loginboolean) {
				String brmsg = br.readLine();
				
				String msgType = "";	
				String msgFromNick = "";
				String msgBeforeNick = "";
				String msgAfterNick = "";
				String msgJoinCount = "";
				String msgMessage = "";
				if(brmsg.equals("[Start]")) {
					brmsg = br.readLine();
					String[] brmsgarr = brmsg.split("::");
					if(brmsgarr[0].equals("Type")) {
						msgType = brmsgarr[1];
					} else {
						System.out.println("���� �޽����� �̻��մϴ�.");
						continue;
					}
					
					while(!(brmsg = br.readLine()).equals("[End]")) {
						brmsgarr = brmsg.split("::");
						switch(brmsgarr[0]) {
						case "FromNick":
							msgFromNick = brmsgarr[1]; break;
						case "BeforeNick":
							msgBeforeNick = brmsgarr[1]; break;
						case "AfterNick":
							msgAfterNick = brmsgarr[1]; break;
						case "JoinCount":
							msgJoinCount = brmsgarr[1]; break;
						
						case "Message":
							msgMessage = "";
							while(!(brmsg = br.readLine()).equals("::Message")) {
								msgMessage += brmsg + "\r\n";
							}
							msgMessage = msgMessage.substring(0,msgMessage.length()-2);
							break;
						default:
							break;
						}
					}
					
					switch(msgType) {
						case "LogIn":
							System.out.println("[" + msgFromNick + "] �����Ͽ����ϴ�.");
							break;
						case "LogOutOK":
							System.out.println("�α׾ƿ��Ͽ����ϴ�.");
							loginboolean = false;
							break;
						case "LogOut":
							System.out.println("[" + msgFromNick + "] ����ڰ� �α׾ƿ��Ͽ����ϴ�.");
							break;
						case "NickNameOK":
							System.out.println("�г����� [" + msgAfterNick + "]�� �����Ͽ����ϴ�.");
							break;
						case "NickNameOverlap":
							System.out.println("�г����� [" + msgAfterNick + "]�� �̹� ������Դϴ�.");
							break;
						case "NickNameInform":
							System.out.println("[" + msgBeforeNick + "] ����ڰ� �г����� [" + msgAfterNick + "]�� �����Ͽ����ϴ�.");
							break;
						case "Whisper":
							System.out.println("[�ӼӸ�] " + msgFromNick + ": " + msgMessage);
							break;
						case "WhisperOK":
							System.out.println("[�ӼӸ�] ���� ����");
							break;
						case "WhisperWhithout":
							System.out.println("[�ӼӸ�] ���� ���� / �г����� �����ϴ�.");
							break;
						case "AllMessage":
							System.out.println(msgFromNick + ": " + msgMessage);
							break;
						case "NickList":
							System.out.println("�����ڼ�: "+ msgJoinCount);
							System.out.println("[�����ڸ���Ʈ]");
							System.out.println(msgMessage);
							System.out.println("[�����ڸ���Ʈ��]");
							break;
						default:
							break;
					}
				}
			}
		} catch(Exception e) {
			System.out.println("���� ����");
			e.printStackTrace();
		}
	}
}