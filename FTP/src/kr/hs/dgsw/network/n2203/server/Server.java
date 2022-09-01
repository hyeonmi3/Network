package kr.hs.dgsw.network.n2203.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5000);
        Socket sc = ss.accept();
        InputStream is = sc.getInputStream();
        BufferedInputStream bir = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bir);

        String filename = dis.readUTF();
        FileOutputStream fos = new FileOutputStream("D:\\temp2\\" + filename);

        int readsize = 0;
        long size = dis.readLong();
        byte[] bytes = new byte[1024];

        while (true) {

            readsize = dis.read(bytes, 0, 1024);
            size -= readsize;
            fos.write(bytes, 0, readsize);
            if (size <= 0) break;

        }
        System.out.println("** " + filename + "파일을(를) 업로드 하였습니다. **");
    }

    public static boolean login(String id, String pw) {
        if("admin".equals(id))
            if("1234".equals(pw))
                return true;
        return false;
    }
}