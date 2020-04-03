package login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import join.Join;
import messenger.Messenger;
import messengerDAO.MessengerDAO;
import messengerVO.MessengerVO;


public class Login extends JFrame implements MouseListener {
	BufferedImage img = null;
	JLayeredPane layeredPane = new JLayeredPane();


	private JPanel contentPane;
	private JTextField tf_login_id;
	private JLabel lb_login_id_icon;
	private JTextField  tf_login_pw;
	private JLabel lb_login_pw_icon;
	public static JButton bt_login;
	private JButton bt_join;
	private int idsw=0;//���̵�
	private int pwsw=0;//��й�ȣ
	
	public Login() {//����Ʈ ������
		
		setTitle("�α���");//�̸����߿�
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	
		// �ǳڿ� �̹��� ���� �κ�
		try {
			img = ImageIO.read(new File("e:/imgFile/����_���ȭ��.jpg"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "�̹��� ����");
			System.exit(0);
		}
		
		myPanel panel = new myPanel();
		panel.setSize(800, 600);
		
		
		getContentPane().setLayout(null);
		contentPane.setLayout(null);
		
		JLabel lb_login_companyname = new JLabel("(\uC8FC) \uAC00\uC988\uC544 \uBA54\uC2E0\uC800");
		lb_login_companyname.setFont(new Font("���� ���", Font.BOLD, 30));
		lb_login_companyname.setBounds(266, 33, 262, 85);
		contentPane.add(lb_login_companyname);
		
		tf_login_id = new JTextField();
		tf_login_id.addMouseListener(this);
		tf_login_id.setFont(new Font("���� ���", Font.PLAIN, 20));
		tf_login_id.setBounds(292, 154, 255, 53);
		tf_login_id.setText("\uC0AC\uC6D0 \uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		contentPane.add(tf_login_id);
		tf_login_id.setColumns(10);
		
		
		lb_login_id_icon = new JLabel("");
		lb_login_id_icon.setIcon(new ImageIcon(Login.class.getResource("/imgFile/\uB85C\uADF8\uC778_\uC0AC\uB78C_\uC544\uC774\uCF58.png")));
		lb_login_id_icon.setBackground(new Color(0,0,0));
		lb_login_id_icon.setBounds(229, 154, 63, 53);
		contentPane.add(lb_login_id_icon);
		
		tf_login_pw = new JTextField();
		tf_login_pw.addMouseListener(this);
		tf_login_pw.setFont(new Font("���� ���", Font.PLAIN, 20));
		tf_login_pw.setColumns(10);
		tf_login_pw.setBounds(292, 231, 255, 53);
		tf_login_pw.setText("\uBE44\uBC00\uBC88\uD638\uB97C \uC785\uB825\uD574\uC8FC\uC138\uC694.");
		tf_login_pw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					try {
						new Logincheck1(tf_login_id.getText(),tf_login_pw.getText());
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException | SQLException | NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		contentPane.add(tf_login_pw);
		
		lb_login_pw_icon = new JLabel("");
		lb_login_pw_icon.setIcon(new ImageIcon(Login.class.getResource("/imgFile/\uB85C\uADF8\uC778_\uD328\uC2A4\uC6CC\uB4DC.png")));
		lb_login_pw_icon.setBackground(Color.BLACK);
		lb_login_pw_icon.setBounds(229, 231, 63, 53);
		contentPane.add(lb_login_pw_icon);
		
		bt_login = new JButton("\uB85C\uADF8\uC778");
		bt_login.setFont(new Font("���� ���", Font.PLAIN, 20));
		bt_login.setBounds(229, 379, 127, 66);
		bt_login.setBorderPainted(false);
		bt_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						try {
							Logincheck1 lgo=new Logincheck1(tf_login_id.getText(),tf_login_pw.getText());
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (ClassNotFoundException | SQLException | NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		contentPane.add(bt_login);
		
		JButton bt_join = new JButton("\uD68C\uC6D0 \uAC00\uC785");
		bt_join.setFont(new Font("���� ���", Font.PLAIN, 20));
		bt_join.setBorderPainted(false);
		
		bt_join.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e1) {
	                try {
	                	Join join=new Join();
	                	dispose();

					} catch (ClassNotFoundException | SQLException e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
					}
				}
		});
		bt_join.setBounds(420, 379, 127, 66);
		contentPane.add(bt_join);
	
		//�ٸ� ��ҵ��� ���̱� ���� �� ���������� ��ġ
		contentPane.add(panel);
		this.setVisible(true);
		
}//����Ʈ end
	
// �̹��� �ִ� Ŭ���� �����
 class myPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}	
 }


class Logincheck1 {
	public Logincheck1(String id, String pw) throws SQLException, ClassNotFoundException, IOException {
		// TODO Auto-generated constructor stub
		MessengerDAO dao = new MessengerDAO();
		MessengerVO tv1=new MessengerVO();//vo�迭 �� �ޱ��
		

		if (!id.equals("") || !pw.equals("")) {
			
			boolean logincheck =dao.login(id,pw);
			if(logincheck==true) {//Ʈ�� �α��ε�. ���� �޽��� â�� ���ְų� �ٸ� �����ҿ� ���������.
				
				boolean doublelogin=dao.inup(Integer.parseInt(id));
				if (doublelogin==true) {
					System.out.println("�α��� �Ǿ����ϴ�.");
					ArrayList<MessengerVO> arVO = new ArrayList<MessengerVO>();
					arVO = dao.getInfo(id,pw);
					try {
						JOptionPane.showMessageDialog(contentPane, arVO.get(0).getEmployee_name()+" �� ȯ���մϴ�");
						new Messenger(arVO);
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						dao.outup(Integer.parseInt(id));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						dao.outup(Integer.parseInt(id));
					}//��̰� ������
					dispose();
				}else {
					JOptionPane.showMessageDialog(contentPane, "�ߺ��α��� �Ǽ̽��ϴ�.");//
					dao.outup(Integer.parseInt(id));
				}
			}
		}else {
			JOptionPane.showMessageDialog(contentPane, "���̵�� ��й�ȣ�� �Է����ּ���.");
		}
	} //logincheck-end	

}//class-end
@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource()==tf_login_id) {
		idsw++;//�ʱ⿡ �ѹ���
		if (idsw==1) 
			tf_login_id.setText("");			
	}
	if (e.getSource()==tf_login_pw) {
	pwsw++;
	if (pwsw==1) 
		tf_login_pw.setText("");			
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

}//class-end



