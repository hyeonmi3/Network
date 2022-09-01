package kr.hs.dgsw.network.d0623;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServerMain {
	public static void main(String[] args) throws IOException {
		DatagramSocket ds = new DatagramSocket();
		
		byte[] buffer = new byte[1024];
		DatagramPacket receivePacket =
				new DatagramPacket(buffer, buffer.length);
		
		while(true) {
			ds.receive(receivePacket);
			
			String msg =
					new String(receivePacket.getData(), 0,
							receivePacket.getLength());
			
			System.out.println(msg + " ");
		}
	}
}
