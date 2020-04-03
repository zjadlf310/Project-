package chattingServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import messengerVO.MessengerVO;

public class ChattingServer {
	
	ArrayList <ThreadServerClass> ChatList, People;
	Socket socket;
	ServerSocket serversocket;
	ThreadServerClass tServer;
	MessengerVO info;	
	
	public ChattingServer() throws IOException {
		ChatList = new ArrayList <ThreadServerClass>();
		People = new ArrayList <ThreadServerClass>();
		
		serversocket=new ServerSocket(6565);
		System.out.println("��������........");
		while(true) {
			socket=serversocket.accept();
			tServer=new ThreadServerClass(socket);
			
			tServer.start();
			ChatList.add(tServer);
			People.add(tServer);
			
			System.out.println("�α��� ��� �� : " + ChatList.size() + "��");
			/*for (int i = 0; i < People.size(); i++) {
				System.out.println("����Ʈ���� people array : "+ People.get(i));
			}*/
		}
	}
	
	public void sendChat(String chat) throws IOException{
		
		for (int i = 0; i < ChatList.size(); i++) {
			ChatList.get(i).outputStream.writeUTF(chat);
		}
	}
	
	public class ThreadServerClass extends Thread{
		Socket socket;
		DataInputStream inputStream;
		DataOutputStream outputStream;
		String name;	
		public ThreadServerClass(Socket s1) throws IOException {
		
			socket=s1;
			inputStream = new DataInputStream(s1.getInputStream());
			outputStream = new DataOutputStream(s1.getOutputStream());
		}
					
		public void run() {	
			String str="",str2="";	
			try{
				
				if(inputStream != null) {
					for (int i = 0; i < ChatList.size(); i++) {
						if(socket.equals(ChatList.get(i).socket)) {
							//***** ����� �޽������� �α׾ƿ����� �� ChatList �迭���� ������ *****
							ChatList.get(i).name = inputStream.readUTF();				
						}
					}
					for (int i = 0; i < ChatList.size(); i++) {
						str += ChatList.get(i).name;
					}
					str += "/p";
					sendChat(str);	
				}
				sendChat(("�α��� ��� �� : " + ChatList.size() + "��\n"));
				while(inputStream != null) {
					sendChat(inputStream.readUTF());
				}
				
			}catch(IOException e) {
				
			}finally {
				for (int i = 0; i < ChatList.size(); i++) {
					if(socket.equals(ChatList.get(i).socket)) {
						//***** ����� �޽������� �α׾ƿ����� �� ChatList �迭���� ������ *****
						ChatList.remove(i);						
					}
				}
				System.out.println("�α��� ��� �� : " + ChatList.size() + "��\n");
				
				for (int i = 0; i < ChatList.size(); i++) {
					str2 += ChatList.get(i).name;			
				}
				str2 += "/p";
				try {
					sendChat(str2);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
					
		}		
	}	
	
	public static void main(String[] args) throws IOException {
		new ChattingServer();
	}
}
