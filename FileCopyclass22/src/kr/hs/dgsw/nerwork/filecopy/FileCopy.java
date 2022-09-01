package kr.hs.dgsw.nerwork.filecopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) throws IOException {
		File fl = new File("D:\\testimage.png");
		FileInputStream fis = new FileInputStream(fl);
		
		FileOutputStream fos = new FileOutputStream("D:\\temp\\testimage.png");
		
		int readsize = 0;
		byte[] bytes = new byte[1024];

		while ((readsize = fis.read(bytes)) != -1) {
			fos.write(bytes, 0, readsize);
		}
	}
}
