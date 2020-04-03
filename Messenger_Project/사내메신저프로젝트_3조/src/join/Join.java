package join;

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
import java.sql.SQLException;

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
import messengerDAO.MessengerDAO;

public class Join extends JFrame implements ActionListener, MouseListener {
	BufferedImage img = null;
	private JPanel contentPane;
	private JLabel lb_join_name;
	private JTextField tf_join_name;
	private int namesw=0;
	private JLabel lb_join_id;
	private JTextField tf_join_id;
	private int idsw=0;
	private JLabel lb_join_dep;
	private JLabel lb_join_rank;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JComboBox cb_join_dep;
	private JComboBox cb_join_rank;
	private JLabel lb_join_tel;
	private JTextField tf_join_tel;
	private int telsw=0;
	private JButton bt_join_ok;
	private JButton bt_join_no;
	private JLabel lb_join_notice;
	private MessengerDAO mdao;
	

	public Join() throws ClassNotFoundException, SQLException {//디폴트 불리면
		
		mdao = new MessengerDAO();
		setTitle("회원가입");
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Login c= new Login();
				JOptionPane.showMessageDialog(contentPane, "취소되었습니다.");
				dispose();
			}
		});
	
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
		
		lb_join_name = new JLabel("\uC131\uBA85");
		lb_join_name.setBounds(273, 37, 48, 33);
		lb_join_name.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_join_name.setBackground(Color.white);
		lb_join_name.setOpaque(true);
		contentPane.add(lb_join_name);
		
		//라벨은 기본적으로 투명색을 띄고 있어서 아래 옵션을 true로 설정해주어야 한다
		tf_join_name = new JTextField();
		tf_join_name.addMouseListener(this);
		tf_join_name.setBounds(273, 75, 230, 33);
		tf_join_name.setText("\uC774\uB984\uC744 \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		contentPane.add(tf_join_name);
		tf_join_name.setColumns(10);
		
		lb_join_id = new JLabel("\uC0DD\uC131\uD560 ID (\uC0AC\uC6D0 \uBC88\uD638)");
		lb_join_id.setBounds(273, 113, 230, 33);
		lb_join_id.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_join_id.setBackground(Color.white);
		lb_join_id.setOpaque(true);
		contentPane.add(lb_join_id);
		
		tf_join_id = new JTextField();
		tf_join_id.setBounds(273, 151, 230, 33);
		tf_join_id.setText("\uBC30\uC815\uBC1B\uC740 \uC0AC\uC6D0\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		tf_join_id.setColumns(10);
		tf_join_id.addMouseListener(this);	
		contentPane.add(tf_join_id);
		
		lb_join_dep = new JLabel("\uBD80\uC11C");
		lb_join_dep.setBounds(273, 189, 48, 33);
		lb_join_dep.setOpaque(true);
		lb_join_dep.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_join_dep.setBackground(Color.WHITE);
		contentPane.add(lb_join_dep);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setToolTipText("");
		scrollPane.setBounds(273, 227, 230, 33);
		contentPane.add(scrollPane);
		
		
		cb_join_dep = new JComboBox();
		cb_join_dep.setModel(new DefaultComboBoxModel(new String[] {"\uC778\uC0AC", "\uACBD\uC601", "\uAE30\uD68D", "\uAE30\uC220\uC9C0\uC6D0", "\uC601\uC5C5"}));
		cb_join_dep.setToolTipText("");
		scrollPane.setViewportView(cb_join_dep);
		
		lb_join_rank = new JLabel("\uC9C1\uAE09");
		lb_join_rank.setOpaque(true);
		lb_join_rank.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_join_rank.setBackground(Color.WHITE);
		lb_join_rank.setBounds(273, 265, 48, 33);
		contentPane.add(lb_join_rank);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(273, 303, 230, 33);
		contentPane.add(scrollPane_1);
		
		
		cb_join_rank = new JComboBox();
		cb_join_rank.setModel(new DefaultComboBoxModel(new String[] {"\uC0AC\uC6D0", "\uC778\uD134\u3160...", "\uBD80\uC7A5", "\uD300\uC7A5", "\uB300\uB9AC"}));
		scrollPane_1.setViewportView(cb_join_rank);
		
		lb_join_tel = new JLabel("\uC5F0\uB77D\uCC98");
		lb_join_tel.setOpaque(true);
		lb_join_tel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lb_join_tel.setBackground(Color.WHITE);
		lb_join_tel.setBounds(273, 341, 68, 33);
		contentPane.add(lb_join_tel);
		
		tf_join_tel = new JTextField();
		tf_join_tel.setText("000-0000-0000 '-'\uBE7C\uACE0 \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		tf_join_tel.setColumns(10);
		tf_join_tel.setBounds(273, 379, 230, 33);
		tf_join_tel.addMouseListener(this);
		contentPane.add(tf_join_tel);
		
		bt_join_ok = new JButton("\uAC00\uC785 \uC2E0\uCCAD");	//가입버튼
		bt_join_ok.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		bt_join_ok.setBounds(273, 439, 94, 33);
		bt_join_ok.setBorderPainted(false);
		bt_join_ok.addActionListener(this);
		contentPane.add(bt_join_ok);
		
		bt_join_no = new JButton("\uCDE8\uC18C");
		bt_join_no.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		bt_join_no.setBounds(409, 439, 94, 33);
		bt_join_no.setBorderPainted(false);
		bt_join_no.addActionListener(this);
		contentPane.add(bt_join_no);
		
		lb_join_notice = new JLabel("* \uCCAB \uB85C\uADF8\uC778\uC758 \uBE44\uBC00 \uBC88\uD638\uB294 \uC804\uD654\uBC88\uD638 \uC785\uB2C8\uB2E4. ");
		lb_join_notice.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		lb_join_notice.setBounds(246, 503, 305, 23);
		lb_join_notice.setBackground(Color.WHITE);
		lb_join_notice.setOpaque(true);
		contentPane.add(lb_join_notice);
		
		//다른 요소들이 보이기 위해 맨 마지막으로 위치
		contentPane.add(panel);
		contentPane.setLayout(null);
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
		if(e.getSource()==bt_join_ok) {//가입버튼
			if (tf_join_tel.getText().length()==11) {
				
			boolean doubleCheck = false;
			try {
				doubleCheck = mdao.id_doubleCheck(tf_join_id.getText());
			} catch (SQLException | ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//중복체크
			
			if (doubleCheck) {
				boolean newjoin = false;
				try {
					newjoin = mdao.kaip(tf_join_id.getText(),tf_join_name.getText(),tf_join_tel.getText(),
							(String)cb_join_rank.getSelectedItem(), (String)cb_join_dep.getSelectedItem());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (newjoin) {//신규가입
					JOptionPane.showMessageDialog(contentPane, "회원가입이 완료되었습니다! 다시 로그인 해주세요.");
					dispose();
					Login c= new Login();
				}
				else
					JOptionPane.showMessageDialog(contentPane, "잘못된 정보입니다. 다시 입력해주세요.");
			}
			else
				JOptionPane.showMessageDialog(contentPane, "이미 있는 사원번호입니다.");//중복
			}
			else
				JOptionPane.showMessageDialog(contentPane, "잘못된 정보입니다. 다시 입력해주세요.");
			
		}//가입버튼-end
		
		if(e.getSource()==bt_join_no) {//취소버튼
			JOptionPane.showMessageDialog(contentPane, "취소되었습니다.");
			dispose();
			Login c= new Login();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==tf_join_name) {
			namesw++;
			if (namesw==1) {
				tf_join_name.setText("");			
			}
		}
		if (e.getSource()==tf_join_id) {
			idsw++;
			if (idsw==1) {
				tf_join_id.setText("");			
			}
		}
		if (e.getSource()==tf_join_tel) {
			telsw++;
			if (telsw==1) {
				tf_join_tel.setText("");			
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

//employee_list/employee_name/employee_tel/
//employee_rank/employee_dep/employee_pw
	

}
