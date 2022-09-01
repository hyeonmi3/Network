package kr.hs.dgsw.network.n2203.client;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Scanner;
import kr.hs.dgsw.network.n2203.server.Server;


public class Client {
    public static boolean Login() throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean success = false;
        while (!success) {
            System.out.print("ID : ");
            String id = sc.next();
            System.out.print("PASS : ");
            String pw = sc.next();
            success = Server.login(id, pw);
            if (!success) {
                System.out.println("** ID 또는 PASS가 틀렸습니다! **");
            } else {
                System.out.println("** FTP 서버에 접속하였습니다. **");
                break;
            }

        }
        return true;
    }

    public static void upload(String cm, DataOutputStream dos) throws UnknownHostException, IOException {
        String[] newCommand = cm.split(" ");

        File fl = new File(newCommand[1]);
        FileInputStream fis = new FileInputStream(fl);
        dos.writeUTF(fl.getName());

        int readsize = 0;
        long size = fl.length();
        dos.writeLong(size);
        byte[] bytes = new byte[1024];

        while ((readsize = fis.read(bytes)) != -1) {
            dos.write(bytes, 0, readsize);
        }
        dos.flush();
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
    	System.out.println("** 서버에 접속하였습니다. **");
        Login();

        Socket sc = new Socket("192.168.0.9", 5000);
        OutputStream os = sc.getOutputStream();
        BufferedOutputStream bor = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bor);

        Scanner scan = new Scanner(System.in);
        while (true) {
            String command = scan.nextLine();

            if (command.startsWith("/업로드")) {
                upload(command, dos);
            }
        }
    }
}