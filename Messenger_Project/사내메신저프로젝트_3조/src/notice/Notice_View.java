package notice;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
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

import messengerDAO.MessengerDAO;
import messengerVO.MessengerVO;

public class Notice_View extends JFrame {
	BufferedImage img = null;
	private JPanel contentPane;
	private JTextField tf_notice_title;
	private JButton bt_Notice_View, bt_notice_delete, bt_notice_close;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel lblNewLabel;
	
	byte [] tabyte = new byte[500];//바이트표시
	private JLabel lbltype;//바이트 표시

//이건 공지 고유 번호 DB에 저장된 rowNum을 이용해서 공지내용 받아와
	public Notice_View(MessengerVO info, int rowNum) throws ClassNotFoundException, SQLException {
		MessengerDAO mdao=new MessengerDAO();
		
		MessengerVO vos=mdao.NoticeRewrite(rowNum);
		/*
		this.num=num;
		this.title=title;
		this.content=content;
		this.writer=writer;
		this.regdate=regdate;	*/
		//얻은내용
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					new  Notice(info);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		setBounds(100, 100, 580, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("공지사항보기");
		contentPane.setLayout(null);
		
		try {
			img = ImageIO.read(new File("e:/imgFile/쪽지화면_테두리.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 실패");
			System.exit(0);
		}
		
		myPanel8 panel = new myPanel8();
		panel.setSize(240, 240);
		panel.setBounds(15,95,550,560);
		contentPane.setLayout(null);
		
		tf_notice_title = new JTextField();
		tf_notice_title.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_notice_title.setText(vos.getTitle());
		tf_notice_title.setBounds(90, 27, 371, 48);
		tf_notice_title.setEditable(false);
		contentPane.add(tf_notice_title);
		tf_notice_title.setColumns(10);
		
		bt_Notice_View = new JButton("\uC218\uC815\uD558\uAE30");
		bt_Notice_View.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		bt_Notice_View.setForeground(new Color(255, 255, 255));
		bt_Notice_View.setBackground(new Color(30, 144, 255));
		bt_Notice_View.setBorderPainted(false);
		bt_Notice_View.setBounds(132, 660, 100, 50);
		bt_Notice_View.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pw = JOptionPane.showInputDialog("관리자 암호를 입력해주세요.");
				if(pw.equals("4321")) {
					try {
						new Notice_Rewrite(info,rowNum);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
				}
			}
		});
		contentPane.add(bt_Notice_View);
		
		bt_notice_close = new JButton("\uB2EB\uAE30");
		bt_notice_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Notice notice=new Notice(info);
					dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		bt_notice_close.setForeground(Color.WHITE);
		bt_notice_close.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		bt_notice_close.setBorderPainted(false);
		bt_notice_close.setBackground(new Color(30, 144, 255));
		bt_notice_close.setBounds(354, 660, 100, 50);
		contentPane.add(bt_notice_close);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 136, 470, 470);
		contentPane.add(scrollPane);
		
		bt_notice_delete = new JButton("\uC0AD\uC81C\uD558\uAE30");
		bt_notice_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw = JOptionPane.showInputDialog("관리자 암호를 입력해주세요.");
				if(pw.equals("4321")) {
					try {
						mdao.NoticeDelete(rowNum);
						
					} catch (SQLException | ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(contentPane, "삭제되었습니다");
					dispose();
					try {
						new Notice(info);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		bt_notice_delete.setForeground(Color.WHITE);
		bt_notice_delete.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		bt_notice_delete.setBorderPainted(false);
		bt_notice_delete.setBackground(new Color(30, 144, 255));
		bt_notice_delete.setBounds(244, 660, 100, 50);
		contentPane.add(bt_notice_delete);
		setVisible(true);
	
		
		textArea = new JTextArea();
		textArea.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		textArea.setText(vos.getContent());
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		contentPane.add(panel);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Notice_View.class.getResource("e:/imgFile/\uAC8C\uC2DC\uD310_\uC218\uC815_\uAFB8\uBBF8\uAE30.jpg")));
		lblNewLabel.setBounds(504, 28, 29, 48);
		contentPane.add(lblNewLabel);
		setVisible(true);
	}
	
	class myPanel8 extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}	
	}
}
