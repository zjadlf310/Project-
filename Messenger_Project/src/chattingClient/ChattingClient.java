package chattingClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import chattingServer.ChattingServer.ThreadServerClass;
import messengerDAO.MessengerDAO;
import messengerVO.MessengerVO;

public class ChattingClient extends JFrame {
	
	private JPanel contentPane;
	private JTextArea ta_chat;
	private JTextField ta_chat_input;
	private JButton bt_chat_send;
	private JTextArea ta_chat_ing;
	private JScrollPane scrollPane, scrollPane_1, scrollPane_2;
	//이미지용 라벨들
	private JLabel la_img, la_img2, la_img3;
	
	private Socket socket;

	private MessengerVO info;
	private ArrayList <ThreadServerClass> ChatList;
	String str,str2="";
    public ChattingClient(ArrayList<MessengerVO> arVO) throws IOException, ClassNotFoundException, SQLException {
		try {
			socket=new Socket("192.168.58.26", 6565);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(contentPane,"서버접속에러");
			socket.close();
			dispose();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(contentPane,"서버접속에러");
			socket.close();
			dispose();
		}
    	
    	
    	chattingUI(socket,arVO);
	}
	
	class ThreadClientRcvClass extends Thread {
    	Socket socket;
    	DataInputStream inputStream;
    	
    	public ThreadClientRcvClass(Socket socket) throws IOException{
    		this.socket = socket;
    		inputStream = new DataInputStream(socket.getInputStream());	
    		
    	}
    	
    	public void run() {    		
    		while(inputStream != null) {
    			try {
    				str = inputStream.readUTF();
					if(str.substring((str.length()-2), str.length()).equals("/p")) {
						str2 = str.substring(0,(str.length()-2));
						ta_chat_ing.setText("< 현재 채팅 참여자 >\n"+str2);
					}  
					else {						
						ta_chat.append(str);
						ta_chat.setCaretPosition(ta_chat.getText().length());
					}
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}   		    		
    	}    	
    }
    

    class ThreadClientSendClass extends Thread {
    	Socket socket; 
    	DataOutputStream outputStream;
    	

    	public ThreadClientSendClass(Socket socket) throws IOException {
    		this.socket = socket;
    		outputStream = new DataOutputStream(socket.getOutputStream());
    		
    	}	
    
    	public void run() {    		
    		try {
    			while(outputStream != null) {
    				outputStream.flush();    				
    			}
    		}
    		catch(IOException e) {
    			e.printStackTrace();
    		}    		
    	}
    }  
    
    // UI
    public void chattingUI(Socket s1, ArrayList<MessengerVO> arVO) throws IOException { 
    	
    	ThreadClientSendClass tcc=new ThreadClientSendClass(s1); 
    	ThreadClientRcvClass tcr=new ThreadClientRcvClass(s1);
    	
    	Thread tsend = new Thread(tcc);
    	Thread trcv = new Thread(tcr);
    	///////////////////////////////////////////////////////////////
    	String name = arVO.get(0).getEmployee_name();
    	String dep = arVO.get(0).getEmployee_dep();   
    	String rank = arVO.get(0).getEmployee_rank();
    	
		setBounds(100, 100, 971, 712);
		setTitle("전체채팅");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				tsend.stop();
				trcv.stop();
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 139, 541, 456);
		contentPane.add(scrollPane);
		
		ta_chat = new JTextArea();
		ta_chat.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ta_chat.setEditable(false);
		scrollPane.setViewportView(ta_chat);
		
		//이미지 추가를 위해 임시로 만든 판넬
		la_img = new JLabel("New label");
		la_img.setIcon(new ImageIcon(ChattingClient.class.getResource("/imgFile/\uCC44\uD305\uCC3D_\uBC30\uACBD\uD654\uBA74.jpg")));
		la_img.setBounds(12, -29, 541, 194);
		contentPane.add(la_img);
		
		ta_chat_input = new JTextField();
		tcc.outputStream.writeUTF(name+"("+dep+","+rank+")\n");
		ta_chat_input.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				try {
					tcc.outputStream.writeUTF(name+" : "+ta_chat_input.getText()+"\n");
					
					ta_chat_input.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
			
		});
		
		
		ta_chat_input.setBounds(12, 616, 437, 47);
		contentPane.add(ta_chat_input);
		
		
		bt_chat_send = new JButton("보내기");
		bt_chat_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bt_chat_send==e.getSource()) {
					try {
						tcc.outputStream.writeUTF(name+" : "+ta_chat_input.getText()+"\n");
						ta_chat_input.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		bt_chat_send.setForeground(new Color(255, 255, 255));
		bt_chat_send.setBackground(new Color(30, 144, 255));
		bt_chat_send.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		bt_chat_send.setBounds(461, 616, 92, 47);
		bt_chat_send.setBorderPainted(false);
		contentPane.add(bt_chat_send);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(565, 139, 378, 194);
		contentPane.add(scrollPane_2);
		
		//로그인한 사람 출력
		
		ta_chat_ing = new JTextArea();
		ta_chat_ing.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ta_chat_ing.setEditable(false);
		////////////////////////////////////////////////////////////////////////////

		scrollPane_2.setViewportView(ta_chat_ing);
				
		
		la_img2 = new JLabel("New label");
		la_img2.setIcon(new ImageIcon(ChattingClient.class.getResource("/imgFile/\uCC44\uD305\uCC3D_\uBC30\uACBD\uD654\uBA74.jpg")));
		la_img2.setBounds(511, -29, 541, 194);
		contentPane.add(la_img2);
		
		la_img3 = new JLabel("New label");
		la_img3.setIcon(new ImageIcon(ChattingClient.class.getResource("/imgFile/\uBC11\uC5D0_\uADF8\uB0E5_\uC788\uB294\uAC70.jpg")));
		la_img3.setBounds(565, 343, 378, 320);
		contentPane.add(la_img3);

		tsend.start();
    	trcv.start();
		
		setVisible(true);
	}
}