package filesendserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileSendServer {
	private Socket socket;
	private ServerSocket serversocket;
	private ThreadFileSendClass sendclass;
	
	public FileSendServer() throws IOException {
		 serversocket = new ServerSocket(6567);
		 System.out.println("서버가동");
		 while(true) {
			 socket = serversocket.accept();
			 sendclass = new ThreadFileSendClass(socket);
			 
			 sendclass.start();
		 }
	}

	class ThreadFileSendClass extends Thread {
		Socket socket;
		FileInputStream fis;
		DataInputStream dis, fileinput;
		DataOutputStream dos;
		String filename;
		int filelenght;
		byte[] filebyte;
		File file;
		
		public ThreadFileSendClass (Socket s1) throws IOException {
			socket = s1;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
		}
		
		public void run() {
			try {
				if(dis !=null) {
					filename = dis.readUTF();
					file = new File("e:/Save/"+filename);
					
					filelenght = (int) file.length();
					filebyte = new byte[filelenght];
					
					fis = new FileInputStream(file);
					fileinput = new DataInputStream(fis);
					
					fileinput.readFully(filebyte);
					dos.writeInt(filebyte.length);
					dos.write(filebyte);
				} 
				
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		new FileSendServer();
	}


}
