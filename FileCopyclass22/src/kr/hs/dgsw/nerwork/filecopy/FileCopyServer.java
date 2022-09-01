package kr.hs.dgsw.nerwork.filecopy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileCopyServer {

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(5000);
		Socket sc = ss.accept();

		InputStream is = sc.getInputStream();
		BufferedInputStream bir = new BufferedInputStream(is);
		DataInputStream dis = new DataInputStream(bir);

		String filename = dis.readUTF();
		FileOutputStream fos = new FileOutputStream("D:\\temp\\" + filename);

		int readsize = 0;
		byte[] bytes = new byte[1024];

		while ((readsize = dis.read(bytes)) != -1) {
			fos.write(bytes, 0, readsize);
		}
	} // 파일 보냈을 때 용량 다른 원인 클라이언트에서 서버로 파일을 조금씩 조금씩 보내고 나서 확 끊어버리는데 그러면 서버도 그 지점에서 확 끊어버림 그러다가 마지막 하나를 못받고 끊어서
	// 그래서 서버에 받는게 다 끝나고 나서 끊어주기
}
// 파일을 정확하게 알도록 하기
// 미리 파일 용량을 5000KB 정하고 서버에서 while(5000) 돌리기
// 파일 두 개 연속으로 보낼때 오류 없게
