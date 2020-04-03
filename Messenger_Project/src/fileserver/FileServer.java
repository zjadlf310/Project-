package fileserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
	private Socket socket;
	private ServerSocket serversocket;
	private ThreadFileServerClass tServer;

	public FileServer() throws IOException {
		// TODO Auto-generated constructor stub
		serversocket=new ServerSocket(6566);
		System.out.println("서버가동........");
		while(true) {
			socket=serversocket.accept();
			tServer=new ThreadFileServerClass(socket);
		
			tServer.start();
		}
	}
	
	class ThreadFileServerClass extends Thread {
		Socket socket;
		DataInputStream inputStream;
		DataOutputStream outputStream;
		FileOutputStream fos;
		FileInputStream fis;
		String filename;
		int filelenght;
		byte[] filebyte;
		
		public ThreadFileServerClass(Socket s1) throws IOException {
			// TODO Auto-generated constructor stub
			this.socket = s1;
			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
		}
		
		public void run() {
			try {
				if(inputStream !=null) {				
					filename = inputStream.readUTF();
				}
				if(inputStream !=null) {
					filelenght = inputStream.readInt();
					filebyte = new byte[filelenght];
				} 
				if(inputStream !=null) {
					inputStream.readFully(filebyte);
					fos = new FileOutputStream("e:/Save/"+filename);
					fos.write(filebyte);
					fos.close();
				} 
				
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new FileServer();
	}

}