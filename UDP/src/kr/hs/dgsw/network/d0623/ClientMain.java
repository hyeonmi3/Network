package kr.hs.dgsw.network.d0623;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClientMain {
	public static void main(String[] args) throws IOException {
		InetAddress inetaddr = InetAddress.getByName("");
		
		DatagramSocket ds = new DatagramSocket();
		
		for (int i = 1; i <= 100; i++) {
			String j = Integer.toString(i);
			DatagramPacket sendPacket =
					new DatagramPacket(j.getBytes(),
							j.getBytes().length, inetaddr, 9999);
			ds.send(sendPacket);
		}
	}
}
