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
	} // ���� ������ �� �뷮 �ٸ� ���� Ŭ���̾�Ʈ���� ������ ������ ���ݾ� ���ݾ� ������ ���� Ȯ ��������µ� �׷��� ������ �� �������� Ȯ ������� �׷��ٰ� ������ �ϳ��� ���ް� ���
	// �׷��� ������ �޴°� �� ������ ���� �����ֱ�
}
// ������ ��Ȯ�ϰ� �˵��� �ϱ�
// �̸� ���� �뷮�� 5000KB ���ϰ� �������� while(5000) ������
// ���� �� �� �������� ������ ���� ����
