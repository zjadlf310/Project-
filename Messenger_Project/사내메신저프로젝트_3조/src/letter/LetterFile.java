package letter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import messengerVO.MessengerVO;

public class LetterFile {
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private FileOutputStream fos;
	private FileInputStream fis;
	private int filelenght;
	private byte [] byte1;
	
	public LetterFile() throws IOException {
		
	}
	
	public void FileDownload(MessengerVO mvo) throws IOException {
		socket = new Socket("192.168.58.26",6567);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		
		dos.writeUTF(mvo.getLetter_filename());
		byte1 = new byte[dis.readInt()];
		String username = System.getProperty("user.name");
		
		dis.readFully(byte1);
		fos = new FileOutputStream("c:/Users/"+username+"/Downloads/"+mvo.getLetter_filename());
		fos.write(byte1);
		
		fos.close();
	}
	
	public void FileSend(File file,String filename) throws IOException {
		socket = new Socket("192.168.58.26",6566);
		filelenght = (int) file.length();
		byte1 = new byte[filelenght];
		
		fis = new FileInputStream(file);
		dis = new DataInputStream(fis);
		dos = new DataOutputStream(socket.getOutputStream());
		
		dos.writeUTF(filename);
		dis.readFully(byte1);
		dos.writeInt(byte1.length);
		dos.write(byte1);
	}

}
