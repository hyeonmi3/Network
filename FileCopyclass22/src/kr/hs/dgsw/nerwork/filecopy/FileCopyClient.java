package kr.hs.dgsw.nerwork.filecopy;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileCopyClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sc = new Socket("10.80.162.218", 5000);

		OutputStream os = sc.getOutputStream();
		BufferedOutputStream bor = new BufferedOutputStream(os);
		DataOutputStream dos = new DataOutputStream(bor);

		File fl = new File("D:\\testimage.png");
		FileInputStream fis = new FileInputStream(fl);
		
		int readsize = 0;
		byte[] bytes = new byte[1024];

		dos.writeUTF(fl.getName());
		
		while ((readsize = fis.read(bytes)) != -1) {
			dos.write(bytes, 0, readsize);
		}
	}
}
