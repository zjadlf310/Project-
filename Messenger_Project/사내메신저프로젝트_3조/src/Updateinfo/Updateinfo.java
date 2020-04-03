package Updateinfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
//import 로그인_회원가입.Login.myPanel;??
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import login.Login;
import messenger.Messenger;
import messengerDAO.MessengerDAO;
import messengerVO.MessengerVO;

public class Updateinfo extends JFrame implements ActionListener, MouseListener {
	BufferedImage img = null;
	private JPanel contentPane;
	private JLabel lb_Updateinfo_name;
	private JTextField tf_Updateinfo_name;
	private int namesw=0;
	private JLabel lb_Updateinfo_pw;
	private JTextField tf_Updateinfo_pw;
	private int idsw=0;
	private JLabel lb_Updateinfo_dep;
	private JLabel lb_Updateinfo_rank;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JComboBox cb_Updateinfo_dep;
	private JComboBox cb_Updateinfo_rank;
	private JLabel lb_Updateinfo_tel;
	private JTextField tf_Updateinfo_tel;
	private int telsw=0;
	private JButton bt_Updateinfo_ok;
	private JButton bt_Updateinfo_no;
	private MessengerVO info;
	private MessengerDAO mdao;
	private Socket socket;
	private ArrayList<MessengerVO> arVO;

	public Updateinfo(MessengerVO info, Socket socket) throws ClassNotFoundException, SQLException {//디폴트 불리면
		this.info=info;
		this.socket=socket;
		mdao= new MessengerDAO();
		arVO = new ArrayList<MessengerVO>();
		arVO.add(info);
		setTitle("정보수정");
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		
	
		// 판넬에 이미지 삽입 부분
		try {
			img = ImageIO.read(new File("e:/imgFile/메인_배경화면.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "이미지 실패");
			System.exit(0);
		}
		
		myPanel2 panel = new myPanel2();
		panel.setSize(800, 600);
		contentPane.setLayout(null);
		
		lb_Updateinfo_name = new JLabel("\uC131\uBA85");
		lb_Updateinfo_name.setBounds(273, 37, 48, 33);
		lb_Updateinfo_name.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_Updateinfo_name.setBackground(Color.white);
		lb_Updateinfo_name.setOpaque(true);
		contentPane.add(lb_Updateinfo_name);
		
		//라벨은 기본적으로 투명색을 띄고 있어서 아래 옵션을 true로 설정해주어야 한다
		tf_Updateinfo_name = new JTextField();
		tf_Updateinfo_name.setBounds(273, 75, 230, 33);
		tf_Updateinfo_name.setText(info.getEmployee_name());
		contentPane.add(tf_Updateinfo_name);
		tf_Updateinfo_name.setColumns(10);
		
		lb_Updateinfo_pw = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lb_Updateinfo_pw.setBounds(273, 113, 230, 33);
		lb_Updateinfo_pw.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_Updateinfo_pw.setBackground(Color.white);
		lb_Updateinfo_pw.setOpaque(true);
		contentPane.add(lb_Updateinfo_pw);
		
		tf_Updateinfo_pw = new JTextField();
		tf_Updateinfo_pw.setBounds(273, 156, 230, 33);
		tf_Updateinfo_pw.setText(info.getEmployee_pw());
		tf_Updateinfo_pw.setColumns(10);
		contentPane.add(tf_Updateinfo_pw);
		
		lb_Updateinfo_dep = new JLabel("\uBD80\uC11C");
		lb_Updateinfo_dep.setBounds(273, 189, 48, 33);
		lb_Updateinfo_dep.setOpaque(true);
		lb_Updateinfo_dep.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_Updateinfo_dep.setBackground(Color.WHITE);
		contentPane.add(lb_Updateinfo_dep);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setToolTipText("");
		scrollPane.setBounds(273, 227, 230, 33);
		contentPane.add(scrollPane);
		
		
		cb_Updateinfo_dep = new JComboBox();
		cb_Updateinfo_dep.setModel(new DefaultComboBoxModel(new String[] {"\uC778\uC0AC", "\uACBD\uC601", "\uAE30\uD68D", "\uAE30\uC220\uC9C0\uC6D0", "\uC601\uC5C5"}));
		cb_Updateinfo_dep.setToolTipText("");
		scrollPane.setViewportView(cb_Updateinfo_dep);
		
		lb_Updateinfo_rank = new JLabel("\uC9C1\uAE09");
		lb_Updateinfo_rank.setOpaque(true);
		lb_Updateinfo_rank.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_Updateinfo_rank.setBackground(Color.WHITE);
		lb_Updateinfo_rank.setBounds(273, 265, 48, 33);
		contentPane.add(lb_Updateinfo_rank);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(273, 303, 230, 33);
		contentPane.add(scrollPane_1);
		
		
		cb_Updateinfo_rank = new JComboBox();
		cb_Updateinfo_rank.setModel(new DefaultComboBoxModel(new String[] {"\uC0AC\uC6D0", "\uC778\uD134\u3160...", "\uBD80\uC7A5", "\uD300\uC7A5", "\uB300\uB9AC"}));
		scrollPane_1.setViewportView(cb_Updateinfo_rank);
		
		lb_Updateinfo_tel = new JLabel("\uC5F0\uB77D\uCC98");
		lb_Updateinfo_tel.setOpaque(true);
		lb_Updateinfo_tel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_Updateinfo_tel.setBackground(Color.WHITE);
		lb_Updateinfo_tel.setBounds(273, 341, 68, 33);
		contentPane.add(lb_Updateinfo_tel);
		
		tf_Updateinfo_tel = new JTextField();
		tf_Updateinfo_tel.setText("000-0000-0000 '-'\uBE7C\uACE0 \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		tf_Updateinfo_tel.setColumns(10);
		tf_Updateinfo_tel.setBounds(273, 379, 230, 33);
		tf_Updateinfo_tel.addMouseListener(this);
		contentPane.add(tf_Updateinfo_tel);
		
		bt_Updateinfo_ok = new JButton("\uC815\uBCF4\uC218\uC815");	//가입버튼
		bt_Updateinfo_ok.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		bt_Updateinfo_ok.setBounds(273, 439, 94, 33);
		bt_Updateinfo_ok.setBorderPainted(false);
		bt_Updateinfo_ok.addActionListener(this);
		contentPane.add(bt_Updateinfo_ok);
		
		bt_Updateinfo_no = new JButton("\uCDE8\uC18C");
		bt_Updateinfo_no.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		bt_Updateinfo_no.setBounds(409, 439, 94, 33);
		bt_Updateinfo_no.setBorderPainted(false);
		bt_Updateinfo_no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(contentPane, "취소되었습니다.");
				dispose();
				try {

					new Messenger(arVO);
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.add(bt_Updateinfo_no);
		
		//다른 요소들이 보이기 위해 맨 마지막으로 위치
		contentPane.add(panel);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\uC5F0\uB77D\uCC98");
		label.setOpaque(true);
		label.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		label.setBackground(Color.WHITE);
		label.setBounds(273, 118, 68, 33);
		contentPane.add(label);
		this.setVisible(true);
	}
	
	// 이미지 넣는 클래스 만들기
	class myPanel2 extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bt_Updateinfo_ok) {//가입버튼
			
			if (tf_Updateinfo_tel.getText().length()==11) {
			
			boolean newUpdateinfo = false;
			try {
				newUpdateinfo = mdao.update(info.getEmployee_list(),tf_Updateinfo_pw.getText(),tf_Updateinfo_name.getText(),tf_Updateinfo_tel.getText(),
						(String)cb_Updateinfo_rank.getSelectedItem(), (String)cb_Updateinfo_dep.getSelectedItem());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (newUpdateinfo) {//신규가입
				JOptionPane.showMessageDialog(contentPane, "회원정보가 수정되었습니다! 다시 로그인 해주세요.");
				dispose();
				Login c= new Login();
			}
			else {
				JOptionPane.showMessageDialog(contentPane, "잘못된 정보입니다. 다시 입력해주세요.1");
			}//new updateinfo-end
			
			}
			else {
				JOptionPane.showMessageDialog(contentPane, "잘못된 정보입니다. 다시 입력해주세요.");
			}//if-end
		}//가입버튼-end
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		if (e.getSource()==tf_Updateinfo_tel) {
			telsw++;
			if (telsw==1) {
				tf_Updateinfo_tel.setText("");			
			}
		}
			
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
