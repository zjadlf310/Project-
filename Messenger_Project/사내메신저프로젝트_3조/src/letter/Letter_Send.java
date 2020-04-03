package letter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Letter_Send extends JFrame {
	BufferedImage img = null;
	private JPanel contentPane;
	private JLabel lbltype;
	private JTextField tf_letter_send_title;
	private JTextArea ta_letter;
	private JScrollPane jsp;
	private JTextArea ta_letter_send_file;
	private JButton bt_letter_file_find;
	private JButton bt_letter_send;
	private JButton bt_letter_cancel;
	private JFileChooser jfc = new JFileChooser();
	private File file;
	private String sendname, title, text, filename;
	
	
	public void File() {
		jfc.showSaveDialog(null);
		file = jfc.getSelectedFile();
		filename = file.getName();
		ta_letter_send_file.setText(filename);
		
	}
	

	public Letter_Send(String name, String dep) {
		sendname = name+"("+dep+")";
		setTitle("쪽지보내기");
		setBounds(100, 100, 581, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		try {
			img = ImageIO.read(new File("e:/imgFile/쪽지화면_테두리.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 실패");
			System.exit(0);
		}
		
		myPanel4 panel = new myPanel4();
		panel.setSize(240, 240);
		panel.setBounds(15,15,550,560);
		contentPane.setLayout(null);
		
		tf_letter_send_title = new JTextField();
		tf_letter_send_title.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_letter_send_title.setText("\uC81C\uBAA9\uC744 \uC785\uB825\uD558\uC138\uC694.");
		tf_letter_send_title.setBounds(44, 36, 481, 51);
		contentPane.add(tf_letter_send_title);
		tf_letter_send_title.setColumns(10);
		
		lbltype = new JLabel();
		lbltype.setBounds(44, 92, 100, 30);
		lbltype.setVisible(true);
		lbltype.setText(0+"/500");
		contentPane.add(lbltype);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 126, 481, 339);
		contentPane.add(scrollPane);
		
		ta_letter = new JTextArea();
		ta_letter.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ta_letter.setText("\uB0B4\uC6A9\uC744 \uC785\uB825\uD558\uC138\uC694.");
		ta_letter.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				byte [] tabyte = new byte[500];
				tabyte=ta_letter.getText().getBytes();
				lbltype.setText(tabyte.length+"/500");
				if(tabyte.length >= 500) {
					JOptionPane.showMessageDialog(scrollPane, "500문자를 넘을 수 없습니다");
				}	
			}
		});
		scrollPane.setViewportView(ta_letter);
		
		jsp = new JScrollPane();
		jsp.setBounds(44, 488, 380, 51);
		contentPane.add(jsp);
		
		ta_letter_send_file = new JTextArea();
		ta_letter_send_file.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ta_letter_send_file.setText("\uD30C\uC77C \uCCA8\uBD80");
		ta_letter_send_file.setEditable(false);
		jsp.setViewportView(ta_letter_send_file);
		
		bt_letter_file_find = new JButton("\uD30C\uC77C \uCCA8\uBD80");
		bt_letter_file_find.setForeground(new Color(255, 255, 255));
		bt_letter_file_find.setBackground(new Color(30, 144, 255));
		bt_letter_file_find.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		bt_letter_file_find.setBounds(436, 489, 89, 50);
		bt_letter_file_find.setBorderPainted(false);
		bt_letter_file_find.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==bt_letter_file_find) {
					File();
				}
			}
		});
		contentPane.add(bt_letter_file_find);
		
		contentPane.add(panel);
		
		bt_letter_send = new JButton("\uBCF4\uB0B4\uAE30");
		bt_letter_send.setForeground(new Color(255, 255, 255));
		bt_letter_send.setBackground(new Color(30, 144, 255));
		bt_letter_send.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		bt_letter_send.setBorderPainted(false);
		bt_letter_send.setBounds(103, 596, 126, 51);
		bt_letter_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				title = tf_letter_send_title.getText();
				text = ta_letter.getText();
				
				try {
					new letter_send_search(sendname, title, text, filename, file,name,dep);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dispose();
			}
			
		});
		contentPane.add(bt_letter_send);
		
		bt_letter_cancel = new JButton("\uCDE8\uC18C");
		bt_letter_cancel.setForeground(new Color(255, 255, 255));
		bt_letter_cancel.setBackground(new Color(128, 128, 128));
		bt_letter_cancel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		bt_letter_cancel.setBounds(325, 596, 126, 51);
		bt_letter_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int i =JOptionPane.showConfirmDialog(ta_letter,"작성하신 내용이 모두 사라집니다."
												+ "\n 정말 닫으시겠습니까?",
											null, JOptionPane.YES_NO_OPTION);
				if(i==0) {
					dispose();
				}
					
			}
			
		});
		bt_letter_send.setBorderPainted(false);
		contentPane.add(bt_letter_cancel);
		setVisible(true);
	}

	
	class myPanel4 extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}	
	}
	
}
