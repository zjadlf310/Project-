package notice;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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


public class Notice_Write extends JFrame {
	BufferedImage img = null;
	private JPanel contentPane;
	private JTextField tf_notice_title;
	int titlesw=0;
	private JScrollPane scrollPane;
	private JTextArea ta_Notice_Write1;//작성내용 창
	int writesw=0;
	private JButton bt_notice_save;
	byte [] tabyte = new byte[500];//바이트표시
	private JLabel lbltype;//바이트 표시

	public Notice_Write(MessengerVO info) {
		setTitle("공지작성");
		setBounds(100, 100, 581, 712);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
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
		
		
		try {
			img = ImageIO.read(new File("e:/imgFile/쪽지화면_테두리.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 실패");
			System.exit(0);
		}
		
		myPanel7 panel = new myPanel7();
		panel.setSize(240, 240);
		panel.setBounds(15,95,550,560);
		setTitle("공지사항쓰기");
		contentPane.setLayout(null);
		
		tf_notice_title = new JTextField();
		tf_notice_title.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		tf_notice_title.setText("\uC81C\uBAA9\uC744 \uC785\uB825\uD558\uC138\uC694.");
		tf_notice_title.setBounds(22, 27, 371, 48);
		tf_notice_title.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				titlesw++;//초기에 한번만
				if (titlesw==1) 
					tf_notice_title.setText("");			
			}
		});
		contentPane.add(tf_notice_title);
		tf_notice_title.setColumns(10);

		bt_notice_save = new JButton("\uC800\uC7A5");
		bt_notice_save.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		bt_notice_save.setForeground(new Color(255, 255, 255));
		bt_notice_save.setBackground(new Color(30, 144, 255));
		bt_notice_save.setBorderPainted(false);
		bt_notice_save.setBounds(405, 27, 67, 48);
		
		bt_notice_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==bt_notice_save) {
					if (!tf_notice_title.getText().equals("") || !ta_Notice_Write1.getText().equals("")) {
					
					//글 저장하는 명령 들어가야함!!
					try {
						MessengerDAO dao=new MessengerDAO();
						boolean Noticecheck= dao.NoticeInsert(tf_notice_title.getText(),ta_Notice_Write1.getText(),info.getEmployee_name());
						if (Noticecheck) {
							JOptionPane.showMessageDialog(ta_Notice_Write1, "공지사항이 등록되었습니다");
							Notice notice=new Notice(info);
							dispose();
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					else
						JOptionPane.showMessageDialog(ta_Notice_Write1, "내용을 기입해주세요.");
				
				}
			}

		});
		contentPane.add(bt_notice_save);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(51, 136, 470, 470);
		contentPane.add(scrollPane);
		
		ta_Notice_Write1 = new JTextArea();
		ta_Notice_Write1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				writesw++;//초기에 한번만
				if (writesw==1) 
					ta_Notice_Write1.setText("");			
			}
		});
		ta_Notice_Write1.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		ta_Notice_Write1.setText("\uB0B4\uC6A9\uC744 \uC785\uB825\uD558\uC138\uC694.");
		ta_Notice_Write1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				tabyte=ta_Notice_Write1.getText().getBytes();
				
				if(e.getKeyCode() !=8) {
					lbltype.setText(tabyte.length+"/500");
					if(tabyte.length >= 500) {
						JOptionPane.showMessageDialog(scrollPane, "500문자를 넘을 수 없습니다");
					}	
				}
			}
		});
		
		scrollPane.setViewportView(ta_Notice_Write1);
		
		contentPane.add(panel);
		contentPane.setLayout(null);
		
		lbltype=new JLabel();
		lbltype.setBounds(50,110,100,30);//byte500
		lbltype.setVisible(true);
		lbltype.setText(0+"/500");
		contentPane.add(lbltype);
		
		
		
		//장식용 라벨
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Notice_Write.class.getResource("/imgFile/\uAC8C\uC2DC\uD310_\uC791\uC131_\uAFB8\uBBF8\uAE30.jpg")));
		lblNewLabel.setBounds(484, 27, 69, 48);
		contentPane.add(lblNewLabel);
		setVisible(true);
	}
	
	class myPanel7 extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}	
	}
}
